package fhs.mmt.nma.pixie.ui.home

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.Modifier
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fhs.mmt.nma.pixie.data.Post
import fhs.mmt.nma.pixie.samples.AllPosts
import fhs.mmt.nma.pixie.ui.theme.PixieTheme
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import java.nio.channels.spi.AbstractSelectableChannel



@ExperimentalPagerApi
@Composable
fun isSelected(selected: Boolean): Color {
    if(selected) {
        return MaterialTheme.colors.primary;
    }
    else
    {
        return MaterialTheme.colors.onBackground;
    }
}

@ExperimentalPagerApi
@Composable
fun HomeScreen(vm: HomeViewModel, navController: NavController) {

    val state = vm.uiState.collectAsState().value
    //launchedEffect, when it, homeffect navigate to user ausfrufen und zum user navigieren
    //auf button vm.setEvent(Homevent.userProfileclicked)

    LaunchedEffect(vm, navController) {
        state.effect {
            when (it) {
                is Effect -> {
                    Effect.NavigateToUser()
                    navController.navigate(route = "profile/${author.id}")
                }
            }
        }
    }

    if(state.loading) {
        CircularProgressIndicator()
    } else {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.background(MaterialTheme.colors.background)) {

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier
                    .background(color = MaterialTheme.colors.background)
                    .padding(start = 8.dp, end = 8.dp)
            ) {

                items(vm.posts) { currentPost ->
                    Card {
                        PostCard(post = currentPost, navController, onClick = {vm.setEvent(Event.onUserClicked(currentPost.author.id))})
                    }
                }
            }
        }
    }
}


@ExperimentalPagerApi
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HomePreview() {
    PixieTheme {
        val vm : HomeViewModel = viewModel()
        HomeScreen(vm, navController = rememberNavController())
    }
}


