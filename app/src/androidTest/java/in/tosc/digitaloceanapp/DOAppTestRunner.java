package in.tosc.digitaloceanapp;

import android.app.Application;
import android.support.test.runner.AndroidJUnitRunner;

import in.tosc.doandroidlib.DigitalOcean;
/**
 * Created by championswimmer on 15/07/17.
 */

public class DOAppTestRunner extends AndroidJUnitRunner {


    @Override
    public void callApplicationOnCreate(Application app) {
        DigitalOcean.init("id", "token", true);
        DigitalOcean.getDOClient("token");
        super.callApplicationOnCreate(app);
    }
}
