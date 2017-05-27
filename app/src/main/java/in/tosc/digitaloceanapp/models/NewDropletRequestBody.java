package in.tosc.digitaloceanapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import in.tosc.doandroidlib.objects.Image;
import in.tosc.doandroidlib.objects.Key;
import in.tosc.doandroidlib.objects.Region;
import in.tosc.doandroidlib.objects.Size;
import in.tosc.doandroidlib.objects.Tag;
import in.tosc.doandroidlib.objects.Volume;

/**
 * Created by ashugupta on 23/05/17.
 */

public class NewDropletRequestBody {

    public NewDropletRequestBody(String name, Region region, Image image, Size size) {

        this.image = image;
        this.size = size;
        this.name = name;
        this.region = region;
        this.keys = null;
        this.backups = true;
        this.ipv6 = true;
        this.userData = null;
        this.private_networking = false;
        this.volumes = null;
        this.tags = null;
    }

    private Integer id;

    private String name;

    private Region region;

    private Image image;

    private Size size;

    @SerializedName("ssh_keys")
    private List<Key> keys;

    private boolean backups;

    private boolean ipv6;

    @SerializedName("user_data")
    private String userData;

    private boolean private_networking;

    private List<Volume> volumes;

    private List<Tag> tags;

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public List<Key> getKeys() {
        return keys;
    }

    public void setKeys(List<Key> keys) {
        this.keys = keys;
    }

    public boolean isBackups() {
        return backups;
    }

    public void setBackups(boolean backups) {
        this.backups = backups;
    }

    public boolean isIpv6() {
        return ipv6;
    }

    public void setIpv6(boolean ipv6) {
        this.ipv6 = ipv6;
    }

    public String getUserData() {
        return userData;
    }

    public void setUserData(String userData) {
        this.userData = userData;
    }

    public boolean isPrivate_networking() {
        return private_networking;
    }

    public void setPrivate_networking(boolean private_networking) {
        this.private_networking = private_networking;
    }

    public List<Volume> getVolumes() {
        return volumes;
    }

    public void setVolumes(List<Volume> volumes) {
        this.volumes = volumes;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }
}
