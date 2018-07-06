package in.tosc.digitaloceanapp.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.customtabs.CustomTabsIntent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import in.tosc.digitaloceanapp.R;
import in.tosc.digitaloceanapp.adapters.DropletsAdapter;
import in.tosc.digitaloceanapp.databinding.ActivityDropletBinding;
import in.tosc.digitaloceanapp.interfaces.OnDropletNameChange;
import in.tosc.digitaloceanapp.utils.FontsOverride;
import in.tosc.doandroidlib.DigitalOcean;
import in.tosc.doandroidlib.api.DigitalOceanClient;
import in.tosc.doandroidlib.objects.AccountInfo;
import in.tosc.doandroidlib.objects.Droplet;
import in.tosc.doandroidlib.objects.Droplets;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by the-dagger on 11/26/2016.
 */

public class DropletActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    static List<Droplet> droplets;
    static DropletsAdapter dropletsAdapter;
    static DigitalOceanClient doClient;
    private ActivityDropletBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FontsOverride.applyFontForToolbarTitle(this, FontsOverride.FONT_PROXIMA_NOVA);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_droplet);
        setSupportActionBar(binding.appBarDroplet.toolbar);
        binding.appBarDroplet.contentDroplet.dropletsRv.setLayoutManager(
                new LinearLayoutManager(DropletActivity.this,
                        LinearLayoutManager.VERTICAL, false));
        droplets = new ArrayList<>();
        dropletsAdapter = new DropletsAdapter(droplets, DropletActivity.this);
        binding.appBarDroplet.contentDroplet.dropletsRv.setAdapter(dropletsAdapter);
        doClient = DigitalOcean.getDOClient(getSharedPreferences("DO", MODE_PRIVATE).getString("authToken", null));
        refreshData();

        binding.appBarDroplet.contentDroplet.swipeRefresh.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        refreshData();
                        binding.appBarDroplet.contentDroplet.swipeRefresh.setRefreshing(false);
                    }
                });

        binding.appBarDroplet.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DropletActivity.this, DropletCreateActivity.class);
                startActivity(intent);
            }
        });
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                binding.drawerLayout,
                binding.appBarDroplet.toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        binding.drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        doClient.getAccount().enqueue(new Callback<AccountInfo>() {
            @Override
            public void onResponse(@NonNull Call<AccountInfo> call,
                                   @NonNull Response<AccountInfo> response) {
                String email = null;
                if (response.isSuccessful() && response.body() != null) {
                    email = response.body().getAccount().getEmail();
                    if (binding.drawerLayout.findViewById(R.id.accountEmail) != null) {
                        ((TextView) binding.drawerLayout.findViewById(R.id.accountEmail))
                                .setText(email);
                    }
                    ImageView profilePic = binding.drawerLayout.findViewById(R.id.accountPic);

                    if (profilePic != null && email != null && !email.isEmpty()) {
                        Picasso.with(DropletActivity.this).load("https://www.gravatar.com/avatar/" + md5(email)).into(profilePic);
                    }
                }
            }

            @Override
            public void onFailure(Call<AccountInfo> call, Throwable t) {
                Log.e("Failed to get email", t.getLocalizedMessage());
            }
        });
        binding.navView.setNavigationItemSelectedListener(this);
    }


    public static void refreshData() {
        doClient.getDroplets(1, 10).enqueue(new Callback<Droplets>() {
            @Override
            public void onResponse(@NonNull Call<Droplets> call,
                                   @NonNull Response<Droplets> response) {
                droplets.clear();
                List<Droplet> dropletsDownloaded = null;
                if (response.isSuccessful() && response.body() != null) {
                    dropletsDownloaded = response.body().getDroplets();
                    for (Droplet droplet : dropletsDownloaded) {
                        if (droplet.isLocked()) {
                            dropletsDownloaded.remove(droplet);  //A locked droplet prevents any user actions
                        }
                    }
                    droplets.addAll(dropletsDownloaded);
                    dropletsAdapter.notifyDataSetChanged();
                    Log.e("Droplets fetched", String.valueOf(response.body().getDroplets().size()));
                }
                dropletsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Droplets> call, Throwable t) {
                droplets = null;
                Log.e("Failed to get Droplets", t.getMessage());
            }
        });
    }

    public static void refreshModifiedData(final OnDropletNameChange onDropletNameChange) {

        doClient.getDroplets(1, 10).enqueue(new Callback<Droplets>() {
            @Override
            public void onResponse(@NonNull Call<Droplets> call,
                                   @NonNull Response<Droplets> response) {
                droplets.clear();
                if (response.isSuccessful() && response.body() != null) {
                    droplets.addAll(response.body().getDroplets());
                    dropletsAdapter.notifyDataSetChanged();
                    Log.e("Droplets fetched", String.valueOf(response.body().getDroplets().size()));
                    onDropletNameChange.onSuccess(droplets);
                }
            }

            @Override
            public void onFailure(Call<Droplets> call, Throwable t) {
                droplets = null;
                Log.e("Failed to get Droplets", t.getMessage());
                onDropletNameChange.onError(t.getMessage());
            }
        });
    }

    public String md5(String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++)
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public void onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        String url;
        if (id == R.id.nav_profile) {
            url = "https://cloud.digitalocean.com/settings/profile";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
            // Handle the camera action
        } else if (id == R.id.nav_billing) {
            url = "https://cloud.digitalocean.com/settings/billing";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        } else if (id == R.id.nav_logout) {
            getSharedPreferences("DO", MODE_PRIVATE).edit().clear().commit();
            Intent i = new Intent(this, SplashActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
            finish();


        } else if (id == R.id.nav_manage) {
            url = "https://cloud.digitalocean.com/settings";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_about) {
            Intent i = new Intent(this, AboutActivity.class);
            startActivity(i);
        }

        binding.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void openCustomTab(String url) {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(url));
    }
}