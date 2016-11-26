package in.tosc.digitaloceanapp;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by Jonsnow21 on 26/11/16.
 */

public class DetailDropletActivity extends AppCompatActivity{

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_detail_droplet);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_droplet, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.delete_droplet) {
            // TODO: 26/11/16 perform delete
            return true;
        } else if (id == R.id.switch_off) {
            // TODO: 26/11/16 perform droplet power toggle
            return true;
        } else if (id == R.id.edit_name) {
            // TODO: 26/11/16 perform droplet edit name
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
