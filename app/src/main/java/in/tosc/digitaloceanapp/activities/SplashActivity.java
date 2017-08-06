package in.tosc.digitaloceanapp.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import in.tosc.digitaloceanapp.R;
import in.tosc.doandroidlib.DigitalOcean;

public class SplashActivity extends AppCompatActivity {

    public static final String TAG = "Splash";

    Button loginButton, signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String authToken = getSharedPreferences("DO", MODE_PRIVATE).getString("authToken", "");

        if (!authToken.isEmpty()) {
            progressFurther();
        }

        setContentView(R.layout.activity_splash);

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        loginButton = (Button) findViewById(R.id.btnLogin);
        signupButton = (Button) findViewById(R.id.btnSignup);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DigitalOcean.doLogin(SplashActivity.this);
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(getString(R.string.signup_url)));
                startActivity(i);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == DigitalOcean.LOGIN_SUCCESS) {
            Log.d(TAG, "onActivityResult: " + "LOGIN SUCCESS" + data.getStringExtra(DigitalOcean.EXTRA_AUTH_TOKEN));
            DigitalOcean.onLoggedIn(data.getStringExtra(DigitalOcean.EXTRA_AUTH_TOKEN));
            getSharedPreferences("DO", MODE_PRIVATE).edit()
                    .putString("authToken", data.getStringExtra(DigitalOcean.EXTRA_AUTH_TOKEN))
                    .apply();
            progressFurther();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    public void progressFurther () {
        Intent i = new Intent(this, DropletActivity.class);
        startActivity(i);
        finish();

    }
}
