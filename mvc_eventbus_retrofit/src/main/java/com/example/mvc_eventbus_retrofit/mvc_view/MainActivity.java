package com.example.mvc_eventbus_retrofit.mvc_view;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebView;

import com.example.mvc_eventbus_retrofit.R;
import com.example.mvc_eventbus_retrofit.mvc_controller.FragmentRouter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            FragmentRouter.replace(getSupportFragmentManager(), R.id.container, FragmentRouter.Tag.LIST, null, FragmentRouter.Animation.NON, false);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        Log.e("LOG_TAG", "onKeyDown");

        Fragment fragment = getSupportFragmentManager().findFragmentByTag(FragmentRouter.Tag.DETAIL.toString());
        if (fragment != null) {
            WebView webView = ((DetailFragment)fragment).getWebView();
            if (webView != null) {
                if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
                    webView.goBack();
                    return true;
                }
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
