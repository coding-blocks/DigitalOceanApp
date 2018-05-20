package in.tosc.digitaloceanapp.fragments;




import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;


import in.tosc.digitaloceanapp.R;
import static in.tosc.digitaloceanapp.matchers.RecyclerViewMatcher.withRecyclerView;

import static junit.framework.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class SelectImageFragmentTest {

    public FragmentTestRule<SelectImageFragment> mFragmentTestRule =
            new FragmentTestRule<>(SelectImageFragment.class);

    @Test
    public void fragmentcanbeinstantiated() {

        assertNotNull(onView(withRecyclerView(R.id.imageRecyclerVIew)
                .atPosition(0))
                .perform(click()));


    }

    @Before
    public void setUp() throws Exception {
        mFragmentTestRule.launchActivity(null);

    }

}