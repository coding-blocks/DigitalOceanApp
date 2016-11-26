package in.tosc.digitaloceanapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import in.tosc.doandroidlib.objects.Droplet;

/**
 * Created by Jonsnow21 on 26/11/16.
 */

public class DetailDropletActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener{

    private TextView name, memory, size, region, osName, ipAddress;
    private Button resize, snapshot;
    private EditText snapshotName;
    private SwitchCompat switchIPv6, switchPrivateNet, switchBackup;
    private Droplet droplet;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_droplet);

        Gson gson = new Gson();
        droplet = gson.fromJson(getIntent().getStringExtra("DROPLET"),Droplet.class);

        name = (TextView) findViewById(R.id.droplet_name);
        ipAddress = (TextView) findViewById(R.id.ipAddress);
        memory = (TextView) findViewById(R.id.droplet_memory);
        size = (TextView) findViewById(R.id.droplet_size);
        region = (TextView) findViewById(R.id.droplet_region);
        osName = (TextView) findViewById(R.id.droplet_os);

        resize = (Button) findViewById(R.id.resize_droplet);
        snapshot = (Button) findViewById(R.id.take_droplet_snapshot);

        snapshotName = (EditText) findViewById(R.id.edittext_snapshot_name);

//        InputMethodManager mgr  = (InputMethodManager)getSystemService(DropletActivity.INPUT_METHOD_SERVICE);;
//        mgr.hideSoftInputFromWindow(snapshotName.getWindowToken(), 0);

        switchIPv6 = (SwitchCompat) findViewById(R.id.switch_ipv6);
        switchPrivateNet = (SwitchCompat) findViewById(R.id.switch_private_network);
        switchBackup = (SwitchCompat) findViewById(R.id.switch_backup);

        setData();
        setSwitches();

        switchIPv6.setOnCheckedChangeListener(this);
        switchPrivateNet.setOnCheckedChangeListener(this);
        switchBackup.setOnCheckedChangeListener(this);
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

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.switch_ipv6:
                if (!isChecked){

                } else{

                }
                break;

            case R.id.switch_private_network:
                if (!isChecked) {

                } else {

                }
                break;

            case R.id.switch_backup:
                if (!isChecked) {

                } else {

                }
                break;

            default:
                break;
        }
    }

    private void setData() {
        name.setText(droplet.getName());
        ipAddress.setText(droplet.getNetworks().getVersion4Networks().get(0).getIpAddress());
        memory.setText(String.format(getResources().getString(R.string.droplet_memory), String.valueOf(droplet.getMemorySizeInMb())));
        size.setText(String.format(getResources().getString(R.string.droplet_disk_size), String.valueOf(droplet.getDiskSize())));
        region.setText(droplet.getRegion().getName());
        osName.setText(droplet.getImage().getName());
    }

    private void setSwitches() {
        boolean isIPv6Enabled = droplet.getEnableIpv6() == null ? false : droplet.getEnableIpv6();
        boolean isPrivateNetworkEnabled = droplet.getEnablePrivateNetworking() == null ? false : droplet.getEnablePrivateNetworking();
        boolean isBackupEnabled = droplet.getEnableBackup() == null ? false : droplet.getEnableBackup();

        switchIPv6.setChecked(isIPv6Enabled);
        switchPrivateNet.setChecked(isPrivateNetworkEnabled);
        switchBackup.setChecked(isBackupEnabled);
    }
}
