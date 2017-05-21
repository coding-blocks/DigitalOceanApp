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

public class DropletsAdapter extends RecyclerView.Adapter<DropletsAdapter.ViewHolder> implements View.OnClickListener {

    public List<Droplet> dropletList;
    private Context context;
    private int position;
    public static final String DROPLET_CLICKED_POSITION = "position";

    public DropletsAdapter(List<Droplet> droplets, Context context) {
        dropletList = droplets;
        this.context = context;
    }

    @Override
    public DropletsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_droplet, parent, false);
        itemView.setOnClickListener(this);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DropletsAdapter.ViewHolder holder, int position) {
        this.position = holder.getAdapterPosition();
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

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, DetailDropletActivity.class);
        Gson gson = new Gson();
        intent.putExtra("DROPLET", gson.toJson(dropletList.get(position), Droplet.class));
        intent.putExtra(DROPLET_CLICKED_POSITION, position);
        context.startActivity(intent);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView dropletStatusImage;
        TextView dropletName;
        TextView dropletRAM;
        TextView dropletSize;
        TextView ipAddress;
        TextView region;

        ViewHolder(View itemView) {
            super(itemView);
            dropletStatusImage = (ImageView) itemView.findViewById(R.id.dropletStatusImageView);
            dropletName = (TextView) itemView.findViewById(R.id.dropletName);
            dropletRAM = (TextView) itemView.findViewById(R.id.dropletRAM);
            dropletSize = (TextView) itemView.findViewById(R.id.dropletHDD);
            ipAddress = (TextView) itemView.findViewById(R.id.ipAddress);
            region = (TextView) itemView.findViewById(R.id.dropletRegion);
        }
    }
}
