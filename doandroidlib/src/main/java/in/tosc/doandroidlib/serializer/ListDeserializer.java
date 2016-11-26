package in.tosc.doandroidlib.serializer;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by championswimmer on 27/11/16.
 */

public class ListDeserializer <T> implements JsonDeserializer<List<T>> {

    String key;

    public ListDeserializer(String objKey) {
        this.key = objKey;
    }

    @Override
    public List<T> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return new Gson().fromJson(json.getAsJsonObject().getAsJsonArray(key), typeOfT);
    }
}
