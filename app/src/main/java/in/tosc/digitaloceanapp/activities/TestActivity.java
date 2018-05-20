package in.tosc.digitaloceanapp.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import in.tosc.digitaloceanapp.R;

@VisibleForTesting
public class TestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout frameLayout = new FrameLayout(this);
        frameLayout.setId(R.id.fragmentHolder);
        setContentView(frameLayout);
    }
}