package in.tosc.digitaloceanapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.databinding.DataBindingUtil;
import android.view.ViewGroup;

import in.tosc.digitaloceanapp.databinding.FragmentImageBinding;
import java.util.List;

import in.tosc.digitaloceanapp.R;
import in.tosc.digitaloceanapp.adapters.ImageAdapter;
import in.tosc.doandroidlib.DigitalOcean;
import in.tosc.doandroidlib.api.DigitalOceanClient;
import in.tosc.doandroidlib.objects.Image;
import in.tosc.doandroidlib.objects.Images;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class SelectImageFragment extends Fragment {

    public static List<Image> imageList;
    ImageAdapter imageAdapter;

    public SelectImageFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final FragmentImageBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_image, container, false);

        DigitalOceanClient doClient = DigitalOcean.getDOClient(getContext().getSharedPreferences("DO", MODE_PRIVATE).getString("authToken", null));
        binding.imageRecyclerVIew.setLayoutManager(new GridLayoutManager(getContext(), 3));
        binding.imageRecyclerVIew.setAdapter(imageAdapter);

        doClient.getImages(1, 100, "distribution").enqueue(new Callback<Images>() {
            @Override
            public void onResponse(Call<Images> call, Response<Images> response) {
                imageList = response.body().getImages();
                imageAdapter = new ImageAdapter(imageList, getContext());
                binding.imageRecyclerVIew.setAdapter(imageAdapter);
                Log.e("Droplets fetched", String.valueOf(imageList.size()));
            }

            @Override
            public void onFailure(Call<Images> call, Throwable t) {
                Log.e("Failed getting images", t.getLocalizedMessage());
            }
        });

        return binding.getRoot();
    }
}
