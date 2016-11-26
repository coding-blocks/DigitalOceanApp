package in.tosc.doandroidlib.serializer;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by championswimmer on 26/11/16.
 */

public  class ObjectDeserializer <T> implements JsonDeserializer<T> {

    private String key;

    public ObjectDeserializer(String objKey) {
        key = objKey;
    }

    @Override
    public T deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return new Gson().fromJson(json.getAsJsonObject().getAsJsonObject(key), typeOfT);
    }
}
