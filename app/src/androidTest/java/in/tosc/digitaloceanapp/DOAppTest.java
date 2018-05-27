package in.tosc.digitaloceanapp;

import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import in.tosc.doandroidlib.DigitalOcean;

/**
 * Created by championswimmer on 15/07/17.
 */

@RunWith(AndroidJUnit4.class)
public class DOAppTest {

    Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
    DOApp doApp;

    @Before
    public void setUp () {
        DigitalOcean.init("id","token", true);
        DigitalOcean.getDOClient("token");
    }

    @Test
    public void startApp () throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        doApp = (DOApp) Instrumentation.newApplication(DOApp.class, instrumentation.getTargetContext());
        instrumentation.callApplicationOnCreate(doApp);
    }

}
