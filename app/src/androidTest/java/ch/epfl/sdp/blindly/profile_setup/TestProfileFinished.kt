package ch.epfl.sdp.blindly.profile_preferences

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import ch.epfl.sdp.blindly.R
import ch.epfl.sdp.blindly.main_screen.MainScreen
import ch.epfl.sdp.blindly.profile_setup.ProfileFinished
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestProfileFinished {

    @get:Rule
    val activityRule = ActivityScenarioRule(ProfileFinished::class.java)

    @Test
    fun profileFinishedFiresMainScreen() {
        Intents.init()
        val buttonMainScreen = Espresso.onView(ViewMatchers.withId(R.id.buttonMainScreen))
        buttonMainScreen.perform(ViewActions.click())
        Intents.intended(IntentMatchers.hasComponent(MainScreen::class.java.name))
        Intents.release()
    }
}