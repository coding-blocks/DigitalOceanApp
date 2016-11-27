package in.tosc.digitaloceanapp;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import in.tosc.digitaloceanapp.fragments.SelectImageFragment;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class DropletCreateActivity extends AppCompatActivity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_droplet_create);
        Button next = (Button) findViewById(R.id.buttonNext);
        Button prev = (Button) findViewById(R.id.buttonPrev);
    }

    @Override
    protected void onResume() {
        super.onResume();
        FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        SelectImageFragment selectImageFragment = new SelectImageFragment();
        fragmentTransaction.add(R.id.fragmentHolder,selectImageFragment,"CREATE_DROPLET");
        fragmentTransaction.commit();
    }
}