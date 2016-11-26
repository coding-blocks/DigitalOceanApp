package in.tosc.doandroidlib.serializer;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.List;

import in.tosc.doandroidlib.objects.Droplet;

/**
 * Created by championswimmer on 26/11/16.
 */

public class DropletListDeserializer implements JsonDeserializer<List<Droplet>> {
    @Override
    public List<Droplet> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return new Gson().fromJson(json.getAsJsonObject().getAsJsonArray("droplets"), typeOfT);
    }
}
