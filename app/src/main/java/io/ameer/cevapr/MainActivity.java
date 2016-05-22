package io.ameer.cevapr;

import android.app.*;
import android.content.*;
import android.graphics.Color;
import android.os.*;
import android.view.*;
import android.webkit.*;
import android.support.v7.widget.Toolbar;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

public class MainActivity extends BaseActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        final Activity MyActivity = this;
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header)
                .build();
        new DrawerBuilder().withSelectedItem(-1)
                .withAccountHeader(headerResult)
                .withActivity(this)
                .withToolbar(myToolbar)
                .withSliderBackgroundColor(Color.DKGRAY)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.drawer_item_home).withIdentifier(0),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_about).withIdentifier(1)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        switch((int) drawerItem.getIdentifier()){
                            case 1:
                                Intent myIntent = new Intent(MyActivity, AboutActivity.class);
                                startActivity(myIntent);
                                break;
                        }
                        return false;
                    }
                })
                .build();

        WebView myWebView = (WebView) findViewById(R.id.webview);
		myWebView.getSettings().setJavaScriptEnabled(true);
		myWebView.setWebViewClient(new MyWebViewClient());
		myWebView.loadUrl("https://ameer.io/");
		myWebView.setWebChromeClient(new WebChromeClient());
	}

    @Override
    public void onBackPressed(){
        WebView myWebView = (WebView) findViewById(R.id.webview);
        if(myWebView.canGoBack()) {
            myWebView.goBack();
        } else {
            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);
        }
    }
}
