package in.tosc.digitaloceanapp.activities;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import in.tosc.digitaloceanapp.R;
import in.tosc.doandroidlib.DigitalOcean;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class SplashActivityTest {

    @Rule
    public ActivityTestRule<SplashActivity> splashActivityActivityTestRule = new ActivityTestRule<>(SplashActivity.class);

    private SplashActivity splashActivity = null;

    @Before
    public void setUp() throws Exception {

        splashActivity = splashActivityActivityTestRule.getActivity();
    }


    @Test
    public void testLauch() {

        assertNotNull(splashActivity.findViewById(R.id.btnLogin));


    }

    @After
    public void tearDown() throws Exception {

        splashActivity = null;
    }

    @Test
    public void onActivityResult() {
    }

    @Test
    public void progressFurther() {

    }
}