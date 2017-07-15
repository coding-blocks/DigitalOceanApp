package in.tosc.digitaloceanapp.activities;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import in.tosc.digitaloceanapp.R;

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
        aboutActivity = activityRule.getActivity();
    }

    @Test
    public void tryOpenPage () {
        View github = aboutActivity.findViewById(R.id.relative_layout_github);
        View contributors = aboutActivity.findViewById(R.id.relative_layout_see_all_contributors);

        aboutActivity.openPage(github);
        aboutActivity.openPage(contributors);
    }
}
