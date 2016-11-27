package in.tosc.digitaloceanapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import in.tosc.doandroidlib.DigitalOcean;

public class SplashActivity extends AppCompatActivity {

    public static final String TAG = "Splash";

    Button loginButton, signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!getSharedPreferences("DO", MODE_PRIVATE).getString("authToken", "").isEmpty()) {
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

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DigitalOcean.doLogin(SplashActivity.this);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == DigitalOcean.LOGIN_SUCCESS) {
            Log.d(TAG, "onActivityResult: " + "LOGIN SUCCESS" + data.getStringExtra("authToken"));
            DigitalOcean.onLoggedIn(data.getStringExtra("authToken"));
            getSharedPreferences("DO", MODE_PRIVATE).edit()
                    .putString("authToken", data.getStringExtra("authToken"))
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
