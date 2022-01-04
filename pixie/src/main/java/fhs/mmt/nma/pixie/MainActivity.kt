package fhs.mmt.nma.pixie

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.pager.ExperimentalPagerApi
import fhs.mmt.nma.pixie.data.Photographer
import fhs.mmt.nma.pixie.data.User
import fhs.mmt.nma.pixie.samples.AllUsers
import fhs.mmt.nma.pixie.samples.FakeUsers
import fhs.mmt.nma.pixie.samples.providers.UserSampleProvider
import fhs.mmt.nma.pixie.ui.home.HomeScreen
import fhs.mmt.nma.pixie.ui.home.isSelected
import fhs.mmt.nma.pixie.ui.profile.ProfileScreen
import fhs.mmt.nma.pixie.ui.theme.PixieTheme

@ExperimentalPagerApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PixieTheme {
                val navController = rememberNavController()
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Scaffold(
                        topBar = {
                            TopAppBar(title = {
                                Text(
                                    "Pixie",
                                    style = MaterialTheme.typography.h1,
                                    color = MaterialTheme.colors.onBackground
                                )
                            }, backgroundColor = MaterialTheme.colors.surface)
                        }, bottomBar = { BottomNavigationBar(navController) }, content = {
                            NavHost(navController = navController, startDestination = "home") {
                                composable(route = "home") {
                                    HomeScreen(navController)
                                }
                                composable(
                                    "profile/{userId}",
                                    arguments = listOf(navArgument("userId") { type = NavType.StringType })
                                ) {
                                    val userId = it.arguments?.getString("userId")
                                    ProfileScreen(user = GetUserByIndex(AllUsers, userId!!.toInt()), navController, userId.toString())
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}

fun GetUserByIndex(users : List<Photographer>, userId: Int) : Photographer{
    var result : Photographer = users[0]
    users.forEach { user ->
        if(user.id == userId) {
            result = user
        }
    }
    return result
}

@ExperimentalPagerApi
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun MainPreview() {
    PixieTheme {
        MainActivity()
    }
}

var selectedItem = 0
val items = listOf("Home", "Search", "Favorite", "Profile", "Settings")

@ExperimentalPagerApi
@Composable
fun BottomNavigationBar(navController : NavController) {
    BottomNavigation(backgroundColor = MaterialTheme.colors.surface) {
        BottomNavigationItem(
            icon = {
                Icon(
                    Icons.Filled.Home,
                    contentDescription = null,
                    tint = isSelected(selectedItem == 0)
                )
            },
            selected = selectedItem == 0,
            onClick = {
                navController.navigate("home")}
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    Icons.Filled.Search,
                    contentDescription = null,
                    tint = isSelected(selectedItem == 1)
                )
            },
            selected = selectedItem == 1,
            onClick = { selectedItem = 1 }
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    Icons.Filled.Bookmark,
                    contentDescription = null,
                    tint = isSelected(selectedItem == 2)
                )
            },
            selected = selectedItem == 2,
            onClick = { selectedItem = 2 }
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    Icons.Filled.Person,
                    contentDescription = null,
                    tint = isSelected(selectedItem == 3)
                )
            },
            selected = selectedItem == 3,
            onClick = { selectedItem = 3 }
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    Icons.Filled.Settings,
                    contentDescription = null,
                    tint = isSelected(selectedItem == 4)
                )
            },
            selected = selectedItem == 4,
            onClick = { selectedItem = 4 }
        )

    }
}