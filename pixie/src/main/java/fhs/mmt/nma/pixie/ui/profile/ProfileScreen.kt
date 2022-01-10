package fhs.mmt.nma.pixie.ui.profile

import android.content.Intent
import android.content.res.Configuration
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import fhs.mmt.nma.pixie.data.Photographer
import fhs.mmt.nma.pixie.data.Post
import fhs.mmt.nma.pixie.samples.providers.UserSampleProvider
import fhs.mmt.nma.pixie.ui.theme.PixieTheme

@ExperimentalFoundationApi
@Composable
fun ProfileScreen(viewModel: ProfileViewModel, navController: NavController) {
    val user = viewModel.uiState.collectAsState().value
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { navController.navigateUp() }
                )
                Text(
                    user.name,
                    style = MaterialTheme.typography.h1,
                    color = MaterialTheme.colors.onBackground,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }, backgroundColor = MaterialTheme.colors.surface)
        }, content = {
            Column {
                LazyColumn {
                    item {
                        Column(
                            modifier = Modifier
                                .padding(start = 8.dp, end = 8.dp)
                                .background(MaterialTheme.colors.background)
                        ) {

                            Row(
                                verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 24.dp, bottom = 24.dp)
                            ) {
                                Image(
                                    painter = rememberImagePainter(user.picture),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .size(width = 80.dp, height = 80.dp)
                                        .clip(shape = CircleShape)
                                        .border(
                                            width = (1.5).dp,
                                            color = MaterialTheme.colors.primary,
                                            CircleShape
                                        )
                                )

                                Row(
                                    horizontalArrangement = Arrangement.SpaceEvenly,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    ProfileInformationCol(upperText = FormatLikes(user.likes), lowerText = "Likes")
                                    ProfileInformationCol(upperText = "${user.photos}", lowerText = "Photos")
                                    ProfileInformationCol(
                                        upperText = "${user.comments}",
                                        lowerText = "Comments"
                                    )
                                }

                            }
                            Text(
                                text = user.name,
                                color = MaterialTheme.colors.onBackground,
                                style = MaterialTheme.typography.h2
                            )
                            LocationSocialMedia("${user.location}", "${user.instagram}")
                            Text(
                                text = user.bio,
                                color = MaterialTheme.colors.onBackground,
                                style = MaterialTheme.typography.body2
                            )
                        }
                    }
                }

                DisplayPosts(user.posts)
            }
        }
    )


}

fun FormatLikes(number : Int) : String {
    var result = ""
    result = if(number / 1_000_000f > 0) {
        String.format("%.0fM+", number / 1_000_000f)
    } else {
        number.toString()
    }
    return result
}

@ExperimentalFoundationApi
@Composable
fun DisplayPosts(posts : List<Post>) {

    LazyVerticalGrid(
        cells = GridCells.Fixed(3), modifier = Modifier.padding(top = 16.dp, start = 8.dp, end = 8.dp)
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
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Filled.Room,
                contentDescription = "Location",
                modifier = Modifier
                    .size(24.dp)
                    .padding(end = 8.dp)
            )
            Text(text = location, color = MaterialTheme.colors.onBackground, style = MaterialTheme.typography.body1)
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Filled.CatchingPokemon,
                contentDescription = "Instagram",
                modifier = Modifier
                    .size(24.dp)
                    .padding(end = 8.dp)
            )
            val context = LocalContext.current
            Text(text = socialMedia, color = MaterialTheme.colors.onBackground, style = MaterialTheme.typography.body1,
                modifier = Modifier
                    .clickable {
                        val intent = Intent.parseUri("https://www.instagram.com/$socialMedia", 0)
                        context.startActivity(intent)
                    })
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


@ExperimentalFoundationApi
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ProfilePreview(@PreviewParameter(UserSampleProvider::class) user: Photographer) {
    PixieTheme {
        ProfileScreen(viewModel = viewModel(), rememberNavController())
    }
}

