package in.tosc.digitaloceanapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import in.tosc.digitaloceanapp.Interfaces.onItemSelectNewDroplet;
import in.tosc.digitaloceanapp.R;
import in.tosc.digitaloceanapp.adapters.DataCenterAdapter;
import in.tosc.digitaloceanapp.models.Datacenter;
import in.tosc.doandroidlib.DigitalOcean;
import in.tosc.doandroidlib.api.DigitalOceanClient;
import in.tosc.doandroidlib.objects.Region;
import in.tosc.doandroidlib.objects.Regions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class DataCenterFragment extends Fragment{

    Regions regions;
    DataCenterAdapter dataCenterAdapter;
    private static final String TAG = "DataCenterFragment";

    public DataCenterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ArrayList<Datacenter.center> countries = Datacenter.getCenter();
        View view =  inflater.inflate(R.layout.fragment_select_data_center, container, false);
        DigitalOceanClient doClient = DigitalOcean.getDOClient(getContext().getSharedPreferences("DO", MODE_PRIVATE).getString("authToken",null));
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.datacenter_recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setAdapter(dataCenterAdapter);
        //Call for getting Regions
        doClient.getRegions().enqueue(new Callback<Regions>() {
            @Override
            public void onResponse(Call<Regions> call, Response<Regions> response) {
                regions = response.body();
                dataCenterAdapter = new DataCenterAdapter(regions,getContext());
                recyclerView.setAdapter(dataCenterAdapter);
                dataCenterAdapter.notifyDataSetChanged();
                Log.i(TAG, "onResponse: Regions loaded successfully!");
            }

            @Override
            public void onFailure(Call<Regions> call, Throwable t) {
                Log.i(TAG, "onFailure: Failed to load Regions " + t.getLocalizedMessage());

            }
        });
        return view;
    }

    //Accessor function to get selected Region
    public Region getRegion()
    {
        return dataCenterAdapter.getSelectedRegion();
    }


}
