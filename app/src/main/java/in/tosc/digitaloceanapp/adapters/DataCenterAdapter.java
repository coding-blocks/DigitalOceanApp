package in.tosc.digitaloceanapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import in.tosc.digitaloceanapp.R;
import in.tosc.doandroidlib.objects.Regions;

/**
 * Created by rishabhkhanna on 27/11/16.
 */

public class DataCenterAdapter extends RecyclerView.Adapter<DataCenterAdapter.DataCenterViewHolder> {

    private Regions regions;
    private Context context;
    private int postion;


    public DataCenterAdapter(Regions regions, Context context) {
        this.regions = regions;
        this.context = context;
    }

    @Override
    public DataCenterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_coutry , parent , false);
        return new DataCenterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DataCenterViewHolder holder, int position) {
        this.postion = holder.getAdapterPosition();
        String thisRegion = regions.getRegions().get(position).getName();
        holder.countryName.setText(thisRegion);
        if(thisRegion.contains("New York"))
        {
            holder.img.setImageResource(R.drawable.murrica);
        }
        else if(thisRegion.contains("San Francisco"))
        {
            holder.img.setImageResource(R.drawable.murrica);
        }
        else if(thisRegion.contains("Amsterdam"))
        {
            holder.img.setImageResource(R.drawable.amsterdam);
        }
        else if(thisRegion.contains("Singapore"))
        {
            holder.img.setImageResource(R.drawable.singapore);
        }
        else if(thisRegion.contains("London"))
        {
            holder.img.setImageResource(R.drawable.london);
        }
        else if(thisRegion.contains("Frankfurt"))
        {
            holder.img.setImageResource(R.drawable.frankfurt);
        }
        else if (thisRegion.contains("Bangalore"))
        {
            holder.img.setImageResource(R.drawable.india);
        }
        else if(thisRegion.contains("Toronto"))
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

        public DataCenterViewHolder(View itemView) {
            super(itemView);
            countryName = (TextView) itemView.findViewById(R.id.countryName);
            img = (ImageView) itemView.findViewById(R.id.country_url);
        }
    }
}
