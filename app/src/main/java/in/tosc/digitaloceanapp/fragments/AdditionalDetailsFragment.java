package in.tosc.digitaloceanapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;

import in.tosc.digitaloceanapp.R;
import in.tosc.digitaloceanapp.models.Datacenter;

/**
 * Created by harsh on 11/27/2016.
 */

public class AdditionalDetailsFragment extends Fragment {
    public EditText nameOfDroplet;
    SwitchCompat ipv6Switch;
    SwitchCompat backupSwitch;
    SwitchCompat privateNetworkSwitch;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_additional_activity, container, false);
        nameOfDroplet = (EditText) view.findViewById(R.id.droplet_name);
        ipv6Switch = (SwitchCompat) view.findViewById(R.id.switch_ipv6);
        backupSwitch = (SwitchCompat) view.findViewById(R.id.switch_backup);
        privateNetworkSwitch = (SwitchCompat) view.findViewById(R.id.switch_private_network);
        return view;
    }

    public String getDropletName()
    {
        return nameOfDroplet.getText().toString();
    }
    public boolean isipv6Checked()
    {
        return ipv6Switch.isChecked();
    }
    public boolean isBackupChecked()
    {
        return backupSwitch.isChecked();
    }
    public boolean isPrivateNetworkChecked()
    {
        return privateNetworkSwitch.isChecked();
    }

}
