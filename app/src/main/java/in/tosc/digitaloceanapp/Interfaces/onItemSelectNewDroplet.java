package in.tosc.digitaloceanapp.Interfaces;

import in.tosc.digitaloceanapp.models.Datacenter;
import in.tosc.doandroidlib.objects.Image;
import in.tosc.doandroidlib.objects.Region;

/**
 * Created by ashugupta on 24/05/17.
 */


// Interface to pass selected options from fragments to activity during Droplet Creating Process
public interface onItemSelectNewDroplet {

    public void onDataCenterSelect(Region region);

    public void onImageSelect(Image image);
}
