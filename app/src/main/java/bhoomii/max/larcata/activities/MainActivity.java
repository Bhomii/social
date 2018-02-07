package bhoomii.max.larcata.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

import bhoomii.max.larcata.R;

public class MainActivity extends AppCompatActivity {

    boolean doubleBackToExitPressedOnce = false;
    private WebView mWebView;
    private int tabNb=1;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebView = (WebView) findViewById(R.id.webview);

        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (haveNetworkConnection()) {
                    setUrl(tabId);
                } else {
                    connectionLossDialog();
                }
            }
        });

        bottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(@IdRes int tabId) {
                setUrl(tabId);
            }
        });
    }


    //this method will set url according to the tab number
    private void setUrl(int tabId){
        switch (tabId) {
            case R.id.home:
                url=getResources().getString(R.string.url_one);
                break;
            case R.id.shop:
                url=getResources().getString(R.string.url_two);
                break;
            case R.id.events:
                url=getResources().getString(R.string.url_three);
                break;
            case R.id.contact:
                url=getResources().getString(R.string.url_four);
                break;
            case R.id.feedback:
                url=getResources().getString(R.string.url_five);
                break;
            case R.id.syria:
                url=getResources().getString(R.string.url_six);
                break;

            default:
                url=getResources().getString(R.string.url_one);
                break;
        }
        startWebView(url);
    }

    private void startWebView(String url) {
        mWebView.setWebViewClient(new WebViewClient() {
            ProgressDialog progressDialog;

            //Show loader on url load
//            public void onLoadResource(WebView view, String url) {
//                if (progressDialog == null) {
//                    // in standard case YourActivity.this
////                    progressDialog = new ProgressDialog(CustomColorAndFontActivity.this);
////                    progressDialog.setMessage("Openning a page");
////                    progressDialog.setCancelable(false);
////                    progressDialog.show();
//                }
//            }
//
//            public void onPageFinished(WebView view, String url) {
//                try {
//                    if (progressDialog.isShowing()) {
//                        progressDialog.dismiss();
//                    }
//                } catch (Exception exception) {
//                    exception.printStackTrace();
//                }
//            }
        });

        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl(url);
    }

    public boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }

    public void connectionLossDialog( ) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.dialog_connection, null);
        dialogBuilder.setView(dialogView);
        final AlertDialog findMeDialog = dialogBuilder.create();
        findMeDialog.show();
        LinearLayout reset_btn = (LinearLayout) dialogView.findViewById(R.id.ok);

        reset_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findMeDialog.dismiss();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (false) {
        } else {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        }
    }
}