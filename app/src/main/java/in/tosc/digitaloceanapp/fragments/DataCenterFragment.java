package in.tosc.digitaloceanapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import in.tosc.digitaloceanapp.R;
import in.tosc.digitaloceanapp.adapters.DataCenterAdapter;
import in.tosc.digitaloceanapp.models.Datacenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class DataCenterFragment extends Fragment {
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
        View view = inflater.inflate(R.layout.fragment_select_data_center, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.datacenter_recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        dataCenterAdapter = new DataCenterAdapter(countries, getActivity(), SelectImageFragment.imageList);
        recyclerView.setAdapter(dataCenterAdapter);
        dataCenterAdapter.notifyDataSetChanged();
        return view;
    }


}
