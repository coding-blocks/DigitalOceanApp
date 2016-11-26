package in.tosc.doandroidlib;

import android.util.Log;

import java.io.IOException;

import in.tosc.doandroidlib.api.DigitalOceanClient;
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


        r = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();
    }

    public static DigitalOceanClient getDOClient () {
        return r.create(DigitalOceanClient.class);
    }




}
