package in.tosc.digitaloceanapp.activities;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.WindowManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import in.tosc.digitaloceanapp.R;

import static android.support.test.espresso.Espresso.*;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


/**
 * Created by championswimmer on 15/07/17.
 */

@RunWith(AndroidJUnit4.class)
public class AboutActivityTest {

    AboutActivity aboutActivity;

    @Rule
    public ActivityTestRule<AboutActivity> activityRule =
            new ActivityTestRule<AboutActivity>(AboutActivity.class);

    @Before
    public void setup () {
        aboutActivity = activityRule.launchActivity(null);
        Runnable wakeUpDevice = new Runnable() {
            public void run() {
                aboutActivity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON |
                        WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
                        WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            }
        };
        aboutActivity.runOnUiThread(wakeUpDevice);
    }

    @Test
    public void verifyContributors () {
        onView(withId(R.id.text_view_contributor_1_title))
                .check(matches(withText("Arnav Gupta")));
    }

    @After
    public void tearDown () {
        aboutActivity.finish();
    }
}
