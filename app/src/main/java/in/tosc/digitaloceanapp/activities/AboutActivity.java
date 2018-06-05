package in.tosc.digitaloceanapp.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import in.tosc.digitaloceanapp.R;

public class AboutActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.about));


    }

    public void openPage(View view)
    {
        String url = "";
        switch (view.getId())
        {
            case R.id.relative_layout_github:
                        url = "https://github.com/coding-blocks/DigitalOceanApp";
                        break;
            case R.id.relative_layout_see_all_contributors:
                        url = "https://github.com/coding-blocks/DigitalOceanApp/graphs/contributors?from=2016-11-20&to=2017-06-30&type=c";
                        break;
        }
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }





}
