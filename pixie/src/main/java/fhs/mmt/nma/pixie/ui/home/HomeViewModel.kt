package fhs.mmt.nma.pixie.ui.home

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import fhs.mmt.nma.pixie.data.Photographer
import fhs.mmt.nma.pixie.data.Post
import fhs.mmt.nma.pixie.samples.AllPosts

class HomeViewModel() : ViewModel() {
    val posts: List<Post> = AllPosts

    fun onUserClicked(author : Photographer, navController: NavController) {
        navController.navigate(route = "profile/${author.id}")
    }
}

