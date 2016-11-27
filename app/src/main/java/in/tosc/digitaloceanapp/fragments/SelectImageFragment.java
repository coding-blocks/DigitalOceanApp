package in.tosc.digitaloceanapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import in.tosc.digitaloceanapp.R;
import in.tosc.digitaloceanapp.adapters.ImageAdapter;
import in.tosc.doandroidlib.DigitalOcean;
import in.tosc.doandroidlib.api.DigitalOceanClient;
import in.tosc.doandroidlib.objects.Droplet;
import in.tosc.doandroidlib.objects.Image;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectImageFragment extends Fragment {

    public Droplet createdDroplet;

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    List<Image> imageList;
    ImageAdapter imageAdapter;
    RecyclerView imageRecyclerView;

    public SelectImageFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image, container, false);
        createdDroplet = new Droplet();
        DigitalOceanClient doClient = DigitalOcean.getDOClient();
        imageRecyclerView = (RecyclerView) view.findViewById(R.id.imageRecyclerVIew);
        imageRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        imageRecyclerView.setAdapter(imageAdapter);
                doClient.getImages(1,100,"distribution").enqueue(new Callback<List<Image>>() {
                    @Override
                    public void onResponse(Call<List<Image>> call, Response<List<Image>> response) {
                        imageList = response.body();
                        imageAdapter = new ImageAdapter(imageList);
                        imageRecyclerView.setAdapter(imageAdapter);
                        Log.e("Droplets fetched", String.valueOf(imageList.size()));
                    }

                    @Override
                    public void onFailure(Call<List<Image>> call, Throwable t) {
                        Log.e("Failed getting images",t.getLocalizedMessage());
                    }
                });
        return view;
    }
}
