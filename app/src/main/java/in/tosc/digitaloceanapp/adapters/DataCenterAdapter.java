package in.tosc.digitaloceanapp.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import in.tosc.digitaloceanapp.Interfaces.onItemSelectNewDroplet;


import com.squareup.picasso.Picasso;

import org.apache.commons.lang3.ClassUtils;

import java.util.ArrayList;

import in.tosc.digitaloceanapp.R;
import in.tosc.digitaloceanapp.models.Datacenter;
import in.tosc.doandroidlib.objects.Region;
import in.tosc.doandroidlib.objects.Regions;

/**
 * Created by rishabhkhanna on 27/11/16.
 */

public class DataCenterAdapter extends RecyclerView.Adapter<DataCenterAdapter.DataCenterViewHolder> {

    private Regions regions;
    private Context context;
    private int postion;
    onItemSelectNewDroplet NewDropletSelect;
    private static final String TAG = "DataCenterAdapter";
    public DataCenterAdapter(Regions regions , Context context, onItemSelectNewDroplet newDropletSelect) {
        this.regions = regions;
        this.context = context;
        this.NewDropletSelect = newDropletSelect;

    }

    @Override
    public DataCenterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_coutry , parent , false);
        return new DataCenterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DataCenterViewHolder holder, int position) {
        this.postion = holder.getAdapterPosition();
        String thisCountry = regions.getRegions().get(position).getName();
        holder.countryName.setText(thisCountry);
        if(thisCountry.contains("New York"))
        {
            holder.img.setImageResource(R.drawable.murrica);
        }
        else if(thisCountry.contains("San Francisco"))
        {
            holder.img.setImageResource(R.drawable.murrica);
        }
        else if(thisCountry.contains("Amsterdam"))
        {
            holder.img.setImageResource(R.drawable.amsterdam);
        }
        else if(thisCountry.contains("Singapore"))
        {
            holder.img.setImageResource(R.drawable.singapore);
        }
        else if(thisCountry.contains("London"))
        {
            holder.img.setImageResource(R.drawable.london);
        }
        else if(thisCountry.contains("Frankfurt"))
        {
            holder.img.setImageResource(R.drawable.frankfurt);
        }
        else if (thisCountry.contains("Bangalore"))
        {
            holder.img.setImageResource(R.drawable.india);
        }
        else if(thisCountry.contains("Toronto"))
        {
            holder.img.setImageResource(R.drawable.canada);
        }
    }

    @Override
    public int getItemCount() {
        return regions.getRegions().size();
    }

    class DataCenterViewHolder extends  RecyclerView.ViewHolder{

        TextView countryName;
        ImageView img;
        CardView card;

        public DataCenterViewHolder(View itemView) {
            super(itemView);
            countryName = (TextView) itemView.findViewById(R.id.countryName);
            img = (ImageView) itemView.findViewById(R.id.country_url);
            card = (CardView) itemView.findViewById(R.id.country_cardview);
            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getLayoutPosition();
                    Log.i(TAG, "onClick: " + regions.getRegions().get(position).getName());
                    NewDropletSelect.onDataCenterSelect(regions.getRegions().get(position));
                }
            });
        }
    }


}
