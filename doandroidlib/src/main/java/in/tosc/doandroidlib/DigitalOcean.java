package in.tosc.doandroidlib;

import android.app.Activity;
import android.content.Intent;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.List;

import in.tosc.doandroidlib.api.DOLoginActivity;
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
    public static final int LOGIN_ACT_INTENT_CODE = 3245;

    public static final int LOGIN_SUCCESS = 901;
    public static final int LOGIN_FAIL = 902;

    public static final String BASE_URL = "https://api.digitalocean.com/v2/";
    public static final String BASE_URL_V1 = "https://cloud.digitalocean.com/api/v1/";

    public static String getClientId() {
        return clientId;
    }

    static String clientId;

    public static String getCallbackUrl() {
        return callbackUrl;
    }

    static String callbackUrl;
    static String authToken = "";
    static Retrofit r, r2, r3;

    public static void init(String clientId, String callbackUrl) {
        DigitalOcean.clientId = clientId;
        DigitalOcean.callbackUrl = callbackUrl;
    }

    public static void doLogin(Activity act) {
        Intent loginIntent = new Intent(act, DOLoginActivity.class);
        act.startActivityForResult(loginIntent, 3245);
    }


    public static void onLoggedIn(String token) {
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
                .registerTypeAdapter(new TypeToken<List<Droplet>>() {
                }.getType(), new ListDeserializer<Droplet>("droplets"))
                .registerTypeAdapter(new TypeToken<List<Image>>() {
                }.getType(), new ListDeserializer<Image>("images"))
                .registerTypeAdapter(new TypeToken<List<Size>>() {
                }.getType(), new ListDeserializer<Size>("sizes"))
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

    public static DigitalOceanClient getDOClient(final String authToken) {

        if (r == null) {
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
                    .registerTypeAdapter(new TypeToken<List<Droplet>>() {
                    }.getType(), new ListDeserializer<Droplet>("droplets"))
                    .registerTypeAdapter(new TypeToken<List<Image>>() {
                    }.getType(), new ListDeserializer<Image>("images"))
                    .registerTypeAdapter(new TypeToken<List<Size>>() {
                    }.getType(), new ListDeserializer<Size>("sizes"))
                    .create();

            r = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(httpClient)
                    .build();
        }
        return r.create(DigitalOceanClient.class);
    }

    public static DigitalOceanStatisticsClient getDOStatsClient(final String authToken) {
        if (r2 == null) {
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


            r2 = new Retrofit.Builder()
                    .baseUrl(BASE_URL_V1)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient)
                    .build();
        }
        return r2.create(DigitalOceanStatisticsClient.class);
    }

    public static Droplet createDOdroplet(final String authToken) {
        if (r3 == null) {
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
            r3 = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient)
                    .build();
        }
        return r3.create(Droplet.class);
    }
}
