package in.tosc.digitaloceanapp.interfaces;


import java.util.List;

import in.tosc.doandroidlib.objects.Droplet;



public interface OnDropletNameChange {
    void onSuccess(List<Droplet> modifiedData);

    void onError(String message);
}
