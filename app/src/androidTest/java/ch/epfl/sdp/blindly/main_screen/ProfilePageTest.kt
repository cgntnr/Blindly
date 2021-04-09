<<<<<<< HEAD:app/src/androidTest/java/ch/epfl/sdp/blindly/main_screen/ProfilePageTest.kt
package ch.epfl.sdp.blindly.main_screen

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.intent.Intents.*
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import ch.epfl.sdp.blindly.AudioLibrary
import ch.epfl.sdp.blindly.EditProfile
import ch.epfl.sdp.blindly.R
import ch.epfl.sdp.blindly.recording.RecordingActivity
import ch.epfl.sdp.blindly.settings.Settings
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class ProfilePageTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainScreen::class.java)

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private fun gotoProfileScreen() {
        onView(withId(R.id.view_pager)).perform(swipeLeft())
        onView(withId(R.id.view_pager)).perform(swipeLeft())
        onView(withId(R.id.view_pager)).perform(swipeLeft())
    }

    @Test
    fun editButtonFiresEditProfileActivty() {
        init()
        gotoProfileScreen()
        onView(withId(R.id.edit_info_profile_button)).perform(click())
        intended(hasComponent(EditProfile::class.java.name))
        release()
    }

    @Test
    fun settingsButtonFiresSettingsActivity() {
        init()
        gotoProfileScreen()
        onView(withId(R.id.settings_profile_button)).perform(click())
        intended(hasComponent(Settings::class.java.name))
        release()
    }

    @Test
    fun recordAudioButtonFiresRecordingActivity() {
        init()
        gotoProfileScreen()
        onView(withId(R.id.record_audio_profile_button)).perform(click())
        intended(hasComponent(RecordingActivity::class.java.name))
        release()
    }

    @Test
    fun audioLibraryFiresAudioLibraryActivity() {
        init()
        gotoProfileScreen()
        onView(withId(R.id.audio_library_profile_button)).perform(click())
        intended(hasComponent(AudioLibrary::class.java.name))
        release()
    }

=======
package ch.epfl.sdp.blindly

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.intent.Intents.*
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import ch.epfl.sdp.blindly.recording.RecordingActivity
import ch.epfl.sdp.blindly.settings.Settings
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class ProfilePageTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainScreen::class.java)

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private fun gotoProfileScreen() {
        onView(withId(R.id.view_pager)).perform(swipeLeft())
        onView(withId(R.id.view_pager)).perform(swipeLeft())
        onView(withId(R.id.view_pager)).perform(swipeLeft())
    }

    @Test
    fun editButtonFiresEditProfileActivty() {
        init()
        gotoProfileScreen()
        onView(withId(R.id.edit_info_profile_button)).perform(click())
        intended(hasComponent(EditProfile::class.java.name))
        release()
    }

    @Test
    fun settingsButtonFiresSettingsActivity() {
        init()
        gotoProfileScreen()
        onView(withId(R.id.settings_profile_button)).perform(click())
        intended(hasComponent(Settings::class.java.name))
        release()
    }

    @Test
    fun recordAudioButtonFiresRecordingActivity() {
        init()
        gotoProfileScreen()
        onView(withId(R.id.record_audio_profile_button)).perform(click())
        intended(hasComponent(RecordingActivity::class.java.name))
        release()
    }

>>>>>>> origin/main:app/src/androidTest/java/ch/epfl/sdp/blindly/ProfilePageTest.kt
}