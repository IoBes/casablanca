# # Imports the monkeyrunner modules used by this program
from com.android.monkeyrunner import MonkeyRunner, MonkeyDevice
import sys
import os
import subprocess
import re

print "- Connecting to device"

# Connects to the current device, returning a MonkeyDevice object
device = MonkeyRunner.waitForConnection()

# sets a variable with the package's internal name
package = 'com.example.casablancatester'

# sets a variable with the name of an Activity in the package
activity = 'com.example.casablancatester.TestListActivity'

sdk_dir = os.environ.get('ANDROID_SDK')
if sdk_dir == None:
    print "*** Error: Must specify 'ANDROID_SDK' in the environment"
    sys.exit(-1)

adb = sdk_dir + "/platform-tools/adb"

print "- Launching adb: ", adb

subprocess.Popen([ adb, "-e", "logcat", "-c" ], shell=False ).communicate()

# Installs the Android package. Notice that this method returns a boolean, so you can test
# to see if the installation worked.
print "- Removing package"
device.removePackage(package)

print "- Installing package"
res = device.installPackage('CasablancaTester/bin/TestListActivity-debug.apk')

if not res:
    print("Installation failed.")
    sys.exit(-1)

# Runs the component

print "- Sleep 5"
MonkeyRunner.sleep(5)

print "- Starting package"
device.startActivity(package + '/' + activity)

print "- Sleep 5"
MonkeyRunner.sleep(5)

adbproc = subprocess.Popen([ adb, "-e", "logcat", "UnitTestpp:W", "*:S" ], shell=False,
                           stdout=subprocess.PIPE,
                           stderr=subprocess.STDOUT,
                           stdin=subprocess.PIPE,
                           bufsize=0)
adbproc.stdin.close()

start_msg_re = re.compile(r"""Start\.\n""")

a = re.compile(r"""Tests complete\. Total: \d+, Failed: (\d+),""")
m = None

line = adbproc.stdout.readline()
while line:
    if not start_msg_re.search(line):
        print line[:-1]
    m = a.search(line)
    if m:
        break
    line = adbproc.stdout.readline()

print "- Testing complete. Failed:", m.group(1)

adbproc.stdout.close()

sys.exit(int(m.group(1)))
