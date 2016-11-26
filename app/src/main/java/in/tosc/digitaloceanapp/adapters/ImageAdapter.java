package in.tosc.digitaloceanapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import in.tosc.digitaloceanapp.R;
import in.tosc.doandroidlib.objects.Image;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> implements View.OnClickListener{

    private final List<Image> imageList;

    public ImageAdapter(List<Image> items) {
        imageList = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_image_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.imageName.setText(imageList.get(position).getName());
        holder.imageDistribution.setText(imageList.get(position).getDistribution());
        //TODO : Add images for each image name
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    @Override
    public void onClick(View v) {

    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageImage;
        TextView imageName;
        TextView imageDistribution;

        ViewHolder(View view) {
            super(view);
            imageImage = (ImageView) view.findViewById(R.id.imageImage);
            imageName = (TextView) view.findViewById(R.id.imageName);
            imageDistribution = (TextView) view.findViewById(R.id.imageDistribution);
            }
    }
}
