package com.example.casablancatester;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import java.lang.Thread;

import com.example.casablancatester.dummy.DummyContent;


/**
 * An activity representing a list of Tests. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link TestDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 * <p>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link TestListFragment} and the item details
 * (if present) is a {@link TestDetailFragment}.
 * <p>
 * This activity also implements the required
 * {@link TestListFragment.Callbacks} interface
 * to listen for item selections.
 */
public class TestListActivity extends FragmentActivity
        implements TestListFragment.Callbacks {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_list);

        if (findViewById(R.id.test_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            ((TestListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.test_list))
                    .setActivateOnItemClick(true);
        }
        
        // TODO: If exposing deep links into your app, handle intents here.
        new CasablancaTesterThread(this).start();
    }

    private static int itemcounter = 0;

    private class DelayedContentAdd extends AsyncTask<Void,Void,Void> {
    	DelayedContentAdd(String s) {
    		this.s = s;
    	}

    	private String s;
        
        @Override
        protected void onProgressUpdate(Void... values) {
	    	DummyContent.addItem(new DummyContent.DummyItem("callback" + itemcounter++, s));
        }
        @Override
    	protected Void doInBackground(Void... voids) {
	    	publishProgress();
			return null;
    	}
    }
    
    private void callback(String s) {
    	new DelayedContentAdd(s).execute();
    	System.err.println(s);
    }
    
    class CasablancaTesterThread extends Thread {
    	TestListActivity m_tla;
    	CasablancaTesterThread(TestListActivity tla) {
    		this.m_tla = tla;
    	}
    	public void run() {
    		m_tla.changeDir(m_tla.getCacheDir().getPath());
    		m_tla.bar();
    	}
    }

    static {
    	//System.loadLibrary("c++_shared");
    	//System.loadLibrary("gnustl_shared");
    	//System.loadLibrary("iconv");
    	System.loadLibrary("cpprest");
    	System.loadLibrary("unittestpp");
    	System.loadLibrary("common_utilities");
    	System.loadLibrary("httptest_utilities");
    	System.loadLibrary("json_test");
    	System.loadLibrary("pplx_test");
    	System.loadLibrary("httpclient_test");
    	System.loadLibrary("httplistener_test");
    	System.loadLibrary("asyncop_test");
    	System.loadLibrary("utils_test");
    	System.loadLibrary("uri_test");
    	System.loadLibrary("websocketclient_test");
    	System.loadLibrary("CasablancaTester");
    }
    
    native void changeDir(String path);
    native int bar();

    /**
     * Callback method from {@link TestListFragment.Callbacks}
     * indicating that the item with the given ID was selected.
     */
    @Override
    public void onItemSelected(String id) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(TestDetailFragment.ARG_ITEM_ID, id);
            TestDetailFragment fragment = new TestDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.test_detail_container, fragment)
                    .commit();

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(this, TestDetailActivity.class);
            detailIntent.putExtra(TestDetailFragment.ARG_ITEM_ID, id);
            startActivity(detailIntent);
        }
    }
}
