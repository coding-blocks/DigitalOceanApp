package in.tosc.digitaloceanapp;

import android.app.Application;

import in.tosc.digitaloceanapp.utils.FontsOverride;
import in.tosc.doandroidlib.DigitalOcean;

/**
 * Created by championswimmer on 26/11/16.
 */

public class DOApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
//        FontsOverride.setDefaultFont(this, "DEFAULT", "MyFontAsset.ttf");
//        FontsOverride.setDefaultFont(this, "MONOSPACE", "MyFontAsset2.ttf");
//        FontsOverride.setDefaultFont(this, "SERIF", "fonts/ProximaNova.ttf");
        FontsOverride.setDefaultFont(this, "SANS_SERIF", "fonts/" + FontsOverride.FONT_PROXIMA_NOVA);
        DigitalOcean.init(getString(R.string.do_client_id), getString(R.string.do_callback_url), false);

    }
}
