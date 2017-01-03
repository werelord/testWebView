package com.test.testwebview;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyLog().penaltyDeath().build());

        String wvUserAgent = getUserAgent(getApplicationContext());
        Log.d("Test", wvUserAgent);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private String getUserAgent(final Context context) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                return WebSettings.getDefaultUserAgent(context);

            } else {
                return new WebView(context).getSettings().getUserAgentString();

            }
        } catch (Exception ex) {
            Log.e("test", "Caught exception with getUserAgent call", ex);
            return null;    // this will set the json object to null, which gson should skip in its session output..
        }
    }
}
