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
import in.tosc.doandroidlib.objects.Image;
import in.tosc.doandroidlib.objects.Region;
import in.tosc.doandroidlib.objects.Regions;

/**
 * Created by rishabhkhanna on 27/11/16.
 */

public class DataCenterAdapter extends RecyclerView.Adapter<DataCenterAdapter.DataCenterViewHolder> {

    private Regions regions;
    private boolean selected = false;
    private Context context;
    private int position;
    private DataCenterViewHolder previousSelectedViewHolder = null;
    private Region selectedRegion = null;
    private static final String TAG = "DataCenterAdapter";
    public DataCenterAdapter(Regions regions , Context context) {
        this.regions = regions;
        this.context = context;
    }

    @Override
    public DataCenterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_coutry , parent , false);
        return new DataCenterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final DataCenterViewHolder holder, final int position) {
        this.position = holder.getAdapterPosition();
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

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selected)
                {
                    if (previousSelectedViewHolder == holder)
                    {
                        holder.selectedIcon.setVisibility(View.INVISIBLE);
                        selected = false;
                        previousSelectedViewHolder = null;
                        selectedRegion = null;
                    }
                    else {
                        previousSelectedViewHolder.selectedIcon.setVisibility(View.INVISIBLE);
                        holder.selectedIcon.setVisibility(View.VISIBLE);
                        previousSelectedViewHolder = holder;
                        selectedRegion = regions.getRegions().get(position);
                    }
                }
                else
                {
                    holder.selectedIcon.setVisibility(View.VISIBLE);
                    selected = true;
                    previousSelectedViewHolder = holder;
                    selectedRegion = regions.getRegions().get(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return regions.getRegions().size();
    }


    //Accessor Function to get Selected Region
    public Region getSelectedRegion()
    {

        return selectedRegion;
    }

    class DataCenterViewHolder extends  RecyclerView.ViewHolder{

        TextView countryName;
        ImageView img;
        CardView card;
        ImageView selectedIcon;

        public DataCenterViewHolder(View itemView) {
            super(itemView);
            countryName = (TextView) itemView.findViewById(R.id.countryName);
            img = (ImageView) itemView.findViewById(R.id.country_url);
            card = (CardView) itemView.findViewById(R.id.country_cardview);
            selectedIcon = (ImageView) itemView.findViewById(R.id.selected_icon);
        }
    }


}
