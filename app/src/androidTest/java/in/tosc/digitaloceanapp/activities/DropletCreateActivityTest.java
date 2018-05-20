package in.tosc.digitaloceanapp.activities;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

import android.support.test.rule.ActivityTestRule;

import android.view.WindowManager;

import in.tosc.digitaloceanapp.R;

import static junit.framework.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;




public class DropletCreateActivityTest {
    public static DropletCreateActivity dropletCreateActivity;

    @Rule
    public ActivityTestRule<DropletCreateActivity> actRule =
            new ActivityTestRule<>(DropletCreateActivity.class);


//runs before test
    @Before
    public void setUp() {
        dropletCreateActivity = actRule.getActivity();
        Runnable wakeUpDevice = new Runnable() {
            public void run() {
                dropletCreateActivity.getWindow()
                        .addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                        |
                        WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                        |
                        WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            }
        };
        dropletCreateActivity.runOnUiThread(wakeUpDevice);
    }

    @Test
    public void testDropletCreateInfo() {
        assertNotNull(dropletCreateActivity.findViewById(R.id.buttonNext));
        onView(withId(R.id.buttonNext))
                .perform(click());
//        onView(withId(R.id.buttonNext)).perform(),
        onView(withId(R.id.buttonPrev))
                .perform(click()).perform();
    }

    @After
    public void tearDown() {
        dropletCreateActivity.finish();
    }
}