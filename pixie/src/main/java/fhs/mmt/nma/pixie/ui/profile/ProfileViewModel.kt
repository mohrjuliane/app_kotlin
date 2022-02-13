package fhs.mmt.nma.pixie.ui.profile

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import fhs.mmt.nma.pixie.data.Post
import fhs.mmt.nma.pixie.samples.AllPosts
import fhs.mmt.nma.pixie.samples.AllUsers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.lang.IllegalStateException

class ProfileViewModel(state: SavedStateHandle) : ViewModel() {
    private val userId: Int = state.get("userId") ?: throw IllegalStateException("Missing argument")
    private val user = AllUsers.first { it.id == userId }

    private val _uiState = MutableStateFlow(User(loading = true, photos = 0, name = "", picture = "", comments = 0, likes = 0, bio = "", posts = emptyList(), instagram = null, location = null))
    val uiState = _uiState.asStateFlow()

    init {
        fillInProfile()
    }

    private fun fillInProfile() {
        val userPosts = AllPosts.filter { it.author.id == userId }
        val photos = userPosts.sumOf { it.photos.size }
        val likes = userPosts.sumOf { it.likes }
        val comments = userPosts.sumOf { it.comments.size }

        _uiState.update {
            it.copy(loading = false, photos = photos, name = user.name, picture = user.picture, comments = comments, likes = likes, bio = user.bio, posts = userPosts, instagram = user.instagram, location = user.location)
        }
    }
}


data class User(
    val loading: Boolean,
    val photos: Int,
    val name: String,
    val picture: String,
    val location: String?,
    val instagram: String?,
    val comments: Int,
    val likes: Int,
    val bio: String,
    val posts: List<Post>)