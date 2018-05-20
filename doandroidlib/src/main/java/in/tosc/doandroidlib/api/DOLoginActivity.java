package in.tosc.doandroidlib.api;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import in.tosc.doandroidlib.DigitalOcean;
import in.tosc.doandroidlib.R;

import static in.tosc.doandroidlib.DigitalOcean.EXTRA_AUTH_TOKEN;

public class DOLoginActivity extends AppCompatActivity {

    public static final String TAG = DOLoginActivity.class.getSimpleName();

    public static final String OAUTH_LOGIN_URL = "https://cloud.digitalocean.com/v1/oauth/authorize";
    public static final String USER_AGENT_FAKE = "";


    WebView loginWebView;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        requestWindowFeature(Window.FEATURE_PROGRESS);

        setContentView(R.layout.activity_dologin);


        loginWebView = (WebView) findViewById(R.id.webView);
        loginWebView.getSettings().setUserAgentString(this.getString(R.string.USER_AGENT_FAKE)
        );

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        if (Build.VERSION.SDK_INT >= 19) {
            loginWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        }

        loginWebView.setWebViewClient(new WebViewClient() {

            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {


                Log.d(TAG, "shouldOverrideUrlLoading: " + url);

                if (url.startsWith(DigitalOcean.getCallbackUrl())) {
                    String properUrl = url.replace("#", "?");
                    String authToken = Uri.parse(properUrl).getQueryParameter("access_token");
                    DigitalOcean.onLoggedIn(authToken);
                    setResult(DigitalOcean.LOGIN_SUCCESS, new Intent().putExtra(EXTRA_AUTH_TOKEN, authToken));
                    finish();
                }

                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        loginWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int progress) {
                progressBar.setProgress(progress);
                if (progress == 100) {
                    progressBar.setVisibility(View.GONE);

                } else {
                    progressBar.setVisibility(View.VISIBLE);

                }
            }
        });

        WebSettings settings = loginWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);

        Uri uri = Uri.parse(OAUTH_LOGIN_URL).buildUpon()
                .appendQueryParameter("client_id", DigitalOcean.getClientId())
                .appendQueryParameter("redirect_uri", DigitalOcean.getCallbackUrl())
                .appendQueryParameter("response_type", "token")
                .appendQueryParameter("scope", "read write")
                .build();
        loginWebView.loadUrl(uri.toString());
    }

    @Override
    protected void onResume() {
        super.onResume();
        loginWebView.onResume();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        loginWebView.saveState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        loginWebView.restoreState(savedInstanceState);
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onPause() {
        loginWebView.onPause();
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        if (loginWebView.canGoBack()) {
            loginWebView.goBack();
        } else {
            setResult(RESULT_CANCELED);
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        loginWebView.destroy();
        super.onDestroy();
    }
}
