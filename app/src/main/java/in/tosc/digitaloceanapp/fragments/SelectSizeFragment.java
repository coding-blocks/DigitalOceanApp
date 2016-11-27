package in.tosc.digitaloceanapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import in.tosc.digitaloceanapp.R;
import in.tosc.digitaloceanapp.adapters.ImageAdapter;
import in.tosc.digitaloceanapp.adapters.SelectSizeAdapter;
import in.tosc.doandroidlib.DigitalOcean;
import in.tosc.doandroidlib.api.DigitalOceanClient;
import in.tosc.doandroidlib.objects.Image;
import in.tosc.doandroidlib.objects.Size;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by the-dagger on 11/26/2016.
 */

public class SelectSizeFragment extends Fragment{

    private List<Size> sizeList;
    private RecyclerView recyclerView;
    private SelectSizeAdapter selectSizeAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_size, container, false);

        DigitalOceanClient doClient = DigitalOcean.getDOClient();
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_size);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
        recyclerView.setAdapter(selectSizeAdapter);
        doClient.getSizes().enqueue(new Callback<List<Size>>() {
            @Override
            public void onResponse(Call<List<Size>> call, Response<List<Size>> response) {
                sizeList = response.body();
                selectSizeAdapter = new SelectSizeAdapter(sizeList, getContext());
                recyclerView.setAdapter(selectSizeAdapter);
                Log.e("Sizes fetched", String.valueOf(sizeList.size()));
            }

            @Override
            public void onFailure(Call<List<Size>> call, Throwable t) {
                Log.e("Failed getting sizes",t.getLocalizedMessage());
            }
        });
        return view;

    }
}
