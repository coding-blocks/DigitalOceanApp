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

import in.tosc.digitaloceanapp.R;
import in.tosc.digitaloceanapp.activities.DropletCreateActivity;
import in.tosc.doandroidlib.objects.Regions;


/**
 * Created by rishabhkhanna on 27/11/16.
 */

public class DataCenterAdapter extends RecyclerView.Adapter<DataCenterAdapter.DataCenterViewHolder> {

    public static final String TAG = "DataCenterAdapter";
    private static int selectedRegion = -1;
    private Regions regions;
    private Context context;
    private int postion;
    private DataCenterViewHolder previousHolder = null;


    public DataCenterAdapter(Regions regions, Context context) {
        this.regions = regions;
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
        String thisRegion = regions.getRegions().get(position).getName();
        holder.countryName.setText(thisRegion);
        if (thisRegion.contains("New York")) {
            Picasso.with(context).load(R.drawable.murrica).resize(425, 220).into(holder.img);
        } else if (thisRegion.contains("San Francisco")) {
            Picasso.with(context).load(R.drawable.murrica).resize(425, 220).into(holder.img);
        } else if (thisRegion.contains("Amsterdam")) {
            Picasso.with(context).load(R.drawable.amsterdam).resize(425, 220).into(holder.img);
        } else if (thisRegion.contains("Singapore")) {
            Picasso.with(context).load(R.drawable.singapore).resize(425, 220).into(holder.img);
        } else if (thisRegion.contains("London")) {
            Picasso.with(context).load(R.drawable.london).resize(425, 220).into(holder.img);
        } else if (thisRegion.contains("Frankfurt")) {
            Picasso.with(context).load(R.drawable.frankfurt).resize(425, 220).into(holder.img);
        } else if (thisRegion.contains("Bangalore")) {
            Picasso.with(context).load(R.drawable.india).resize(425, 220).into(holder.img);
        } else if (thisRegion.contains("Toronto")) {
            Picasso.with(context).load(R.drawable.canada).resize(425, 220).into(holder.img);
        }
        if (DropletCreateActivity.getDroplet().getRegion() == null) {
            deselectRegion(position, holder);
        } else if (DropletCreateActivity.getDroplet().getRegion().getSlug().equals(regions.getRegions().get(position).getSlug())) {
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
                    DropletCreateActivity.getDroplet().setRegion(regions.getRegions().get(position));
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
        Log.i(TAG, "selectRegion:" + regions.getRegions().get(position).getSlug() + " Selected");
    }

    private void deselectRegion(int position, DataCenterViewHolder holder) {
        holder.countryLayout.setBackgroundColor(Color.WHITE);
        holder.countryCV.setBackgroundColor(Color.WHITE);
        holder.countryCV.setTag(false);
    }


    @Override
    public int getItemCount() {
        return regions.getRegions().size();
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
