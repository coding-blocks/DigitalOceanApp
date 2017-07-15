package in.tosc.doandroidlib;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import in.tosc.doandroidlib.api.DigitalOceanClient;
import in.tosc.doandroidlib.http.MockResponses;
import in.tosc.doandroidlib.http.MockUtils;
import in.tosc.doandroidlib.objects.AccountInfo;
import okhttp3.Interceptor;

/**
 * Created by championswimmer on 15/07/17.
 */

public class DigitalOceanTest {

    public static DigitalOceanClient doClient;


    @Before
    public void init () {
        DigitalOcean.init("1kn3b41mn35b135", "https://mywebsite.com/callback");
        doClient = DigitalOcean.getDOClient("1351mbmn1bb4mnb3m1n4b1mn4bm");

        // Setup mock interceptors
        List<Interceptor> mockInterceptors = new ArrayList<>();
        mockInterceptors.add(
                new MockUtils.MockGetInterceptor()
                        .addPathResponse(MockResponses.api)
        );
        DigitalOcean.setHttpClient(MockUtils.createClient(
                mockInterceptors
        ));
    }

    @Test
    public void onLoggedIn() {
        DigitalOcean.onLoggedIn("m31b5m13b1b23febm1513bj15hb");
    }

    @Test
    public void getDOClient () {
        doClient = DigitalOcean.getDOClient("1351mbmn1bb4mnb3m1n4b1mn4bm");
    }

    @Test
    public void getAccount () throws IOException {
        AccountInfo accountInfo = doClient.getAccount().execute().body();
        assertEquals(accountInfo.getAccount().getEmail(), "sammy@digitalocean.com");
    }

    @Test
    public void getDroplets () throws IOException {
        assertEquals(Integer.valueOf(3164444), doClient.getDroplets(1,1).execute().body().getDroplets().get(0).getId());
    }
}
