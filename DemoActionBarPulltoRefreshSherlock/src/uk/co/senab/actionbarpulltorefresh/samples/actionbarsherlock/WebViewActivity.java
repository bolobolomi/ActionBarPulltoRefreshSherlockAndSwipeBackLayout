/*
 * Copyright 2013 Chris Banes
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.co.senab.actionbarpulltorefresh.samples.actionbarsherlock;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import uk.co.senab.actionbarpulltorefresh.library.ActionBarPullToRefresh;
import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshLayout;
import uk.co.senab.actionbarpulltorefresh.library.listeners.OnRefreshListener;

/**
 * This sample shows how to use ActionBar-PullToRefresh with a
 * {@link android.webkit.WebView WebView}, and manually creating (and attaching) a
 * {@link uk.co.senab.actionbarpulltorefresh.extras.actionbarsherlock.AbsPullToRefreshAttacher} to the view.
 */
public class WebViewActivity extends SherlockActivity
        implements OnRefreshListener {

    private PullToRefreshLayout mPullToRefreshLayout;

    private WebView mWebView;
    ActionBar ab;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        // Find WebView and get it ready to display pages
        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new SampleWebViewClient());

        // Now find the PullToRefreshLayout and set it up
        mPullToRefreshLayout = (PullToRefreshLayout) findViewById(R.id.ptr_layout);
        ActionBarPullToRefresh.from(this)
                .allChildrenArePullable()
                .listener(this)
                .setup(mPullToRefreshLayout);

        // Finally make the WebView load something...
        mWebView.loadUrl("http://www.google.com");
       ab =  getSupportActionBar();
       ab.setDisplayHomeAsUpEnabled(true);
       ab.setDisplayUseLogoEnabled(true);
       ab.setLogo(R.drawable.warning);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	 getSupportMenuInflater().inflate(R.menu.main_menu, menu);
    	return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	
    	 switch (item.getItemId()) {
    	 	case android.R.id.home:
    	 		finish();
    	 		break;
    	 }
    	return super.onOptionsItemSelected(item);
    }
    @Override
    public void onRefreshStarted(View view) {
        // Here we just reload the webview
        mWebView.reload();
    }

    private class SampleWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // Return false so the WebView loads the url
            return false;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);

            // If the PullToRefreshAttacher is refreshing, make it as complete
            if (mPullToRefreshLayout.isRefreshing()) {
                mPullToRefreshLayout.setRefreshComplete();
            }
        }
    }
}
