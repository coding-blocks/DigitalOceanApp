package in.tosc.doandroidlib.serializer;

/**
 * Created by championswimmer on 26/11/16.
 */
import java.lang.reflect.Type;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import in.tosc.doandroidlib.objects.Droplet;
import in.tosc.doandroidlib.objects.Key;

/**
 * Serializer for droplet class
 *
 * @author Jeevanandam M. (jeeva@myjeeva.com)
 *
 * @since v2.0
 */
public class DropletSerializer implements JsonSerializer<Droplet> {

    @Override
    public JsonElement serialize(Droplet droplet, Type paramType, JsonSerializationContext context) {
        final JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("name", droplet.getName());

        if (null != droplet.getNames() && droplet.getNames().size() > 0) {
            JsonArray names = new JsonArray();
            for (String name : droplet.getNames()) {
                names.add(context.serialize(name));
            }
            jsonObject.add("names", names);
        }

        jsonObject.addProperty("region", droplet.getRegion().getSlug());
        jsonObject.addProperty("size", droplet.getSize());

        if (null == droplet.getImage().getId()) {
            jsonObject.addProperty("image", droplet.getImage().getSlug());
        } else {
            jsonObject.addProperty("image", droplet.getImage().getId());
        }

        if (null != droplet.getEnableBackup()) {
            jsonObject.addProperty("backups", droplet.getEnableBackup());
        }

        if (null != droplet.getEnableIpv6()) {
            jsonObject.addProperty("ipv6", droplet.getEnableIpv6());
        }

        if (null != droplet.getEnablePrivateNetworking()) {
            jsonObject.addProperty("private_networking", droplet.getEnablePrivateNetworking());
        }

        if (null != droplet.getKeys() && droplet.getKeys().size() > 0) {
            JsonArray sshKeys = new JsonArray();
            for (Key k : droplet.getKeys()) {
                if (null != k.getId()) {
                    sshKeys.add(context.serialize(k.getId()));
                }
                if (!StringUtils.isEmpty(k.getFingerprint())) {
                    sshKeys.add(context.serialize(k.getFingerprint()));
                }
            }
            jsonObject.add("ssh_keys", sshKeys);
        }

        // #19 - https://github.com/jeevatkm/digitalocean-api-java/issues/19
        if (null != droplet.getUserData()) {
            jsonObject.addProperty("user_data", droplet.getUserData());
        }

        return jsonObject;
    }

}