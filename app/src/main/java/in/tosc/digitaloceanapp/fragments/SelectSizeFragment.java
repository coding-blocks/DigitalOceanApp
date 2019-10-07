package in.tosc.digitaloceanapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import in.tosc.digitaloceanapp.databinding.FragmentSizeBinding;
import android.view.ViewGroup;

import android.databinding.DataBindingUtil;
import java.util.List;

import in.tosc.digitaloceanapp.R;
import in.tosc.digitaloceanapp.adapters.SelectSizeAdapter;
import in.tosc.doandroidlib.DigitalOcean;
import in.tosc.doandroidlib.api.DigitalOceanClient;
import in.tosc.doandroidlib.objects.Size;
import in.tosc.doandroidlib.objects.Sizes;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by the-dagger on 11/26/2016.
 */

public class SelectSizeFragment extends Fragment{

    private List<Size> sizeList;
    private SelectSizeAdapter selectSizeAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
       final FragmentSizeBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_size, container, false);

        DigitalOceanClient doClient = DigitalOcean.getDOClient(getContext().getSharedPreferences("DO", MODE_PRIVATE).getString("authToken",null));
        binding.recyclerViewSize.setLayoutManager(new GridLayoutManager(getContext(),1));
        binding.recyclerViewSize.setAdapter(selectSizeAdapter);
        doClient.getSizes().enqueue(new Callback<Sizes>() {
            @Override
            public void onResponse(Call<Sizes> call, Response<Sizes> response) {
                sizeList = response.body().getSizes();
                selectSizeAdapter = new SelectSizeAdapter(sizeList, getContext());
                binding.recyclerViewSize.setAdapter(selectSizeAdapter);
                Log.e("Sizes fetched", String.valueOf(sizeList.size()));
            }

            @Override
            public void onFailure(Call<Sizes> call, Throwable t) {
                Log.e("Failed getting sizes",t.getLocalizedMessage());
            }
        });

        return binding.getRoot();

    }
}
