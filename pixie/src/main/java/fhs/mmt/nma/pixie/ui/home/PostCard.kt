package fhs.mmt.nma.pixie.ui.home

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import fhs.mmt.nma.pixie.data.Post
import fhs.mmt.nma.pixie.samples.providers.PostSampleProvider
import fhs.mmt.nma.pixie.ui.theme.PixieTheme

@Composable
fun PostCard(post: Post) {
   Text(text = "TODO $post")
    Row {
        Box(modifier = Modifier
            .size(size = 48.dp)
            .clip(shape = CircleShape)
            .background(color = Color.LightGray)
        )
        Column {
            Text("Juliane Mohr")
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





