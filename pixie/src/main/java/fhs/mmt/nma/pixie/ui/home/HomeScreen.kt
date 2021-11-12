package fhs.mmt.nma.pixie.ui.home

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.Modifier
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fhs.mmt.nma.pixie.data.Post
import fhs.mmt.nma.pixie.samples.AllPosts
import fhs.mmt.nma.pixie.ui.theme.PixieTheme

@Composable
fun HomeScreen(posts: List<Post> = AllPosts) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.background(MaterialTheme.colors.background)) {
        TopAppBar(title = {Text("Pixie",style = MaterialTheme.typography.h1, color = MaterialTheme.colors.onBackground)}, backgroundColor = MaterialTheme.colors.surface)
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier
            .background(color = MaterialTheme.colors.background)
            .padding(start = 8.dp, end = 8.dp)) {

            items(posts) { currentPost ->
                Card {
                    PostCard(post = currentPost)
                }

            }
        }
    }


}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HomePreview() {
    PixieTheme {

        HomeScreen()
    }
}

