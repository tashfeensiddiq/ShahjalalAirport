package com.app.nerd.shahjalalairport.activites;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.app.nerd.shahjalalairport.R;

public class ScheduleActivity extends AppCompatActivity {

    WebView browser;
    ProgressDialog progressDialog;
    String URL = "http://www.flightstats.com" +
            "/go" +
            "/FlightStatus" +
            "/flightStatusByAirport.do?airportCode=dac&airportQueryType=1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        browser = (WebView) findViewById(R.id.webview);


        startWebView(URL);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog = new ProgressDialog(ScheduleActivity.this);
                        progressDialog.setMessage("Loading...");
                        progressDialog.show();
                        browser.reload();
                    }
                }, 700);
                progressDialog.dismiss();
            }
        });
    }

    private void startWebView(String url) {

        //Create new webview Client to show progress dialog
        //When opening a url or click on link

        browser.setWebViewClient(new WebViewClient() {
            //If you will not use this method url links are opeen in new brower not in webview
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            //Show loader on url load
            public void onLoadResource (WebView view, String url) {
                if (progressDialog == null) {
                    // in standard case YourActivity.this
                    progressDialog = new ProgressDialog(ScheduleActivity.this);
                    progressDialog.setMessage("Loading...");
                    progressDialog.show();
                }
            }
            public void onPageFinished(WebView view, String url) {
                try{
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                }catch(Exception exception){
                    exception.printStackTrace();
                }
            }

        });
        browser.loadUrl(url);
    }

    // Open previous opened link from history on webview when back button pressed

    @Override
    // Detect when the back button is pressed
    public void onBackPressed() {
        if(browser.canGoBack()) {
            browser.goBack();
        } else {
            // Let the system handle the back button
            super.onBackPressed();
            startActivity(new Intent(ScheduleActivity.this, MainActivity.class));
            overridePendingTransition(R.anim.anim_enter,R.anim.anim_leave);
            finish();
        }
    }
}
