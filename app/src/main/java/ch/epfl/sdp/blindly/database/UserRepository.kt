package ch.epfl.sdp.blindly.database

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import ch.epfl.sdp.blindly.user.User
import ch.epfl.sdp.blindly.user.User.Companion.toUser
import ch.epfl.sdp.blindly.user.storage.UserCache
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

/**
 * The main access to Firebase firestore
 *
 * @property db the instance of FirebaseFirestore
 * @property userCache the local cache
 */
class UserRepository @Inject constructor(
    private val db: FirebaseFirestore,
    private val userCache: UserCache
) {

    companion object {
        private const val TAG = "UserRepository"
        private const val USER_COLLECTION: String = "usersMeta"
    }

    /**
     * Given a uid, if the user is cached locally return this user, otherwise
     * look for the user in firestore and update the cache
     *
     * @param uid the uid of the user to retrieve
     * @return the user with the corresponding uid or null if they doesn't exist
     */
    @RequiresApi(Build.VERSION_CODES.N)
    suspend fun getUser(uid: String): User? {
        val cached: User? = userCache.get(uid)
        if (cached != null) {
            Log.d(TAG, "Found user with uid: $uid in cache")
            return cached
        }
        return refreshUser(uid)
    }

    /**
     * Look for the user with the corresponding uid in firestore and store it in the local cache
     *
     * @param uid the uid of the user to retrieve in firestore
     * @return the user with the corresponding uid or null if he/she/it doesn't exist
     */
    suspend fun refreshUser(uid: String): User? {
        return try {
            val freshUser = db.collection(USER_COLLECTION)
                .document(uid).get().await().toUser()
            if (freshUser != null) {
                Log.d(TAG, "Put User \"$uid\" in local cache")
                userCache.put(uid, freshUser)
            }
            Log.d(TAG, "Retrieve User \"$uid\" in firestore")
            return freshUser
        } catch (e: Exception) {
            Log.e(TAG, "Error getting user details", e)
            null
        }
    }


    @RequiresApi(Build.VERSION_CODES.N)
    private suspend fun <T> updateLocalCache(uid: String, field: String, newValue: T) {
        val user = userCache.get(uid)
        if(user != null) {
            Log.d(TAG, "Updated user in local cache")
            userCache.put(uid, User.updateUser(user, field, newValue))
        }
        else {
            refreshUser(uid)
        }
    }

    /**
     * Update a given field of the user's information (and call refreshUser to update or set the
     * user in the local cache)
     *
     * @param uid the uid of the user to update
     * @param field the field of the value to change inside the database
     * @param newValue the new value to set for the user
     */

    @RequiresApi(Build.VERSION_CODES.N)
    suspend fun <T> updateProfile(uid: String, field: String, newValue: T) {
        if (newValue !is String && newValue !is List<*> && newValue !is Int)
            throw IllegalArgumentException("Expected String, List<String> or Int")

        db.collection(USER_COLLECTION)
            .document(uid)
            .update(field, newValue)
        Log.d(TAG, "Updated user")
        //Put updated value into the local cache
        updateLocalCache(uid, field, newValue)
    }

    /**
     * Get the collection reference of the database of users.
     *
     * @return the reference of the database
     */
    fun getCollectionReference(): CollectionReference {
        return db.collection(USER_COLLECTION)
    }
}