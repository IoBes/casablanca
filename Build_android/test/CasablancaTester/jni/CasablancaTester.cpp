#include <jni.h>
#include <pthread.h>
#include <string>
#include <sstream>
#include <mutex>
#include <functional>
#include "unittestpp.h"
#include "src/TestReporter.h"
#include "src/TestDetails.h"
#include <pplx/pplxtasks.h>
#include <android/log.h>

JavaVM* JVM = nullptr;
void printLn(const std::string& s) {
    __android_log_print(ANDROID_LOG_WARN, "UnitTestpp", "%s", s.c_str());
}

struct MyTestReporter : UnitTest::TestReporter {
    UNITTEST_LINKAGE virtual void ReportTestStart(UnitTest::TestDetails const& test) {
    	std::stringstream ss;
    	ss << test.suiteName << ":" << test.testName << ": Start.";
    	printLn(ss.str());
    }
    UNITTEST_LINKAGE virtual void ReportFailure(UnitTest::TestDetails const& test, char const* failure) {
    	std::stringstream ss;
	ss << test.suiteName << ":" << test.testName << ": " << failure;
    	printLn(ss.str());
    }
    UNITTEST_LINKAGE virtual void ReportTestFinish(UnitTest::TestDetails const& test, bool passed, float secondsElapsed) {
        if (!passed) {
	    std::stringstream ss;
	    ss << test.suiteName << ":" << test.testName << ": Failed. Seconds: " << secondsElapsed;
	    printLn(ss.str());
        }
    }
    UNITTEST_LINKAGE virtual void ReportSummary(int totalTestCount, int failedTestCount, int failureCount, float secondsElapsed) {
    	std::stringstream ss;
    	ss << "Tests complete. Total: " << totalTestCount << ", Failed: " << failedTestCount << ", Time: " << secondsElapsed;
    	printLn(ss.str());
	printLn("--- Flush buffer ---");
	printLn("--- Flush buffer ---");
	printLn("--- Flush buffer ---");
	printLn("--- Flush buffer ---");
	printLn("--- Flush buffer ---");
	printLn("--- Flush buffer ---");
	printLn("--- Flush buffer ---");
	printLn("--- Flush buffer ---");
	printLn("--- Flush buffer ---");
	printLn("--- Flush buffer ---");
	printLn("--- Flush buffer ---");
	printLn("--- Flush buffer ---");
	printLn("--- Flush buffer ---");
	printLn("--- Flush buffer ---");
	printLn("--- Flush buffer ---");
	printLn("--- Flush buffer ---");
	printLn("--- Flush buffer ---");
	printLn("--- Flush buffer ---");
	printLn("--- Flush buffer ---");
	printLn("--- Flush buffer ---");
	printLn("--- Flush buffer ---");
    }
    UNITTEST_LINKAGE virtual void print(const std::string& s) {
    	printLn(s);
    }
};

static std::string to_lower(const std::string &str)
{
    std::string lower;
    for(auto iter = str.begin(); iter != str.end(); ++iter)
    {
        lower.push_back((char)tolower(*iter));
    }
    return lower;
}

bool matched_properties(UnitTest::TestProperties const& test_props) {
    if (test_props.Has("Requires")) {
        std::string const requires = test_props.Get("Requires");
        std::vector<std::string> requirements;

        // Can be multiple requirements, a semi colon seperated list
        std::string::size_type pos = requires.find_first_of(';');
        std::string::size_type last_pos = 0;
        while(pos != std::string::npos)
        {
            requirements.push_back(requires.substr(last_pos, pos - last_pos));
            last_pos = pos + 1;
            pos = requires.find_first_of(';', last_pos);
        }
        requirements.push_back(requires.substr(last_pos));
        for(auto iter = requirements.begin(); iter != requirements.end(); ++iter)
        {
            if(!UnitTest::GlobalSettings::Has(to_lower(*iter)))
            {
                return false;
            }
        }
    }
    return true;
}

bool should_run_test(UnitTest::Test *pTest)
{
    if(pTest->m_properties.Has("Ignore")) return false;
    if(pTest->m_properties.Has("Ignore:Linux")) return false;
    if(pTest->m_properties.Has("Ignore:Android")) return false;
    if (matched_properties(pTest->m_properties)) {
	return true;
    }
    return false;
}

void* RunTests() {
    UnitTest::TestList& tests = UnitTest::GetTestList();

    MyTestReporter mtr;

    // Do work
    UnitTest::TestRunner testrunner(mtr, false);
    testrunner.RunTestsIf(tests,
                          [](UnitTest::Test *pTest) -> bool
                          {
			      if (should_run_test(pTest))
				  return true;
			      auto& test = pTest->m_details;
			      std::stringstream ss;
			      ss << test.suiteName << ":" << test.testName << ": Skipped.";
			      printLn(ss.str());
			      return false;
                          }, 6000000 * 3);

    return nullptr;
}

#include <cstdlib>

extern "C" {

jint JNI_OnLoad(JavaVM* vm, void* reserved)
{
   JNIEnv* env;
   if (vm->GetEnv(reinterpret_cast<void**>(&env), JNI_VERSION_1_6) != JNI_OK)
   {
      return -1;
   }

   JVM = vm;
   return JNI_VERSION_1_6;
}

JNIEXPORT int JNICALL Java_com_example_casablancatester_TestListActivity_bar(JNIEnv* env, jobject l_obj) {

    RunTests();

    return 0;
}

JNIEXPORT void JNICALL Java_com_example_casablancatester_TestListActivity_changeDir(JNIEnv* l_env, jobject l_obj, jstring path) {
	const char* cpath = l_env->GetStringUTFChars(path, 0);
	chdir(cpath);

	l_env->ReleaseStringUTFChars(path, cpath);
}

}
