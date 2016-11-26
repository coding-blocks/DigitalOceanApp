package in.tosc.doandroidlib;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.List;

import in.tosc.doandroidlib.api.DigitalOceanClient;
import in.tosc.doandroidlib.api.DigitalOceanStatisticsClient;
import in.tosc.doandroidlib.objects.Account;
import in.tosc.doandroidlib.objects.Action;
import in.tosc.doandroidlib.objects.Droplet;
import in.tosc.doandroidlib.objects.Image;
import in.tosc.doandroidlib.objects.Size;
import in.tosc.doandroidlib.serializer.ListDeserializer;
import in.tosc.doandroidlib.serializer.ObjectDeserializer;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by championswimmer on 26/11/16.
 */

public class DigitalOcean {

    public static final String TAG = "DO";

    public static final String BASE_URL = "https://api.digitalocean.com/v2/";
    public static final String BASE_URL_V1 = "https://cloud.digitalocean.com/api/v1/";

    static String authToken = "";
    static Retrofit r, r2;

    public static void init (String token) {
        DigitalOcean.authToken = token;

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request().newBuilder()
                                .addHeader("Content-Type", "application/json")
                                .addHeader("Authorization", "Bearer " + authToken)
                                .build();
                        return chain.proceed(request);
                    }
                })
                .build();


        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Account.class, new ObjectDeserializer<Account>("account"))
                .registerTypeAdapter(Action.class, new ObjectDeserializer<Action>("action"))
                .registerTypeAdapter(new TypeToken<List<Droplet>>(){}.getType(), new ListDeserializer<Droplet>("droplets"))
                .registerTypeAdapter(new TypeToken<List<Image>>(){}.getType(), new ListDeserializer<Image>("images"))
                .registerTypeAdapter(new TypeToken<List<Size>>(){}.getType(), new ListDeserializer<Size>("sizes"))
                .create();

        r = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient)
                .build();
        r2 = new Retrofit.Builder()
                .baseUrl(BASE_URL_V1)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();
    }

    public static DigitalOceanClient getDOClient () {
        return r.create(DigitalOceanClient.class);
    }

    public static DigitalOceanStatisticsClient getDOStatsClient () {
        return r2.create(DigitalOceanStatisticsClient.class);
    }




}
