package in.tosc.digitaloceanapp.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import in.tosc.digitaloceanapp.R;
import in.tosc.digitaloceanapp.adapters.DropletsAdapter;
import in.tosc.digitaloceanapp.interfaces.OnDropletNameChange;
import in.tosc.digitaloceanapp.utils.FontsOverride;
import in.tosc.doandroidlib.DigitalOcean;
import in.tosc.doandroidlib.api.DigitalOceanClient;
import in.tosc.doandroidlib.objects.AccountInfo;
import in.tosc.doandroidlib.objects.Droplet;
import in.tosc.doandroidlib.objects.Droplets;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DropletActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    static List<Droplet> droplets;
    static DropletsAdapter dropletsAdapter;
    static DigitalOceanClient doClient;
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView dropletRecyclerView;

    public static void refreshData() {

        doClient.getDroplets(1, 10).enqueue(new Callback<Droplets>() {
            @Override
            public void onResponse(Call<Droplets> call, Response<Droplets> response) {
                droplets.clear();
                List<Droplet> dropletsDownloaded = response.body().getDroplets();
                for (Droplet droplet : dropletsDownloaded) {
                    if (droplet.isLocked()) {
                        dropletsDownloaded.remove(droplet);  //A locked droplet prevents any user actions
                    }
                }
                droplets.addAll(dropletsDownloaded);
                dropletsAdapter.notifyDataSetChanged();
                Log.e("Droplets fetched", String.valueOf(response.body().getDroplets().size()));
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
            public void onResponse(Call<Droplets> call, Response<Droplets> response) {
                droplets.clear();
                droplets.addAll(response.body().getDroplets());
                dropletsAdapter.notifyDataSetChanged();
                Log.e("Droplets fetched", String.valueOf(response.body().getDroplets().size()));
                onDropletNameChange.onSuccess(droplets);
            }

            @Override
            public void onFailure(Call<Droplets> call, Throwable t) {
                droplets = null;
                Log.e("Failed to get Droplets", t.getMessage());
                onDropletNameChange.onError(t.getMessage());
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FontsOverride.applyFontForToolbarTitle(this, FontsOverride.FONT_PROXIMA_NOVA);
        setContentView(R.layout.activity_droplet);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        dropletRecyclerView = (RecyclerView) findViewById(R.id.dropletsRv);
        dropletRecyclerView.setLayoutManager(new LinearLayoutManager(DropletActivity.this, LinearLayoutManager.VERTICAL, false));
        droplets = new ArrayList<>();
        dropletsAdapter = new DropletsAdapter(droplets, DropletActivity.this);
        dropletRecyclerView.setAdapter(dropletsAdapter);
        doClient = DigitalOcean.getDOClient(getSharedPreferences("DO", MODE_PRIVATE).getString("authToken", null));
        refreshData();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DropletActivity.this, DropletCreateActivity.class);
                startActivity(intent);
            }
        });
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        doClient.getAccount().enqueue(new Callback<AccountInfo>() {
            @Override
            public void onResponse(Call<AccountInfo> call, Response<AccountInfo> response) {
                String email = response.body().getAccount().getEmail();
                if (((TextView) drawer.findViewById(R.id.accountEmail)) != null) {
                    ((TextView) drawer.findViewById(R.id.accountEmail)).setText(email);
                }
                ImageView profilePic = ((ImageView) drawer.findViewById(R.id.accountPic));
                if (profilePic != null && email != null && !email.isEmpty()) {
                    Picasso.with(DropletActivity.this).load("https://www.gravatar.com/avatar/" + md5(email)).into(profilePic);
                }

            }

            @Override
            public void onFailure(Call<AccountInfo> call, Throwable t) {
                Log.e("Failed to get email", t.getLocalizedMessage());
            }
        });
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void openCustomTab(String url) {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(url));
    }
}
