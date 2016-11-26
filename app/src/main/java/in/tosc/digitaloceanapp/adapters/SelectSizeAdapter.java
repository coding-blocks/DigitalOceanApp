package in.tosc.digitaloceanapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    }

    @Override
    public int getItemCount() {
        return sizeList.size();
    }

    @Override
    public void onClick(View v) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
