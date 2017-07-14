package in.tosc.digitaloceanapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.tosc.digitaloceanapp.R;
import in.tosc.digitaloceanapp.adapters.DataCenterAdapter;
import in.tosc.doandroidlib.DigitalOcean;
import in.tosc.doandroidlib.api.DigitalOceanClient;
import in.tosc.doandroidlib.objects.Regions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class DataCenterFragment extends Fragment {

    private static final String TAG = "DataCenterFragment";
    DataCenterAdapter dataCenterAdapter;


    public DataCenterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_select_data_center, container, false);
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.datacenter_recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        DigitalOceanClient doClient = DigitalOcean.getDOClient(getContext().getSharedPreferences("DO", MODE_PRIVATE).getString("authToken", null));
        recyclerView.setAdapter(dataCenterAdapter);
        doClient.getRegions().enqueue(new Callback<Regions>() {
            @Override
            public void onResponse(Call<Regions> call, Response<Regions> response) {
                Regions regions = response.body();
                DataCenterAdapter dataCenterAdapter = new DataCenterAdapter(regions, getActivity());
                recyclerView.setAdapter(dataCenterAdapter);
                dataCenterAdapter.notifyDataSetChanged();
                Log.i(TAG, "onResponse: " + regions.getRegions().size() + " Regions Fetched Successfully!");
            }

            @Override
            public void onFailure(Call<Regions> call, Throwable t) {
                Log.e(TAG, "onFailure: Unable to oad Regions ", t);
            }
        });

        return view;
    }


}
