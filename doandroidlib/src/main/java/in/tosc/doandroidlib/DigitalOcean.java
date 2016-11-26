package in.tosc.doandroidlib;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.List;

import in.tosc.doandroidlib.api.DigitalOceanClient;
import in.tosc.doandroidlib.objects.Account;
import in.tosc.doandroidlib.objects.Droplet;
import in.tosc.doandroidlib.serializer.DropletListDeserializer;
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
    static String authToken = "";
    static Retrofit r;

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
                .registerTypeAdapter(new TypeToken<List<Droplet>>(){}.getType(), new DropletListDeserializer())
                .create();

        r = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient)
                .build();
    }

    public static DigitalOceanClient getDOClient () {
        return r.create(DigitalOceanClient.class);
    }




}
