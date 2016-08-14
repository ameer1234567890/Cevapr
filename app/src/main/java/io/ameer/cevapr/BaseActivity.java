package io.ameer.cevapr;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public abstract class BaseActivity extends AppCompatActivity {
    public class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest url) {
            if (Uri.parse(String.valueOf(url)).getHost().equals("ameer.io")) {
                return false;
            }
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(String.valueOf(url)));
            startActivity(intent);
            return true;
        }
    }
}
