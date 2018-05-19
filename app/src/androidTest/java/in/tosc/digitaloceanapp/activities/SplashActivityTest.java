package in.tosc.digitaloceanapp.activities;


import android.support.test.rule.ActivityTestRule;

import in.tosc.digitaloceanapp.R;

import static junit.framework.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;



public class SplashActivityTest {

    @Rule
    public ActivityTestRule<SplashActivity> splashActivityActivityTestRule =
            new ActivityTestRule<>(SplashActivity.class);

    private SplashActivity splashActivity = null;

    @Before
    public void setUp() {

        splashActivity = splashActivityActivityTestRule.getActivity();
    }


    @Test
    public void testLauch() {

        assertNotNull(splashActivity.findViewById(R.id.btnLogin));


    }

    @After
    public void tearDown() {

        splashActivity = null;
    }

}