package in.tosc.doandroidlib;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by championswimmer on 15/07/17.
 */

public class DigitalOceanTest {



    @Before
    public void init () {
        DigitalOcean.init("1kn3b41mn35b135", "https://mywebsite.com/callback");
    }

    @Test
    public void onLoggedIn() {
        DigitalOcean.onLoggedIn("m31b5m13b1b23febm1513bj15hb");
    }

    @Test
    public void getDOClient () {
        DigitalOcean.getDOClient("1351mbmn1bb4mnb3m1n4b1mn4bm");
    }
}
