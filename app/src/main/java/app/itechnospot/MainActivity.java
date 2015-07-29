package app.itechnospot;

import android.app.Activity;
import android.app.ActionBar;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.SlidingDrawer;


public class MainActivity extends ActionBarActivity {

    private WebView mainWv;
    private ProgressDialog progressDialog;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private Toolbar mToolbar;
    private SlidingDrawer slidingDrawer;
    private Button slideHandleButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mainWv = (WebView) findViewById(R.id.mainWebView);
        mainWv.loadUrl("http://www.itechnospot.com/blog/");
        mainWv.setWebViewClient(new myclient());

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        mDrawerToggle = new CustomActionBarDrawerToggle(this, mDrawerLayout);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(mToolbar); Causes crashes here but works fine without it
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mDrawerToggle.syncState();


        slidingDrawer = (SlidingDrawer)findViewById(R.id.slidingCart);
        slideHandleButton = (Button)findViewById(R.id.slideHandleButton);

        slidingDrawer.setOnDrawerOpenListener(new SlidingDrawer.OnDrawerOpenListener() {
            @Override
            public void onDrawerOpened() {
                slideHandleButton.setBackgroundResource(R.mipmap.ic_down_icon);
            }
        });

        slidingDrawer.setOnDrawerCloseListener(new SlidingDrawer.OnDrawerCloseListener() {
            @Override
            public void onDrawerClosed() {
                slideHandleButton.setBackgroundResource(R.mipmap.ic_up_icon);
            }
        });
    }

    private class CustomActionBarDrawerToggle extends ActionBarDrawerToggle {

        public CustomActionBarDrawerToggle(Activity mActivity,
                                           DrawerLayout mDrawerLayout) {
            super(mActivity, mDrawerLayout,mToolbar,
                    R.string.app_name, R.string.app_name);
        }

        @Override
        public void onDrawerClosed(View view) {
            //getActionBar().setTitle("Home");
            invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
        }

        @Override
        public void onDrawerOpened(View drawerView) {
            //getActionBar().setTitle("Menu");
            invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
        }
    }

    private class myclient extends WebViewClient{

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            progressDialog.show();
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progressDialog.dismiss();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
