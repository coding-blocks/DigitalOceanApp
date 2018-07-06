package in.tosc.digitaloceanapp.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.Gson;

import in.tosc.digitaloceanapp.R;
import in.tosc.digitaloceanapp.adapters.DropletsAdapter;
import in.tosc.digitaloceanapp.databinding.ActivityDetailDropletBinding;
import in.tosc.digitaloceanapp.interfaces.OnDropletNameChange;
import in.tosc.doandroidlib.DigitalOcean;
import in.tosc.doandroidlib.api.DigitalOceanClient;
import in.tosc.doandroidlib.common.ActionType;
import in.tosc.doandroidlib.objects.Action;
import in.tosc.doandroidlib.objects.Droplet;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Jonsnow21 on 26/11/16.
 */

public class DetailDropletActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private Droplet droplet;
    private DigitalOceanClient doaClient;
    private int position;
    public static final String TAG = "DetailDropletActivity";
    Gson gson;

    private ActivityDetailDropletBinding binding;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_droplet);

        gson = new Gson();
        droplet = gson.fromJson(getIntent().getStringExtra("DROPLET"), Droplet.class);
        position = getIntent().getIntExtra(DropletsAdapter.DROPLET_CLICKED_POSITION, 0);


        doaClient = DigitalOcean.getDOClient(getSharedPreferences("DO", MODE_PRIVATE).getString("authToken", null));
