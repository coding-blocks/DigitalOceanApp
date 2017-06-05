package in.tosc.digitaloceanapp.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import in.tosc.digitaloceanapp.R;
import in.tosc.digitaloceanapp.activities.DropletCreateActivity;
import in.tosc.digitaloceanapp.models.Datacenter;
import in.tosc.doandroidlib.objects.Image;

/**
 * Created by rishabhkhanna on 27/11/16.
 */

public class DataCenterAdapter extends RecyclerView.Adapter<DataCenterAdapter.DataCenterViewHolder> {

    private ArrayList<Datacenter.center> countriesList;
    private Context context;
    private int postion;
    private DataCenterViewHolder previousHolder = null;
    private static int selectedRegion = -1;
    public static final String TAG = "DataCenterAdapter";

    public DataCenterAdapter(ArrayList<Datacenter.center> countries, Context context, List<Image> imageList) {
        countriesList = countries;
        this.context = context;
    }

    @Override
    public DataCenterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_coutry, parent, false);
        return new DataCenterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final DataCenterViewHolder holder, final int position) {
        this.postion = holder.getAdapterPosition();
        String thisCountry = countriesList.get(position).getCity();
        int url = countriesList.get(position).getId();
        holder.countryName.setText(thisCountry);
        Picasso.with(context).load(url).resize(425, 220).into(holder.img);
        Log.d(TAG, "onBindViewHolder: ");

        if (DropletCreateActivity.getDroplet().getRegion() == null) {
            deselectRegion(position, holder);
        } else if (DropletCreateActivity.getDroplet().getRegion().getSlug().equals(countriesList.get(position).getRegion().getSlug())) {
            selectRegion(position, holder);
            previousHolder = holder;
            selectedRegion = position;
        } else {
            deselectRegion(position, holder);
        }

        holder.countryLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(Boolean) holder.countryCV.getTag()) {
                    if (previousHolder != null) {
                        deselectRegion(selectedRegion, previousHolder);
                    }
                    DropletCreateActivity.getDroplet().setRegion(countriesList.get(position).getRegion());
                    selectRegion(position, holder);
                    previousHolder = holder;
                    selectedRegion = position;
                } else {
                    deselectRegion(position, holder);
                    previousHolder = null;
                    DropletCreateActivity.getDroplet().setRegion(null);
                    selectedRegion = -1;
                }
            }
        });

    }

    private void selectRegion(int position, DataCenterViewHolder holder) {
        holder.countryLayout.setBackgroundColor(Color.argb(60, 0, 90, 230));
        holder.countryCV.setBackgroundColor(Color.argb(60, 0, 90, 230));
        holder.countryCV.setTag(true);
        Log.i(TAG, "selectRegion:" + countriesList.get(position).getCity() + " Selected");
    }

    private void deselectRegion(int position, DataCenterViewHolder holder) {
        holder.countryLayout.setBackgroundColor(Color.WHITE);
        holder.countryCV.setBackgroundColor(Color.WHITE);
        holder.countryCV.setTag(false);
    }


    @Override
    public int getItemCount() {
        return countriesList.size();
    }

    class DataCenterViewHolder extends RecyclerView.ViewHolder {

        TextView countryName;
        ImageView img;
        LinearLayout countryLayout;
        CardView countryCV;

        public DataCenterViewHolder(View itemView) {
            super(itemView);
            countryName = (TextView) itemView.findViewById(R.id.countryName);
            img = (ImageView) itemView.findViewById(R.id.country_url);
            countryLayout = (LinearLayout) itemView.findViewById(R.id.countryLayout);
            countryCV = (CardView) itemView.findViewById(R.id.countryCardView);
        }
    }
}
