package in.tosc.doandroidlib.api;

import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import in.tosc.doandroidlib.R;

public class DOLoginActivity extends AppCompatActivity {

    public static final String OAUTH_LOGIN_URL = "https://cloud.digitalocean.com/v1/oauth/authorize";

    WebView loginWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        requestWindowFeature(Window.FEATURE_PROGRESS);

        setContentView(R.layout.activity_dologin);


        loginWebView = (WebView) findViewById(R.id.webview)   ;
        if (Build.VERSION.SDK_INT >= 19) {
            loginWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        }

        loginWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                if (url.startsWith("callbackurl")) {
                    String authToken = Uri.parse(url).getQueryParameter("access_token");
                    finish();
                }

                return super.shouldOverrideUrlLoading(view, url);
            }
        });
    }
}
