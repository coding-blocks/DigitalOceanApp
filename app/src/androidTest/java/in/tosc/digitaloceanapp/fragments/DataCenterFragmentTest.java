package in.tosc.digitaloceanapp.fragments;

import org.junit.Test;

import in.tosc.digitaloceanapp.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static in.tosc.digitaloceanapp.matchers.RecyclerViewMatcher.withRecyclerView;
import static org.junit.Assert.*;

public class DataCenterFragmentTest {

    public FragmentTestRule<DataCenterFragment> mFragmentTestRule = new FragmentTestRule<>(DataCenterFragment.class);

    @Test
    public void fragment_can_be_instantiated() {

        // Launch the activity to make the fragment visible
        mFragmentTestRule.launchActivity(null);

        // Then use Espresso to test the Fragment
        onView(withRecyclerView(R.id.datacenter_recycler).atPosition(0)).check(matches(isDisplayed()));
        onView(withRecyclerView(R.id.datacenter_recycler).atPosition(0)).perform(click());


    }

}