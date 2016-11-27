package in.tosc.digitaloceanapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import in.tosc.digitaloceanapp.R;
import in.tosc.doandroidlib.objects.Size;

/**
 * Created by the-dagger on 11/27/2016.
 */

public class SelectSizeAdapter extends RecyclerView.Adapter<SelectSizeAdapter.ViewHolder> implements View.OnClickListener {

    List<Size> sizeList;
    Context context;

    public SelectSizeAdapter(List<Size> sizeList, Context context){
        this.sizeList = sizeList;
        this.context = context;
    }

    @Override
    public SelectSizeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_size,parent,false);
        itemView.setOnClickListener(this);
        return new SelectSizeAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SelectSizeAdapter.ViewHolder holder, int position) {

        holder.monthlyPrice.setText(String.format(context.getString(R.string.monthly_price), sizeList.get(position).getPriceMonthly().toString()));
        holder.hourlyPrice.setText(String.format(context.getString(R.string.hourly_price), sizeList.get(position).getPriceHourly().toString()));
        holder.memory.setText(String.format(context.getString(R.string.memory), sizeList.get(position).getMemorySizeInMb().toString(), sizeList.get(position).getVirutalCpuCount().toString()));
        holder.diskSpace.setText(String.format(context.getString(R.string.disk_space), sizeList.get(position).getDiskSize().toString()));
        holder.transfer.setText(String.format(context.getString(R.string.transfer), sizeList.get(position).getTransfer().toString()));
    }

    @Override
    public int getItemCount() {
        return sizeList.size();
    }

    @Override
    public void onClick(View v) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView monthlyPrice, hourlyPrice, memory, diskSpace, transfer;
        View view;
        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            monthlyPrice = (TextView) itemView.findViewById(R.id.monthly_price);
            hourlyPrice = (TextView) itemView.findViewById(R.id.hourly_price);
            memory = (TextView) itemView.findViewById(R.id.memory_space);
            diskSpace = (TextView) itemView.findViewById(R.id.disk_space);
            transfer = (TextView) itemView.findViewById(R.id.transfer);
        }
    }
}
