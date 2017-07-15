package in.tosc.doandroidlib;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import in.tosc.doandroidlib.api.DigitalOceanClient;
import in.tosc.doandroidlib.mockapi.MockResponses;
import in.tosc.doandroidlib.mockapi.MockUtils;
import in.tosc.doandroidlib.objects.AccountInfo;
import okhttp3.Interceptor;

/**
 * Created by championswimmer on 15/07/17.
 */

public class DigitalOceanTest {

    public static DigitalOceanClient doClient;


    @Before
    public void init () {
        DigitalOcean.init("1kn3b41mn35b135", "https://mywebsite.com/callback", true);
        doClient = DigitalOcean.getDOClient("1351mbmn1bb4mnb3m1n4b1mn4bm");
    }

    @Test
    public void onLoggedIn() {
        DigitalOcean.onLoggedIn("m31b5m13b1b23febm1513bj15hb");
    }


    @Test
    public void getAccount () throws IOException {
        AccountInfo accountInfo = doClient.getAccount().execute().body();
        assertEquals("sammy@digitalocean.com", accountInfo.getAccount().getEmail());
    }

    @Test
    public void getDroplets () throws IOException {
        assertEquals(Integer.valueOf(3164444), doClient.getDroplets(1,1).execute().body().getDroplets().get(0).getId());
    }
}
