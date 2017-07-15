package in.tosc.digitaloceanapp.activities;

import android.app.Instrumentation;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import android.support.test.internal.runner.listener.InstrumentationRunListener;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import in.tosc.digitaloceanapp.R;
import in.tosc.doandroidlib.DigitalOcean;

import static android.support.test.espresso.Espresso.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static in.tosc.digitaloceanapp.matchers.RecyclerViewMatcher.withRecyclerView;

/**
 * Created by championswimmer on 15/07/17.
 */
@RunWith(AndroidJUnit4.class)
public class DropletActivityTest {
    public static DropletActivity dropletActivity;

    @Rule
    public ActivityTestRule<DropletActivity> actRule =
            new ActivityTestRule<DropletActivity>(DropletActivity.class);

    @Before
    public void setup() {
        dropletActivity = actRule.getActivity();
    }

    @Test
    public void testDropletInfo () {
        onView(withRecyclerView(R.id.dropletsRv).atPosition(0))
                .check(matches(hasDescendant(withText("example.com"))));
    }

    @After
    public void tearDown () {
        dropletActivity.finish();
    }

}
