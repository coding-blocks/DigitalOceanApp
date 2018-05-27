package in.tosc.digitaloceanapp.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import in.tosc.digitaloceanapp.R;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {    // Avoid null errors
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(getString(R.string.about));
        }
    }

    /**
     * Called by the {@link View} objects in the About Activity when they're pressed/clicked and
     * will launch the default browser to either the GitHub repo for the app or the Contributors
     * page for the repo depending on which button was pressed.
     *
     * @param view View object that was pressed
     */
    public void openPage(View view) {
        Intent i = new Intent(Intent.ACTION_VIEW);

        switch (view.getId()) {
            case R.id.relative_layout_github:
                // Send the user to the GitHub repo
                i.setData(Uri.parse("https://github.com/coding-blocks/DigitalOceanApp"));
                break;
            case R.id.relative_layout_see_all_contributors:
                // Send the user to the GitHub Contributors page
                i.setData(Uri.parse("https://github.com/coding-blocks/DigitalOceanApp/graphs/contributors"));
                break;
        }

        startActivity(i);
    }
}
