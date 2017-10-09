package in.tosc.digitaloceanapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;

import in.tosc.digitaloceanapp.R;
import in.tosc.digitaloceanapp.activities.DetailDropletActivity;
import in.tosc.doandroidlib.objects.Droplet;

/**
 * Created by the-dagger on 11/26/2016.
 */

public class DropletsAdapter extends RecyclerView.Adapter<DropletsAdapter.ViewHolder>{

    public List<Droplet> dropletList;
    private Context context;
    public static final String DROPLET_CLICKED_POSITION = "position";

    public DropletsAdapter(List<Droplet> droplets, Context context) {
        dropletList = droplets;
        this.context = context;
    }

    @Override
    public DropletsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_droplet, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DropletsAdapter.ViewHolder holder, int position) {
        Droplet droplet = dropletList.get(position);
        if (droplet.getStatus().toString().equals("active")) {

        }
        holder.region.setText(droplet.getRegion().getName());
        holder.ipAddress.setText(droplet.getNetworks().getVersion4Networks().get(0).getIpAddress());
        holder.dropletSize.setText(String.format(context.getResources().getString(R.string.droplet_disk_size), String.valueOf(droplet.getDiskSize())));
        holder.dropletRAM.setText(String.format(context.getResources().getString(R.string.droplet_memory), String.valueOf(droplet.getMemorySizeInMb())));
        holder.dropletName.setText(droplet.getName());
    }

    @Override
    public int getItemCount() {
        if (dropletList == null)
            return 0;
        return dropletList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView dropletStatusImage;
        TextView dropletName;
        TextView dropletRAM;
        TextView dropletSize;
        TextView ipAddress;
        TextView region;

        ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            dropletStatusImage = (ImageView) itemView.findViewById(R.id.dropletStatusImageView);
            dropletName = (TextView) itemView.findViewById(R.id.dropletName);
            dropletRAM = (TextView) itemView.findViewById(R.id.dropletRAM);
            dropletSize = (TextView) itemView.findViewById(R.id.dropletHDD);
            ipAddress = (TextView) itemView.findViewById(R.id.ipAddress);
            region = (TextView) itemView.findViewById(R.id.dropletRegion);
        }
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, DetailDropletActivity.class);
            Gson gson = new Gson();
            intent.putExtra("DROPLET", gson.toJson(dropletList.get(getAdapterPosition()), Droplet.class));
            intent.putExtra(DROPLET_CLICKED_POSITION, getAdapterPosition());
            context.startActivity(intent);
        }
    }
}
