package fhs.mmt.nma.pixie.ui.home

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.material.Text
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import fhs.mmt.nma.pixie.data.Post
import fhs.mmt.nma.pixie.samples.providers.PostSampleProvider
import androidx.compose.material.Icon
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Comment
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.NoPhotography
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Bottom
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.google.accompanist.pager.*
import fhs.mmt.nma.pixie.data.Comment
import fhs.mmt.nma.pixie.data.Photographer
import fhs.mmt.nma.pixie.ui.theme.*
import org.intellij.lang.annotations.JdkConstants

@ExperimentalPagerApi
@Composable
fun PostCard(post: Post) {

    Column(modifier = Modifier
        .background(color = MaterialTheme.colors.surface)) {
        AuthorSection(author = post.author)

        val pagerState = rememberPagerState()

        if(pagerState.pageCount > 1) {
            Box(modifier = Modifier
                .offset(y = 40.dp)
                .padding(end = 8.dp)
                .zIndex(2F)
                .clip(
                    RoundedCornerShape(topEnd = 14.dp, topStart = 14.dp, bottomEnd = 14.dp, bottomStart = 14.dp)
                )
                .background(MaterialTheme.colors.secondary)
                .padding(all = 8.dp)
                .align(alignment = End)){
                Text(text = "${pagerState.currentPage+1} / ${pagerState.pageCount}", style = MaterialTheme.typography.caption)
            }
        }

        HorizontalPager(count = post.photos.size, state = pagerState) { page ->

                val painter = rememberImagePainter(post.photos[page].url)

                when(painter.state) {

                    is ImagePainter.State.Loading ->  Box(modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(4.0f / 3.0f),
                        contentAlignment = Center){
                        CircularProgressIndicator()
                    }
                    is ImagePainter.State.Success -> {
                    }
                    is ImagePainter.State.Error -> Box(modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(4.0f / 3.0f)
                        .background(Color(0xFFECECEC)),
                    contentAlignment = Center) {
                        Icon(
                            imageVector = Icons.Default.NoPhotography,
                            contentDescription = "Error",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    is ImagePainter.State.Empty -> Box(modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFECECEC))
                        .aspectRatio(4.0f / 3.0f), contentAlignment = Center){
                        Icon(imageVector = Icons.Default.CameraAlt, contentDescription = "Empty image",
                            modifier = Modifier.size(24.dp))
                    }
            }

            Column {
                Image(painter = painter, contentDescription = null, contentScale = ContentScale.Crop, modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(4.0f / 3.0f)
                )

            }
        }
        if(pagerState.pageCount > 1) {
            HorizontalPagerIndicator(activeColor = MaterialTheme.colors.secondary, pagerState = pagerState, modifier = Modifier
                .align(alignment = CenterHorizontally)
                .padding(top = 8.dp))
        }

        ActionSection(likes = post.likes, commentsCount = post.comments.size)
        CommentSection(comments = post.comments)
    }
}

@ExperimentalPagerApi
@Composable
fun ActionSection(likes: Int, commentsCount: Int) {
    Row(modifier = Modifier
        .padding(top = 24.dp, bottom = 24.dp, start = 16.dp, end = 16.dp)
        .fillMaxWidth()
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
                    modifier = Modifier.requiredSize(size = 24.dp), tint = MaterialTheme.colors.onBackground)
            }
            Text("${likes}", color = MaterialTheme.colors.onBackground, modifier = Modifier
                .padding(start = 8.dp)
                .align(Alignment.CenterVertically), style = MaterialTheme.typography.caption)
        }
        Row {
            TextButton(onClick = { /* Do something! */ },
                modifier = Modifier
                    .height(height = 24.dp)
                    .width(width = 24.dp)
            ) {
                Icon(Icons.Filled.Comment, contentDescription = "Like",
                    modifier = Modifier.requiredSize(size = 24.dp), tint = MaterialTheme.colors.onBackground)
            }
            Text("${commentsCount}", color = MaterialTheme.colors.onBackground, modifier = Modifier
                .padding(start = 8.dp)
                .align(Alignment.CenterVertically), style = MaterialTheme.typography.caption)
        }
    }
}

@ExperimentalPagerApi
@Composable
fun AuthorSection(author: Photographer) {
    Row(modifier = Modifier
        .padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 8.dp)
        .fillMaxWidth()) {
        Image(
            painter = rememberImagePainter(author.picture),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(width = 48.dp, height = 48.dp)
                .clip(shape = CircleShape)
                .border(width = (1.5).dp, color = MaterialTheme.colors.primary, CircleShape)
        )
        Column(
            modifier = Modifier
                .height(height = 48.dp)
                .padding(start = 8.dp),
            verticalArrangement = Arrangement.Center

        ) {
            Text(author.name, color = MaterialTheme.colors.onBackground, style = MaterialTheme.typography.h2)
            if(author.location != null) {
                Text(author.location, color = MaterialTheme.colors.onBackground, style = MaterialTheme.typography.body2)
            }
        }
    }
}


@ExperimentalPagerApi
@Composable
fun CommentSection(comments: List<Comment>) {
    //val opened = remember {mutableStateOf(false)}

    if(comments.isNotEmpty()) {
        Column(modifier = Modifier.padding( start = 16.dp, end = 16.dp)) {
            Comment(comment = comments[comments.size-1])
            if(comments.size > 1) {
                Comment(comment = comments[comments.size-2])
            }
            TextButton(onClick = { /*ShowAllComments(comments = comments*/ }) {
                Text("Show all ${comments.size} comments", style = MaterialTheme.typography.button)
            }
        }
    }
}

@ExperimentalPagerApi
@Composable
fun Comment(comment: Comment) {
    Row {
        Text(text = comment.author.name, style = MaterialTheme.typography.h2, color = MaterialTheme.colors.onBackground)
        Text(text = comment.message, style = MaterialTheme.typography.body2, color = MaterialTheme.colors.onBackground, modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, bottom = 10.dp))
    }
}

@ExperimentalPagerApi
@Composable
fun ShowAllComments(comments: List<Comment>) {
    for (element in comments) {
        Comment(element)
    }
}


@ExperimentalPagerApi
@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PostPreview(@PreviewParameter(PostSampleProvider::class) post: Post) {
    PixieTheme {
        PostCard(post = post)
    }
}





