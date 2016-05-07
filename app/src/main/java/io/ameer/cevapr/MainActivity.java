package io.ameer.cevapr;

import android.app.*;
import android.content.*;
import android.net.*;
import android.os.*;
import android.view.*;
import android.webkit.*;
import android.widget.*;

public class MainActivity extends Activity 
{
	WebView myWebView;

	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);

		this.getWindow().requestFeature(Window.FEATURE_PROGRESS);
		setContentView(R.layout.main );

		getWindow().setFeatureInt( Window.FEATURE_PROGRESS, Window.PROGRESS_VISIBILITY_ON); 

		myWebView = (WebView) findViewById( R.id.webview );
		myWebView.getSettings().setJavaScriptEnabled(true);
		myWebView.setWebViewClient(new MyWebViewClient());
		myWebView.loadUrl("https://ameer.io/");

		final Activity MyActivity = this;
		myWebView.setWebChromeClient(new WebChromeClient() {
				public void onProgressChanged(WebView view, int progress)
				{
					MyActivity.setTitle("Loading...");
					MyActivity.setProgress(progress * 100);
					if(progress == 100)
						MyActivity.setTitle(R.string.app_name);
				}
			});
	}
	
	@Override
    public void onBackPressed() {
        if(myWebView.canGoBack()) {
            myWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }

	private class MyWebViewClient extends WebViewClient {
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			if (Uri.parse(url).getHost().equals("ameer.io")) {
				return false;
			}
			Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
			startActivity(intent);
			return true;
		}
	}
}

