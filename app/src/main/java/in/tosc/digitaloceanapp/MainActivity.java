package in.tosc.digitaloceanapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;

import in.tosc.digitaloceanapp.utils.FontsOverride;
import in.tosc.doandroidlib.DigitalOcean;
import in.tosc.doandroidlib.api.DigitalOceanClient;
import in.tosc.doandroidlib.api.DigitalOceanStatisticsClient;
import in.tosc.doandroidlib.common.ActionStatus;
import in.tosc.doandroidlib.common.ActionType;
import in.tosc.doandroidlib.common.PeriodType;
import in.tosc.doandroidlib.common.StatisticsType;
import in.tosc.doandroidlib.objects.Account;
import in.tosc.doandroidlib.objects.Action;
import in.tosc.doandroidlib.objects.Droplet;
import in.tosc.doandroidlib.objects.Statistics;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "DO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FontsOverride.applyFontForToolbarTitle(this, "ProximaNova.ttf");

        DigitalOceanClient doClient = DigitalOcean.getDOClient();
        final DigitalOceanStatisticsClient statClient = DigitalOcean.getDOStatsClient();



        doClient.getDroplets(1, 10).enqueue(new Callback<List<Droplet>>() {
            @Override
            public void onResponse(Call<List<Droplet>> call, Response<List<Droplet>> response) {
                Log.d(TAG, "onResponse: droplet 0 = " + response.raw().toString());
                Log.d(TAG, "onResponse: droplet 0 = " + response.body().get(0).getName());

            }

            @Override
            public void onFailure(Call<List<Droplet>> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });

        doClient.getAccount().enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                Log.d(TAG, "onResponse: email = " + response.body().getEmail());
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);

            }
        });

//        statClient.getStats(10222412, StatisticsType.BANDWIDTH, PeriodType.HOUR)
//                .enqueue(new Callback<Statistics>() {
//                    @Override
//                    public void onResponse(Call<Statistics> call, Response<Statistics> response) {
//                        Log.d(TAG, "onResponse: " + response.raw().toString());
//                        Log.d(TAG, "onResponse: " + response.body().getStatistics().get(0).getData().size());
//                        Log.d(TAG, "onResponse: " + response.body().getStatistics().get(1).getData().size());
//                    }
//
//                    @Override
//                    public void onFailure(Call<Statistics> call, Throwable t) {
//
//                    }
//                });

    }
}
