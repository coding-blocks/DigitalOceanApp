package in.tosc.digitaloceanapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import in.tosc.digitaloceanapp.R;

/**
 * Created by rishabhkhanna on 27/11/16.
 */

public class DataCenterAdapter extends RecyclerView.Adapter<DataCenterAdapter.DataCenterViewHolder> {

    private ArrayList<String> countriesList;
    private Context context;
    private int postion;

    public DataCenterAdapter(ArrayList<String> countries , Context context) {
        countriesList = countries;
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
        String thisCountry = countriesList.get(position);
        holder.countryName.setText(thisCountry);

    }

    @Override
    public int getItemCount() {
        return countriesList.size();
    }

    class DataCenterViewHolder extends  RecyclerView.ViewHolder{

        TextView countryName;

        public DataCenterViewHolder(View itemView) {
            super(itemView);
            countryName = (TextView) itemView.findViewById(R.id.countryName);
        }
    }
}
