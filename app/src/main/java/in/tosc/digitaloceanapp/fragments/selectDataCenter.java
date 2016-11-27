package in.tosc.digitaloceanapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import in.tosc.digitaloceanapp.R;
import in.tosc.digitaloceanapp.adapters.DataCenterAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class selectDataCenter extends Fragment {


    public selectDataCenter() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ArrayList<String> countries = new ArrayList<>();
        countries.add("Bangalore");
        countries.add("San Francisco");
        View view =  inflater.inflate(R.layout.fragment_select_data_center, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.datacenter_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        DataCenterAdapter dataCenterAdapter = new DataCenterAdapter(countries , getActivity());
        recyclerView.setAdapter(dataCenterAdapter);
        dataCenterAdapter.notifyDataSetChanged();
        return view;
    }


}
