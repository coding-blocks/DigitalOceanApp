package in.tosc.digitaloceanapp.fragments;

import org.junit.Test;

import in.tosc.digitaloceanapp.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static in.tosc.digitaloceanapp.matchers.RecyclerViewMatcher.withRecyclerView;
import static org.junit.Assert.*;

public class SelectImageFragmentTest {

    public FragmentTestRule<SelectImageFragment> mFragmentTestRule = new FragmentTestRule<>(SelectImageFragment.class);

    @Test
    public void fragment_can_be_instantiated() {

        // Launch the activity to make the fragment visible
        mFragmentTestRule.launchActivity(null);

        // Then use Espresso to test the Fragment
        onView(withRecyclerView(R.id.imageRecyclerVIew).atPosition(0)).check(matches(isDisplayed()));
        onView(withRecyclerView(R.id.imageRecyclerVIew).atPosition(0)).perform(click());


    }
}