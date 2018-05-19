package in.tosc.digitaloceanapp.activities;

import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.espresso.contrib.NavigationViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import android.support.test.internal.runner.listener.InstrumentationRunListener;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import android.view.Gravity;
import android.view.WindowManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import in.tosc.digitaloceanapp.R;
import in.tosc.doandroidlib.DigitalOcean;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.*;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.contrib.DrawerMatchers.isClosed;
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
        Runnable wakeUpDevice = new Runnable() {
            public void run() {
                dropletActivity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON |
                        WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
                        WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            }
        };
        dropletActivity.runOnUiThread(wakeUpDevice);
    }


    @Test
    public void testDropletInfo() {
        onView(withRecyclerView(R.id.dropletsRv).atPosition(0))
                .check(matches(hasDescendant(withText("example.com"))));
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText("Settings")).perform(click());



    }


    @Test
    public void clickOnYourNavigationItem_ShowsYourScreen1() {
        // Open Drawer to click on navigation.
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
                .perform(DrawerActions.open()); // Open Drawer
        onView(withId(R.id.nav_view))
                .perform(NavigationViewActions.navigateTo(R.id.nav_about));

    }

    @Test
    public void clickOnYourNavigationItem_ShowsYourScreen2() {
        // Open Drawer to click on navigation.
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
                .perform(DrawerActions.open()); // Open Drawer
        onView(withId(R.id.nav_view))
                .perform(NavigationViewActions.navigateTo(R.id.nav_billing));
    }

    @Test
    public void clickOnYourNavigationItem_ShowsYourScreen3() {
        // Open Drawer to click on navigation.
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
                .perform(DrawerActions.open()); // Open Drawer
        onView(withId(R.id.nav_view))
                .perform(NavigationViewActions.navigateTo(R.id.nav_profile));

    }

    @Test
    public void clickOnYourNavigationItem_ShowsYourScreen4() {
        // Open Drawer to click on navigation.
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
                .perform(DrawerActions.open()); // Open Drawer
        onView(withId(R.id.nav_view))
                .perform(NavigationViewActions.navigateTo(R.id.nav_manage));

    }


    @Test
    public void clickOnYourNavigationItem_ShowsYourScreen6() {
        // Open Drawer to click on navigation.
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
                .perform(DrawerActions.open()); // Open Drawer
        onView(withId(R.id.nav_view))
                .perform(NavigationViewActions.navigateTo(R.id.nav_share));

    }

    @Test
    public void clickOnYourNavigationItem_ShowsYourScreen5() {
        // Open Drawer to click on navigation.
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
                .perform(DrawerActions.open()); // Open Drawer
        onView(withId(R.id.nav_view))
                .perform(NavigationViewActions.navigateTo(R.id.nav_logout));

    }

    @After
    public void tearDown() {
        dropletActivity.finish();
    }

}
