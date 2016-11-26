package in.tosc.digitaloceanapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import in.tosc.digitaloceanapp.R;
import in.tosc.doandroidlib.objects.Droplet;

/**
 * Created by the-dagger on 11/26/2016.
 */

public class DropletsAdapter extends RecyclerView.Adapter<DropletsAdapter.ViewHolder> implements View.OnClickListener {

    private List<Droplet> dropletList;
    private Context contex;
    private int position;

    public DropletsAdapter(List<Droplet> dropletList, Context context){
        this.dropletList = dropletList;
        this.contex = context;
    }

    @Override
    public DropletsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_droplet,parent,false);
        itemView.setOnClickListener(this);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DropletsAdapter.ViewHolder holder, int position) {
        this.position = holder.getAdapterPosition();
        Droplet droplet = dropletList.get(position);
        if(droplet.getStatus().toString().equals("active")){

        }
        holder.region.setText(droplet.getRegion().getName());
        holder.ipAddress.setText(droplet.getNetworks().getVersion4Networks().get(0).getIpAddress());
        holder.dropletSize.setText(droplet.getDiskSize());
        holder.dropletRAM.setText(droplet.getMemorySizeInMb());
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

    }

    class ViewHolder extends RecyclerView.ViewHolder{
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
