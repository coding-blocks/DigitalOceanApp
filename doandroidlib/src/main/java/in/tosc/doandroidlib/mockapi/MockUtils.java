package in.tosc.doandroidlib.mockapi;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by championswimmer on 15/07/17.
 */

public class MockUtils {

    public static final String BASE_PATH = "v2/";

    public static OkHttpClient createClient(List<Interceptor> mockInterceptors) {
        OkHttpClient.Builder clientBuilder  = new OkHttpClient.Builder();
        clientBuilder.interceptors().addAll(mockInterceptors);
        return clientBuilder.build();
    }

    public static class MockGetInterceptor implements Interceptor {
        private HashMap<String, String> pathResponses = new HashMap<>();

        public MockGetInterceptor addPathResponse(HashMap<String, String> pathResponses) {
            this.pathResponses.putAll(pathResponses);
            return this;
        }


        @Override
        public Response intercept(Chain chain) throws IOException {

            for (String path: pathResponses.keySet()) {
                if (StringUtils.join(chain.request().url().pathSegments(), "/").equals(BASE_PATH + path)) {

                    return new Response.Builder()
                            .body(
                                    ResponseBody.create(
                                            MediaType.parse("application/json; charset=utf-8"),
                                            pathResponses.get(path)
                                    )
                            )
                            .request(chain.request())
                            .code(200)
                            .message("200 OK")
                            .protocol(Protocol.HTTP_1_1)
                            .addHeader("ratelimit-limit", String.valueOf(1200))
                            .addHeader("ratelimit-remaining", String.valueOf(1137))
                            .addHeader("ratelimit-reset", String.valueOf(1415984218))
                            .build();
                }
            }
            return null;
        }
    }

}
