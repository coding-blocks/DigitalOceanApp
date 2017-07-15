package in.tosc.digitaloceanapp;

import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by championswimmer on 15/07/17.
 */

@RunWith(AndroidJUnit4.class)
public class DOAppTest {

    Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
    DOApp doApp;


    @Before
    public void setUp () throws IllegalAccessException, ClassNotFoundException, InstantiationException {

        doApp = (DOApp) Instrumentation.newApplication(DOApp.class, instrumentation.getTargetContext());

    }

    @Test
    public void check () {
        doApp.onCreate();
    }

}
