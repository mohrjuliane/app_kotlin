package fhs.mmt.nma.pixie.ui.home

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.material.Text
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import fhs.mmt.nma.pixie.data.Post
import fhs.mmt.nma.pixie.samples.providers.PostSampleProvider
import androidx.compose.material.Icon
import androidx.compose.material.icons.filled.Comment
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fhs.mmt.nma.pixie.ui.theme.*


@Composable
fun PostCard(post: Post) {
    Column(modifier = Modifier
        .background(color = background)) {
        Row(modifier = Modifier
            .padding(top = 8.dp, bottom = 8.dp)
            .width(width = 300.dp)) {
            Box(
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp)
                    .size(size = 48.dp)
                    .clip(shape = CircleShape)
                    .background(color = secondary)
                    .border(width = 1.5.dp, color = primary, shape = CircleShape)
            )
            Column(
                modifier = Modifier
                    .height(height = 48.dp), verticalArrangement = Arrangement.Center
            ) {
                Text("${post.author.name}")
                if(post.author.location != null) {
                    Text("${post.author.location}")
                }
            }
        }
        Box(
            modifier = Modifier
                .width(width = 300.dp)
                .height(height = 400.dp)
                .background(color = onBackground)
        )
        Row(modifier = Modifier
            .padding(top = 24.dp, bottom = 24.dp, start = 16.dp, end = 16.dp)
            .width(width = 268.dp)
            .height(height = 24.dp)
            , horizontalArrangement = Arrangement.SpaceBetween)
            {
                Row {
                    TextButton(onClick = { /* Do something! */ },
                        modifier = Modifier
                            .height(height = 24.dp)
                            .width(width = 24.dp)
                    ) {
                        Icon(Icons.Filled.FavoriteBorder, contentDescription = "Like",
                            modifier = Modifier.requiredSize(size = 24.dp), tint = onBackground)
                    }
                    Text("${post.likes}", modifier = Modifier.padding(start = 8.dp))
                }
                Row {
                    TextButton(onClick = { /* Do something! */ },
                        modifier = Modifier
                            .height(height = 24.dp)
                            .width(width = 24.dp)
                    ) {
                        Icon(Icons.Filled.Comment, contentDescription = "Like",
                            modifier = Modifier.requiredSize(size = 24.dp), tint = onBackground)
                    }
                    Text("${post.comments.size}", modifier = Modifier.padding(start = 8.dp))
                }
            }
    }
}



@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PostPreview(@PreviewParameter(PostSampleProvider::class) post: Post) {
    PixieTheme {
        PostCard(post = post)
    }
}





