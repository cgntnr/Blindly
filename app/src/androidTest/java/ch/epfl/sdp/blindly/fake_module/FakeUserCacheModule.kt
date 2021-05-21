package ch.epfl.sdp.blindly.fake_module

import ch.epfl.sdp.blindly.dependency_injection.UserCacheModule
import ch.epfl.sdp.blindly.fake_module.FakeUserHelperModule.Companion.TEST_UID
import ch.epfl.sdp.blindly.location.AndroidLocationService.Companion.createLocationTableEPFL
import ch.epfl.sdp.blindly.user.User
import ch.epfl.sdp.blindly.user.storage.UserCache
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import org.mockito.Mockito
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [UserCacheModule::class]
)
open class FakeUserCacheModule {
    companion object {
        private const val uid = TEST_UID
        private const val username = "Jane Doe"
        private const val usernameUpdated = "Jack"
        private val location = createLocationTableEPFL() // Ecublens, Switzerland
        private const val MULHOUSE_LAT = 47.749
        private const val MULHOUSE_LON = 7.335
        private val locationUpdated = listOf(MULHOUSE_LAT, MULHOUSE_LON) // Mulhouse, France
        private const val birthday = "01.01.2001"
        private const val gender = "Woman"
        private const val genderUpdated = "Man"
        private val sexualOrientations = listOf("Asexual")
        private val sexualOrientationsUpdated = listOf("Asexual", "Bisexual")
        private const val showMe = "Everyone"
        private const val showMeUpdated = "Women"
        private val passions = listOf("Coffee", "Tea")
        private val passionsUpdated = listOf("Coffee", "Tea", "Movies", "Brunch")
        private const val radius = 150
        private const val radiusUpdated = 50
        private val matches: List<String> = listOf("a1", "b2")
        private val likes: List<String> = listOf("c3", "d4")
        private const val recordingPath =
            "Recordings/OKj1UxZao3hIVtma95gWZlner9p1-PresentationAudio.amr"
        private val ageRange = listOf(30, 50)
        private val ageRangeUpdated = listOf(40, 50)
        val fakeUser = User.Builder()
            .setUid(uid)
            .setUsername(username)
            .setLocation(location)
            .setBirthday(birthday)
            .setGender(gender)
            .setSexualOrientations(sexualOrientations)
            .setShowMe(showMe)
            .setPassions(passions)
            .setRadius(radius)
            .setMatches(matches)
            .setLikes(likes)
            .setRecordingPath(recordingPath)
            .setAgeRange(ageRange)
            .build()
        val fakeUserUpdated = User.Builder()
            .setUid(uid)
            .setUsername(usernameUpdated)
            .setLocation(locationUpdated)
            .setBirthday(birthday)
            .setGender(genderUpdated)
            .setSexualOrientations(sexualOrientationsUpdated)
            .setShowMe(showMeUpdated)
            .setPassions(passionsUpdated)
            .setRadius(radiusUpdated)
            .setMatches(matches)
            .setLikes(likes)
            .setRecordingPath(recordingPath)
            .setAgeRange(ageRangeUpdated)
            .build()
    }

    @Singleton
    @Provides
    open fun provideUserCache(): UserCache {
        val userCache = Mockito.mock(UserCache::class.java)
        Mockito.`when`(userCache.get(TEST_UID)).thenReturn(fakeUser)
        return userCache
    }
}