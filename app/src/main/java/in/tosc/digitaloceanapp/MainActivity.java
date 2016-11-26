package in.tosc.digitaloceanapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import in.tosc.digitaloceanapp.utils.FontsOverride;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "DO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FontsOverride.applyFontForToolbarTitle(this, "ProximaNova.ttf");

    }
}