//        setData(droplet);
        setIndividualFeatures(droplet);
        setSwitches();

        binding.switchIpv6.setOnCheckedChangeListener(this);
        binding.switchPrivateNetwork.setOnCheckedChangeListener(this);
        binding.switchBackup.setOnCheckedChangeListener(this);
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
            new MaterialDialog.Builder(this)
                    .title(R.string.delete_droplet)
                    .content(getString(R.string.dialog_delete_droplet_msg, droplet.getName()))
                    .positiveText(R.string.dialog_confirm).onPositive(new MaterialDialog.SingleButtonCallback() {
                @Override
                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                    Snackbar.make(binding.coordinatorLayout,
                            getString(R.string.delete_droplet_msg),
                            Snackbar.LENGTH_INDEFINITE)
                            .show();
                    doaClient.performAction(droplet.getId(), ActionType.DESTROY, null).enqueue(new Callback<Action>() {
                        @Override
                        public void onResponse(Call<Action> call, Response<Action> response) {
                            Log.d("DESTROY", String.valueOf(response.code()));
                            DropletActivity.refreshData();
                            finish();
                        }

                        @Override
                        public void onFailure(Call<Action> call, Throwable t) {

                        }
                    });
                }
            })
                    .negativeText(R.string.dialog_cancel).onNegative(new MaterialDialog.SingleButtonCallback() {
                @Override
                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                    dialog.dismiss();
                }
            })
                    .show();
            return true;
        } else if (id == R.id.switch_off) {

            doaClient.performAction(droplet.getId(), ActionType.REBOOT, null).enqueue(new Callback<Action>() {
                @Override
                public void onResponse(Call<Action> call, Response<Action> response) {
                    Log.d("REBOOT", response.code() + "");
                }

                @Override
                public void onFailure(Call<Action> call, Throwable t) {

                }
            });
            return true;
        } else if (id == R.id.power_off) {

            doaClient.performAction(droplet.getId(), ActionType.POWER_OFF, null).enqueue(new Callback<Action>() {
                @Override
                public void onResponse(Call<Action> call, Response<Action> response) {
                    Log.d("POWER_OFF", response.code() + "");
                }

                @Override
                public void onFailure(Call<Action> call, Throwable t) {

                }
            });
            return true;
        } else if (id == R.id.power_on) {

            doaClient.performAction(droplet.getId(), ActionType.POWER_ON, null).enqueue(new Callback<Action>() {
                @Override
                public void onResponse(Call<Action> call, Response<Action> response) {
                    Log.d("POWER_ON", response.code() + "");
                }

                @Override
                public void onFailure(Call<Action> call, Throwable t) {

                }
            });
            return true;
        } else if (id == R.id.edit_name) {
            new MaterialDialog.Builder(this)
                    .title(R.string.rename_droplet)
                    .content("")
                    .inputType(InputType.TYPE_CLASS_TEXT)
                    .input(R.string.droplet_name, R.string.empty, new MaterialDialog.InputCallback() {
                        @Override
                        public void onInput(MaterialDialog dialog, CharSequence input) {
                            doaClient.performAction(droplet.getId(), ActionType.RENAME, input.toString()).enqueue(new Callback<Action>() {
                                @Override
                                public void onResponse(Call<Action> call, Response<Action> response) {
                                    Log.d("RENAME", response.code() + "");
                                    OnDropletNameChange onDropletNameChange = new OnDropletNameChange() {
                                        @Override
                                        public void onSuccess(List<Droplet> modifiedData) {
                                            Droplet thisDroplet = modifiedData.get(position);
                                            setData(thisDroplet);
                                        }

                                        @Override
                                        public void onError(String message) {
                                            Log.e(TAG, "onError: " + message);
                                        }
                                    };
                                    DropletActivity.refreshModifiedData(onDropletNameChange);
                                    Log.d("TAG", "onResponse: changed");
                                }

                                @Override
                                public void onFailure(Call<Action> call, Throwable t) {

                                }
                            });
                        }
                    }).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, final boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.switch_ipv6:
                if (isChecked) {
                    doaClient.performAction(droplet.getId(), ActionType.ENABLE_IPV6, null)
                            .enqueue(new Callback<Action>() {
                                @Override
                                public void onResponse(@NonNull Call<Action> call,
                                                       @NonNull Response<Action> response) {
                                    if (response.code() >= 200 && response.code() <= 299) {
                                        Snackbar.make(binding.coordinatorLayout,
                                                getString(R.string.ipv6_enabled),
                                                Snackbar.LENGTH_SHORT)
                                                .show();
                                        DropletActivity.refreshData();
                                    } else {
                                        Log.d("IPv6", response.code() + "");
                                        setSwitchWithoutTriggering(binding.switchIpv6, false);
                                        Snackbar.make(binding.coordinatorLayout,
                                                getString(R.string.ipv6_couldnt_be_enabled),
                                                Snackbar.LENGTH_SHORT)
                                                .show();

                                    }
                                }

                                @Override
                                public void onFailure(Call<Action> call, Throwable t) {
                                    setSwitchWithoutTriggering(binding.switchIpv6, false);
                                    Snackbar.make(binding.coordinatorLayout,
                                            getString(R.string.network_error),
                                            Snackbar.LENGTH_SHORT)
                                            .show();
                                }
                            });
                } else {
                    setSwitchWithoutTriggering(binding.switchIpv6, true);
                    Snackbar.make(binding.coordinatorLayout,
                            getString(R.string.ipv6_cannot_be_disabled),
                            Snackbar.LENGTH_SHORT)
                            .show();
                }
                break;

            case R.id.switch_private_network:
                if (isChecked) {
                    doaClient.performAction(droplet.getId(),
                            ActionType.ENABLE_PRIVATE_NETWORKING,
                            null)
                            .enqueue(new Callback<Action>() {
                                @Override
                                public void onResponse(Call<Action> call, Response<Action> response) {
                                    if (response.code() >= 200 && response.code() <= 299) {
                                        Snackbar.make(binding.coordinatorLayout,
                                                getString(R.string.private_network_enabled),
                                                Snackbar.LENGTH_SHORT)
                                                .show();
                                        DropletActivity.refreshData();
                                    } else {
                                        Log.d("SPN", response.code() + "");
                                        setSwitchWithoutTriggering(binding.switchPrivateNetwork, false);
                                        Snackbar.make(binding.coordinatorLayout,
                                                getString(R.string.private_network_couldnt_be_enabled),
                                                Snackbar.LENGTH_SHORT)
                                                .show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<Action> call, Throwable t) {
                                    setSwitchWithoutTriggering(binding.switchPrivateNetwork, false);
                                    Snackbar.make(binding.coordinatorLayout,
                                            getString(R.string.network_error),
                                            Snackbar.LENGTH_SHORT)
                                            .show();
                                }
                            });
                } else {
                    setSwitchWithoutTriggering(binding.switchPrivateNetwork, true);
                    Snackbar.make(binding.coordinatorLayout,
                            getString(R.string.private_network_cannot_be_disabled),
                            Snackbar.LENGTH_SHORT)
                            .show();
                }
                break;

            case R.id.switch_backup:
                if (isChecked) {
                    doaClient.performAction(droplet.getId(), ActionType.ENABLE_BACKUPS, null).enqueue(new Callback<Action>() {
                        @Override
                        public void onResponse(Call<Action> call, Response<Action> response) {
                            if (response.code() >= 200 && response.code() <= 299) {
                                Snackbar.make(binding.coordinatorLayout,
                                        getString(R.string.backup_enabled),
                                        Snackbar.LENGTH_SHORT)
                                        .show();
                                DropletActivity.refreshData();
                            } else {
                                setSwitchWithoutTriggering(binding.switchBackup, false);
                                Snackbar.make(binding.coordinatorLayout,
                                        getString(R.string.backup_couldnt_be_enabled),
                                        Snackbar.LENGTH_SHORT)
                                        .show();
                                Log.d("SBE", response.code() + "");
                            }
                        }

                        @Override
                        public void onFailure(Call<Action> call, Throwable t) {
                            setSwitchWithoutTriggering(binding.switchBackup, false);
                            Snackbar.make(binding.coordinatorLayout,
                                    getString(R.string.network_error),
                                    Snackbar.LENGTH_SHORT)
                                    .show();
                        }
                    });
                } else {
                    doaClient.performAction(droplet.getId(), ActionType.DISABLE_BACKUPS, null).enqueue(new Callback<Action>() {
                        @Override
                        public void onResponse(Call<Action> call, Response<Action> response) {
                            if (response.code() >= 200 && response.code() <= 299) {
                                Snackbar.make(binding.coordinatorLayout,
                                        getString(R.string.backup_disabled),
                                        Snackbar.LENGTH_SHORT)
                                        .show();
                                DropletActivity.refreshData();
                            } else {
                                Log.d("SBD", response.code() + "");
                                setSwitchWithoutTriggering(binding.switchBackup, true);
                                Snackbar.make(binding.coordinatorLayout,
                                        getString(R.string.backup_couldnt_be_disabled),
                                        Snackbar.LENGTH_SHORT)
                                        .show();

                            }
                        }

                        @Override
                        public void onFailure(Call<Action> call, Throwable t) {
                            setSwitchWithoutTriggering(binding.switchBackup, true);
                            Snackbar.make(binding.coordinatorLayout,
                                    getString(R.string.network_error),
                                    Snackbar.LENGTH_SHORT)
                                    .show();
                        }
                    });
                }
                break;

            default:
                break;
        }
    }

    private void setData(Droplet droplet) {
        binding.dropletName.setText(droplet.getName());
        binding.ipAddress.setText(droplet.getNetworks()
                .getVersion4Networks()
                .get(0)
                .getIpAddress());
        binding.dropletMemory.setText(String.format(
                getResources().getString(R.string.droplet_memory),
                String.valueOf(droplet.getMemorySizeInMb())));
        binding.dropletSize.setText(String.format(getResources().getString(
                R.string.droplet_disk_size),
                String.valueOf(droplet.getDiskSize())));
        binding.dropletRegion.setText(droplet.getRegion().getName());
        binding.dropletOs.setText(droplet.getImage().getName());
    }

    private void setSwitches() {
        boolean isIPv6Enabled = droplet.getEnableIpv6() == null ? false : droplet.getEnableIpv6();
        boolean isPrivateNetworkEnabled = droplet.getEnablePrivateNetworking() == null ? false : droplet.getEnablePrivateNetworking();
        boolean isBackupEnabled = droplet.getEnableBackup() == null ? false : droplet.getEnableBackup();

        binding.switchIpv6.setChecked(isIPv6Enabled);
        binding.switchPrivateNetwork.setChecked(isPrivateNetworkEnabled);
        binding.switchBackup.setChecked(isBackupEnabled);
    }

    private void setSwitchWithoutTriggering(SwitchCompat switchCompat, boolean newState) {
        switchCompat.setOnCheckedChangeListener(null);
        switchCompat.setChecked(newState);
        switchCompat.setOnCheckedChangeListener(this);
    }

    private void setIndividualFeatures(Droplet droplet) {
        droplet.setEnableIpv6(false);
        droplet.setEnablePrivateNetworking(false);
        droplet.setEnableBackup(false);
        for (String feature : droplet.getFeatures()) {
            if (feature.equals("backups"))
                droplet.setEnableBackup(true);
            else if (feature.equals("private_networking"))
                droplet.setEnablePrivateNetworking(true);
            else if (feature.equals("ipv6"))
                droplet.setEnableIpv6(true);
        }
    }

}