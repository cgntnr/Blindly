package ch.epfl.sdp.blindly.settings

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import ch.epfl.sdp.blindly.R
import org.hamcrest.Matchers
import org.junit.Test
import org.junit.runner.RunWith

private const val TEST_LOCATION = "Lausanne, Switzerland"
@RunWith(AndroidJUnit4::class)
class SettingsLocationTest {

    @Test
    fun showMeFromIntentIsDisplayedProperly() {
        val intent = Intent(ApplicationProvider.getApplicationContext(), SettingsLocation::class.java)
        intent.putExtra(EXTRA_LOCATION, TEST_LOCATION)

        ActivityScenario.launch<SettingsLocation>(intent)
        onView(withId(R.id.my_current))
                .check(ViewAssertions.matches(ViewMatchers.withText(Matchers.containsString(TEST_LOCATION))))
    }
}