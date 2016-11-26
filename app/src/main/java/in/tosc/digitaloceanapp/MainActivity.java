package in.tosc.digitaloceanapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.myjeeva.digitalocean.DigitalOcean;
import com.myjeeva.digitalocean.exception.DigitalOceanException;
import com.myjeeva.digitalocean.exception.RequestUnsuccessfulException;
import com.myjeeva.digitalocean.impl.DigitalOceanClient;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "DO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DigitalOcean apiClient = new DigitalOceanClient(getString(R.string.do_test_token));

        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {

                try {
                    Log.d(TAG, "onCreate: " + apiClient.getAccountInfo().getEmail());
                } catch (DigitalOceanException | RequestUnsuccessfulException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();



    }
}
