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
import android.view.Menu;
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
import in.tosc.digitaloceanapp.utils.FontsOverride;
import in.tosc.digitaloceanapp.interfaces.onDropletNameChange;
import in.tosc.doandroidlib.DigitalOcean;
import in.tosc.doandroidlib.api.DigitalOceanClient;
import in.tosc.doandroidlib.objects.Account;
import in.tosc.doandroidlib.objects.Droplet;
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
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView dropletRecyclerView;
    static DigitalOceanClient doClient;

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
        doClient.getAccount().enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                String email = response.body().getEmail();
                ((TextView) drawer.findViewById(R.id.accountEmail)).setText(email);
                ImageView profilePic = ((ImageView) drawer.findViewById(R.id.accountPic));
                Picasso.with(DropletActivity.this).load("https://www.gravatar.com/avatar/" + md5(email)).into(profilePic);

            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                Log.e("Failed to get email", t.getLocalizedMessage());
            }
        });
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    public static void refreshData() {

        doClient.getDroplets(1, 10).enqueue(new Callback<List<Droplet>>() {
            @Override
            public void onResponse(Call<List<Droplet>> call, Response<List<Droplet>> response) {
                droplets.clear();
                droplets.addAll(response.body());
                dropletsAdapter.notifyDataSetChanged();
                Log.e("Droplets fetched", String.valueOf(response.body().size()));
            }

            @Override
            public void onFailure(Call<List<Droplet>> call, Throwable t) {
                droplets = null;
                Log.e("Failed to get Droplets", t.getMessage());
            }
        });
    }

    public static void refreshModifiedData(final onDropletNameChange onDropletNameChange) {

        doClient.getDroplets(1, 10).enqueue(new Callback<List<Droplet>>() {
            @Override
            public void onResponse(Call<List<Droplet>> call, Response<List<Droplet>> response) {
                droplets.clear();
                droplets.addAll(response.body());
                dropletsAdapter.notifyDataSetChanged();
                Log.e("Droplets fetched", String.valueOf(response.body().size()));
                onDropletNameChange.onSuccess(droplets);
            }

            @Override
            public void onFailure(Call<List<Droplet>> call, Throwable t) {
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
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.droplet, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
            Intent i=new Intent(this,SplashActivity.class);
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
