package fhs.mmt.nma.pixie.ui.profile

import android.content.res.Configuration
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddLocation
import androidx.compose.material.icons.filled.CatchingPokemon
import androidx.compose.material.icons.filled.NoPhotography
import androidx.compose.material.icons.filled.Room
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.google.accompanist.pager.ExperimentalPagerApi
import fhs.mmt.nma.pixie.data.Photographer
import fhs.mmt.nma.pixie.data.Post
import fhs.mmt.nma.pixie.samples.AllPosts
import fhs.mmt.nma.pixie.samples.FakeUsers
import fhs.mmt.nma.pixie.samples.providers.UserSampleProvider
import fhs.mmt.nma.pixie.ui.home.PostCard
import fhs.mmt.nma.pixie.ui.theme.PixieTheme

@ExperimentalFoundationApi
@Composable
fun ProfileScreen(user: Photographer, navController: NavController, userId: String) {
    LazyColumn(modifier = Modifier
        .padding(start = 8.dp, end = 8.dp)
        .background(MaterialTheme.colors.background)) {
            item {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp, bottom = 24.dp)) {
                Image(
                    painter = rememberImagePainter(user.picture),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(width = 80.dp, height = 80.dp)
                        .clip(shape = CircleShape)
                        .border(width = (1.5).dp, color = MaterialTheme.colors.primary, CircleShape)
                )
                val counts = GetCounts(user)
                Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
                    ProfileInformationCol(upperText = "${counts[0]}", lowerText = "Likes")
                    ProfileInformationCol(upperText = "${counts[1]}", lowerText = "Photos")
                    ProfileInformationCol(upperText = "${counts[2]}", lowerText = "Comments")
                }

            }
            Text(text = "${user.name}", color = MaterialTheme.colors.onBackground, style = MaterialTheme.typography.h2)
            LocationSocialMedia("${user.location}", "${user.instagram}")
            Text(text = "${user.bio}", color = MaterialTheme.colors.onBackground, style = MaterialTheme.typography.body2)
            DisplayPosts(user)

        }

    }
}

@ExperimentalFoundationApi
@Composable
fun DisplayPosts(user : Photographer) {
    val posts = AllPosts.filter { post -> post.author == user }

    LazyVerticalGrid(
        cells = GridCells.Fixed(3), modifier = Modifier.padding(top = 16.dp)
    ) {
        items(posts.size) { index ->

            Image(
                painter = rememberImagePainter(posts[index].photos[0].url),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.0f / 1.0f)
                    .padding(all = (0.5).dp)
            )
        }
    }

}

@Composable
fun LocationSocialMedia(location : String, socialMedia : String) {
    Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier
        .fillMaxWidth()
        .padding(top = 16.dp, bottom = 16.dp)) {
        Row() {
            Icon(
                imageVector = Icons.Filled.Room,
                contentDescription = "Location",
                modifier = Modifier
                    .size(20.dp)
                    .padding(end = 8.dp)
            )
            Text(text = location, color = MaterialTheme.colors.onBackground, style = MaterialTheme.typography.body1)
        }
        Row() {
            Icon(
                imageVector = Icons.Filled.CatchingPokemon,
                contentDescription = "Instagram",
                modifier = Modifier
                    .size(20.dp)
                    .padding(end = 8.dp)
            )
            Text(text = socialMedia, color = MaterialTheme.colors.onBackground, style = MaterialTheme.typography.body1)
        }
    }
}
@Composable
fun ProfileInformationCol(upperText : String, lowerText : String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = upperText, color = MaterialTheme.colors.onBackground, style = MaterialTheme.typography.h2)
        Text(text = lowerText, color = MaterialTheme.colors.onBackground)
    }
}

fun GetCounts(user : Photographer) : List<String> {
    var counts = mutableListOf<Int>(0,0,0)

    AllPosts.forEach { post ->
        if(post.author == user) {
            counts[0] = counts[0] + post.likes /*count of likes*/
            counts[1] = counts[1] + post.photos.size /*count of photos*/
            counts[2] = counts[2] + post.comments.size /*count of comments*/
        }
    }

    var result = mutableListOf<String>("${counts[0]}", "${counts[1]}", "${counts[2]}")

    if(counts[0]/1000000 > 0) {
        val cut = (counts[0] / 1000000).toInt()
        result[0] = "${cut}M+"
    }


    return result
}

@ExperimentalFoundationApi
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ProfilePreview(@PreviewParameter(UserSampleProvider::class) user: Photographer) {
    PixieTheme {
        ProfileScreen(user = FakeUsers[0], rememberNavController(), "hallo")
    }
}

