package in.tosc.digitaloceanapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.databinding.DataBindingUtil;
import android.widget.Toast;

import in.tosc.digitaloceanapp.R;
import in.tosc.digitaloceanapp.activities.DropletCreateActivity;
import in.tosc.digitaloceanapp.databinding.FragmentAdditionalDetailsBinding;
import in.tosc.doandroidlib.DigitalOcean;
import in.tosc.doandroidlib.api.DigitalOceanClient;
import in.tosc.doandroidlib.objects.Droplet;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by harsh on 11/27/2016.
 */

public class AdditionalDetailsFragment extends Fragment {

    public static final String TAG = "AdditionalFragment";
    private FragmentAdditionalDetailsBinding binding;

    public AdditionalDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_additional_details, container, false);

        setAdditionalOptions();
        binding.btnCreateDroplet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dropletName = binding.etDropletName.getText().toString();
                if (dropletName.equals("")) {
                    Toast.makeText(getContext(), "Droplet Name is Required", Toast.LENGTH_SHORT).show();
                } else {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("Please wait... Creating Droplet");
                    builder.setIcon(R.drawable.digital_ocean_logo);
                    final AlertDialog dialog = builder.create();
                    dialog.show();
                    Droplet newDroplet = DropletCreateActivity.getDroplet();
                    newDroplet.setName(dropletName);
                    DigitalOceanClient doaClient = DigitalOcean.getDOClient(getContext().getSharedPreferences("DO", MODE_PRIVATE).getString("authToken", null));

                    Log.d(TAG, "onClick: " + newDroplet.getName());
                    Log.d(TAG, "onClick: " + newDroplet.getRegion().getSlug());
                    Log.d(TAG, "onClick: " + newDroplet.getImage().getSlug());
                    Log.d(TAG, "onClick: " + newDroplet.getSize());
                    Log.d(TAG, "onClick: " + newDroplet.getEnableBackup());
                    Log.d(TAG, "onClick: " + newDroplet.getEnableIpv6());
                    Log.d(TAG, "onClick: " + newDroplet.getEnablePrivateNetworking());


                    doaClient.createDroplet(newDroplet.getName(), newDroplet.getRegion().getSlug(),
                            newDroplet.getSize(), newDroplet.getImage().getSlug(), newDroplet.getEnableBackup(),
                            newDroplet.getEnableIpv6(), newDroplet.getEnablePrivateNetworking()).enqueue(new Callback<Droplet>() {
                        @Override
                        public void onResponse(Call<Droplet> call, Response<Droplet> response) {
                            Log.d(TAG, "onResponse: " + response.body());
                            dialog.dismiss();
                            getActivity().finish();
                        }

                        @Override
                        public void onFailure(Call<Droplet> call, Throwable t) {
                            Log.d(TAG, "onFailure: " + t.getMessage());
                        }
                    });
                }
            }
        });

        return binding.getRoot();
    }


    public void setAdditionalOptions() {
        binding.networkingCheckBox.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    DropletCreateActivity.getDroplet().setEnablePrivateNetworking(true);
                    Log.d(TAG, "onCheckedChanged: true");
                } else {
                    DropletCreateActivity.getDroplet().setEnablePrivateNetworking(false);
                }
            }
        });
        binding.ipv6CheckBox.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    DropletCreateActivity.getDroplet().setEnableIpv6(true);
                    Log.d(TAG, "onCheckedChanged: ipv6 true");
                } else {
                    DropletCreateActivity.getDroplet().setEnableIpv6(false);
                }
            }
        });
        binding.backupsCheckBox.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    DropletCreateActivity.getDroplet().setEnableBackup(true);
                    Log.d(TAG, "onCheckedChanged: backups true");
                } else {
                    DropletCreateActivity.getDroplet().setEnableBackup(false);
                }
            }
        });
    }

}
