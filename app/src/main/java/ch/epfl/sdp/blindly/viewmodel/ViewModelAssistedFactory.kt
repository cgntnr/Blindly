package ch.epfl.sdp.blindly.viewmodel

import android.os.Bundle
import androidx.savedstate.SavedStateRegistryOwner
import dagger.assisted.AssistedFactory

@AssistedFactory
interface ViewModelAssistedFactory {
    fun create(owner: SavedStateRegistryOwner, bundle: Bundle): ViewModelFactory
}