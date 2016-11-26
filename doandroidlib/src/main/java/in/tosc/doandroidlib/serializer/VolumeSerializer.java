package in.tosc.doandroidlib.serializer;

import java.lang.reflect.Type;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import in.tosc.doandroidlib.objects.Volume;

/**
 * Serialize the volume info for POST request.
 *
 * @author Eugene Strokin (https://github.com/strokine)
 *
 * @since v2.7
 */
public class VolumeSerializer implements JsonSerializer<Volume> {

    @Override
    public JsonElement serialize(Volume volume, Type paramType, JsonSerializationContext context) {
        final JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("id", volume.getId());
        jsonObject.addProperty("name", volume.getName());

        if (StringUtils.isNotBlank(volume.getDescription())) {
            jsonObject.addProperty("description", volume.getDescription());
        }

        if (StringUtils.isNotBlank(volume.getRegion().getSlug())) {
            jsonObject.addProperty("region", volume.getRegion().getSlug());
        }

        if (null != volume.getSize()) {
            jsonObject.addProperty("size_gigabytes", volume.getSize());
        }

        return jsonObject;
    }

}