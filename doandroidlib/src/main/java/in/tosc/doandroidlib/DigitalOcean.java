package in.tosc.doandroidlib;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import java.io.IOException;

import in.tosc.doandroidlib.api.DOLoginActivity;
import in.tosc.doandroidlib.api.DigitalOceanClient;
import in.tosc.doandroidlib.api.DigitalOceanStatisticsClient;
import in.tosc.doandroidlib.exceptions.ClientInitializationException;
import in.tosc.doandroidlib.mockapi.MockResponses;
import in.tosc.doandroidlib.mockapi.MockUtils;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * The main entry point for the Library
 * First you need to initialise it using {@link #init(String clientId, String callbackUrl)}
 * (The clientId and callbackUrl are what you get when you register an app on Digital Oceal
 * developer dashboard)
 *
 * You should use one of the following -
 * <ul>
 *  <li>Use the static {@link #doLogin(Activity)} to login the user and get token</li>
 *  <li>Use the static {@link #getDOClient(String)} to get a client</li>
 * </ul>
 * @author championswimmer
 * @since 0.1.0
 */

public class DigitalOcean {

    public static final String TAG = "DO";
    public static final int LOGIN_ACT_INTENT_CODE = 3245;
    public static final String EXTRA_AUTH_TOKEN = "authToken";

    private static boolean USE_MOCK_API = false;

    /**
     * The status code for successful login
     */
    public static final int LOGIN_SUCCESS = 901;
    /**
     * The status code for failed login
     */
    public static final int LOGIN_FAIL = 902;

    public static final String BASE_URL = "https://api.digitalocean.com/v2/";
    public static final String BASE_URL_V1 = "https://cloud.digitalocean.com/api/v1/";

    public static String getClientId() {
        return clientId;
    }

    private static String clientId;

    public static String getCallbackUrl() {
        return callbackUrl;
    }

    private static String callbackUrl;
    private static String authToken = "";
    private static Retrofit r, r2;
    private static OkHttpClient okHttpClient;

    /**
     * Static initialiser to initialise usage of Digital Ocean in your app
     * @param clientId
     * @param callbackUrl
     */
    public static void init(String clientId, String callbackUrl) {
        DigitalOcean.clientId = clientId;
        DigitalOcean.callbackUrl = callbackUrl;
    }

    /**
     * Initialise Digital Ocean, optionally with mock api
     * @param clientId
     * @param callbackUrl
     * @param useMockApi
     */
    public static void init(String clientId, String callbackUrl, boolean useMockApi) {
        USE_MOCK_API = useMockApi;
        init(clientId, callbackUrl);
    }

    /**
     * This starts the login process (in a Chrome custom tab).
     * We use {@link #LOGIN_ACT_INTENT_CODE} to do a {@link Activity#startActivityForResult(Intent, int)}
     *
     *
     * @param act An activity object, usually <code>this</code> if doing this from
     *            {@link Activity#onCreate(Bundle)}
     */
    public static void doLogin(Activity act) {
        Intent loginIntent = new Intent(act, DOLoginActivity.class);
        act.startActivityForResult(loginIntent, LOGIN_ACT_INTENT_CODE);
    }

    /**
     * Method to set your custom HttpClient. Use this if you want custom interceptors
     * You need to do this before calling {@link #getDOClient(String)}
     * @param customClient
     */
    public static void setHttpClient (OkHttpClient customClient) {
        okHttpClient = customClient;
    }


    /**
     * You do not need to directly use the function ever. We use this to set the Oauth Token
     * globally.
     *
     * @param token : The auth token you receive from the Chrome Custom Tab for login
     */
    public static void onLoggedIn(String token) {
        DigitalOcean.authToken = token;

        if (okHttpClient == null) {
            OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder()
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request request = chain.request().newBuilder()
                                    .addHeader("Content-Type", "application/json")
                                    .addHeader("Authorization", "Bearer " + authToken)
                                    .build();
                            return chain.proceed(request);
                        }
                    });

            if (USE_MOCK_API) {
                clientBuilder.addInterceptor(new MockUtils.MockGetInterceptor()
                        .addPathResponse(MockResponses.api)
                );
            }
            okHttpClient = clientBuilder.build();
        }




        r = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        r2 = new Retrofit.Builder()
                .baseUrl(BASE_URL_V1)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    /**
     * Get a Digital Ocean v2 API client. For available methods, see {@link DigitalOceanClient}
     * @param authToken The auth token (which you probably should have saved in SharedPreferences)
     * @return
     */
    public static DigitalOceanClient getDOClient(@NonNull String authToken) {

        if (clientId == null || callbackUrl == null) {
            (new ClientInitializationException("DigitalOcean.init() and/or DigitalOcean.onLoggedIn() wasn't called. " +
                    "You need to initialize before getting access to client")).printStackTrace();
            return null;
        }

        onLoggedIn(authToken);

        return r.create(DigitalOceanClient.class);
    }

    /**
     * Get a DigitalOcean statistics API client
     * FIXME: This is not yet implemented
     * @param authToken
     * @return A client you can use to get statistics from DigitalOcean
     */
    public static DigitalOceanStatisticsClient getDOStatsClient(@NonNull String authToken) {

        if (clientId == null || callbackUrl == null) {
            (new ClientInitializationException("DigitalOcean.init() and/or DigitalOcean.onLoggedIn() wasn't called. " +
                "You need to initialize before getting access to client")).printStackTrace();
            return null;
        }
        onLoggedIn(authToken);

        return r2.create(DigitalOceanStatisticsClient.class);
    }

}
