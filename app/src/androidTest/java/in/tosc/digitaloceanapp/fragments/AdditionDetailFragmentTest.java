package in.tosc.digitaloceanapp.fragments;

import org.junit.Rule;
import org.junit.Test;


import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class AdditionDetailFragmentTest {

    @Rule
    public FragmentTestRule<AdditionalDetailsFragment> mFragmentTestRule = new FragmentTestRule<>(AdditionalDetailsFragment.class);

    @Test
    public void fragmentcanbeinstantiated() {

        // Launch the activity to make the fragment visible
        mFragmentTestRule.launchActivity(null);

        // Then use Espresso to test the Fragment
//        onView(withId(R.id.btnCreateDroplet)).check(matches(isDisplayed())).perform(click());
        onView(withText("Create Droplet")).check(matches(isDisplayed())).perform(click());
        onView(withText("Private Networking")).check(matches(isDisplayed())).perform(click());
        onView(withText("Backups")).check(matches(isDisplayed())).perform(click());
        onView(withText("IPv6")).check(matches(isDisplayed())).perform(click());


    }

}