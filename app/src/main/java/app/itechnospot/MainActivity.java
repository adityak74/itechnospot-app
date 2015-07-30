package app.itechnospot;

import android.app.Activity;
import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.SlidingDrawer;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    private WebView mainWv;
    private ProgressDialog progressDialog;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private Toolbar mToolbar;
    private SlidingDrawer slidingDrawer;
    private Button slideHandleButton;
    private Button teambt,homebt,loginbt,servicesbt,contactusbt, careersbt, aboutbt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainWv = (WebView) findViewById(R.id.mainWebView);
        mainWv.loadUrl("http://www.itechnospot.com/blog/");
        mainWv.setWebChromeClient(new chromeClient());
        mainWv.setWebViewClient(new webClient());
        mainWv.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        mainWv.getSettings().setJavaScriptEnabled(true);

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

        teambt = (Button) findViewById(R.id.teambt);
        teambt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.closeDrawers();
                mainWv.loadUrl("http://www.itechnospot.com/#team");
            }
        });
        homebt = (Button) findViewById(R.id.homebt);
        homebt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.closeDrawers();
                mainWv.loadUrl("http://www.itechnospot.com/blog");
            }
        });
        loginbt = (Button) findViewById(R.id.loginbt);
        loginbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.closeDrawers();
                mainWv.loadUrl("http://www.itechnospot.com/blog/wp-login.php");
            }
        });
        servicesbt = (Button) findViewById(R.id.servicesbt);
        servicesbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.closeDrawers();
                mainWv.loadUrl("http://www.itechnospot.com/#services");
            }
        });
        contactusbt = (Button) findViewById(R.id.contactusbt);
        contactusbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.closeDrawers();
                mainWv.loadUrl("http://itechnospot.com/#contact");
            }
        });
        careersbt = (Button) findViewById(R.id.careersbt);
        careersbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.closeDrawers();
                Toast.makeText(getApplicationContext(),"We are growing.Will update you soon!!!",Toast.LENGTH_LONG).show();
            }
        });

        aboutbt = (Button) findViewById(R.id.aboutbt);
        aboutbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.closeDrawers();
                AlertDialog alertDialog = new AlertDialog.Builder(
                        MainActivity.this).create();

                // Setting Dialog Title
                alertDialog.setTitle("About");

                // Setting Dialog Message
                alertDialog.setMessage("ITechnospot was founded in January 2014.It is Blogging Portal." +
                        "Also we offer services like Android App Development,Web Development and Logo Designing.");

                // Setting Icon to Dialog
                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                // Setting OK Button


                // Showing Alert Message
                alertDialog.show();
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

    private class chromeClient extends WebChromeClient{

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            progressDialog.show();
            if(newProgress>=100){
                if(progressDialog.isShowing())
                    progressDialog.dismiss();
            }
        }


    }

    private class webClient extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return super.shouldOverrideUrlLoading(view, url);
        }

        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            Toast.makeText(getApplicationContext(), "Oh no! " + description, Toast.LENGTH_SHORT).show();
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
        if (id == R.id.action_clear_cache) {
            mainWv.clearCache(true);
            mainWv.reload();
        }

        return super.onOptionsItemSelected(item);
    }
}
