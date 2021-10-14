package fhs.mmt.nma.pixie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import fhs.mmt.nma.pixie.data.*
import fhs.mmt.nma.pixie.ui.home.PostCard
import fhs.mmt.nma.pixie.ui.home.PostPreview
import fhs.mmt.nma.pixie.ui.theme.PixieTheme

class PostCardComponent : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PixieTheme {
                // A surface container using the 'background' color from the theme
                val photography = Photography("https://wiki.mediacube.at/wiki/index.php?title=Hauptseite")
                val photos = listOf<Photography>(photography)
                val me = Photographer(id = 0, name = "Juliane Mohr", picture = "https://kotlinlang.org", bio = "My name is Juliane")
                data class Photographer(
                    override val id: Int,
                    override val name: String,
                    override val picture: String,
                    val bio: String,
                    val profile: String? = null,
                    val location: String? = null,
                    val instagram: String? = null,
                ) : User

                val comment = Comment(me, "Nice picture")
                val commentList = listOf(comment)


                val post1 = Post(photos = photos, author = me, likes = 10, comments = commentList)
                PostPreview(post = post1)
            }
        }
    }
}