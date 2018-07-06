package in.tosc.digitaloceanapp.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import in.tosc.digitaloceanapp.R;
import in.tosc.digitaloceanapp.databinding.ActivitySplashBinding;
import in.tosc.doandroidlib.DigitalOcean;
import in.tosc.doandroidlib.api.DigitalOceanClient;
import in.tosc.doandroidlib.objects.AccountInfo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity {

    public static final String TAG = "Splash";


    private ActivitySplashBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String authToken = getSharedPreferences("DO", MODE_PRIVATE).getString("authToken", "");

        if (!authToken.isEmpty()) {
            progressFurther();
        }

        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);


        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String token = binding.etToken.getText().toString();
                if (token.isEmpty()) {
                    DigitalOcean.doLogin(SplashActivity.this);
                } else {
                    onLoggedIn(token);
                }
            }
        });

        binding.btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(getString(R.string.signup_url)));
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == DigitalOcean.LOGIN_SUCCESS) {
            String token = data.getStringExtra(DigitalOcean.EXTRA_AUTH_TOKEN);
            Log.d(TAG, "onActivityResult: " + "LOGIN SUCCESS" + token);
            onLoggedIn(token);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void onLoggedIn(final String token) {
        DigitalOceanClient client = DigitalOcean.getDOClient(token);
        final ProgressDialog progressDialog = new ProgressDialog(SplashActivity.this);
        progressDialog.setMessage(getString(R.string.checking_token));
        progressDialog.show();
        client.getAccount().enqueue(new Callback<AccountInfo>() {
            @Override
            public void onResponse(Call<AccountInfo> call, Response<AccountInfo> response) {
                progressDialog.dismiss();
                if (response.body() != null) {
                    getSharedPreferences("DO", MODE_PRIVATE).edit()
                            .putString("authToken", token)
                            .apply();
                    progressFurther();
                } else {
                    new AlertDialog.Builder(SplashActivity.this)
                            .setMessage(R.string.this_token_is_invalid)
                            .show();
                }
            }

            @Override
            public void onFailure(Call<AccountInfo> call, Throwable t) {
                Log.e("Failed check token", t.getLocalizedMessage());
                progressDialog.dismiss();
            }
        });
    }

    public void progressFurther() {
        Intent i = new Intent(this, DropletActivity.class);
        startActivity(i);
        finish();

    }
}
