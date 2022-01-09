package fhs.mmt.nma.pixie.ui.profile

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import fhs.mmt.nma.pixie.data.Photographer
import fhs.mmt.nma.pixie.data.Post

class ProfileViewModel(private val state: SavedStateHandle) : ViewModel() {
    private val data = state.getLiveData<Photographer>("user")
}

data class User(
    val userId: Int,
    val name: String,
    val picture: String,
    val location: String,
    val instagram: String,
    val bio: String,
    val posts: List<Post>)