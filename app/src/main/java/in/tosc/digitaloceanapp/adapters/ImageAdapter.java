package in.tosc.digitaloceanapp.adapters;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import in.tosc.digitaloceanapp.activities.DropletCreateActivity;
import in.tosc.digitaloceanapp.R;
import in.tosc.doandroidlib.objects.Image;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> implements View.OnClickListener {

    private final List<Image> imageList;
    private int position;
    private Context context;

    public ImageAdapter(List<Image> items, Context context)
    {
        imageList = items;
        this.context = context;
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
        this.position = holder.getAdapterPosition();
        holder.imageDistribution.setText(imageList.get(position).getDistribution());
        switch (imageList.get(position).getDistribution()){
            case "CoreOS":
                holder.imageImage.setBackground(ContextCompat.getDrawable(context,R.drawable.coreos));
                break;
            case "FreeBSD":
                holder.imageImage.setBackground(ContextCompat.getDrawable(context,R.drawable.freebsd));
                break;
            case "Fedora":
                holder.imageImage.setBackground(ContextCompat.getDrawable(context,R.drawable.fedora));
                break;
            case "Debian":
                holder.imageImage.setBackground(ContextCompat.getDrawable(context,R.drawable.debian));
                break;
            case "CentOS":
                holder.imageImage.setBackground(ContextCompat.getDrawable(context,R.drawable.centos));
                break;
            case "Ubuntu":
                holder.imageImage.setBackground(ContextCompat.getDrawable(context,R.drawable.ubuntu));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        v.setBackground(context.getDrawable(R.drawable.selector));
        DropletCreateActivity.getDroplet().setImage(imageList.get(this.position));
        Log.d("Touched", "Chhua Chhua");

        Log.e("OnClick",imageList.get(this.position).getDistribution());
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
