package com.example.moviebox.ui.screens.main_screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.moviebox.ui.composable.MovieBoxAppBar
import com.example.moviebox.ui.navigations.BottomNavigationItem
import com.example.moviebox.ui.navigations.Screens
import com.example.moviebox.ui.navigations.hasRoute
import com.example.moviebox.ui.screens.movie_detail_screen.MovieDetailScreen
import com.example.moviebox.ui.screens.now_playing_screen.NowPlayingScreen
import com.example.moviebox.ui.screens.search_screen.SearchScreen
import com.example.moviebox.ui.screens.top_rated_screen.TopRatedScreen
import com.example.moviebox.ui.screens.upcoming_screen.UpComingMovieScreen
import com.example.moviebox.ui.theme.MBTheme
import com.example.moviebox.ui.theme.MovieBoxTheme
import dagger.hilt.android.AndroidEntryPoint

private val navigationItems by lazy {
    listOf(
        BottomNavigationItem(
            "Home",
            Icons.Filled.Home,
            Icons.Outlined.Home,
            Screens.NowPlayingScreen
        ),
        BottomNavigationItem(
            "Top Rating",
            Icons.Filled.Star,
            Icons.Outlined.Star,
            Screens.TopRatingScreen
        ),
        BottomNavigationItem(
            "Upcoming",
            Icons.Filled.PlayArrow,
            Icons.Outlined.PlayArrow,
            Screens.UpcomingScreen
        )
    )
}


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieBoxTheme {
                val navController = rememberNavController()
                AppNavHost(navController)
            }
        }
    }
}

@Composable
private fun AppNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screens.NowPlayingScreen) {
        composable<Screens.NowPlayingScreen> {
            MainScreen(navController)
        }
        composable<Screens.MovieDetailScreen> {
            val args = it.toRoute<Screens.MovieDetailScreen>()
            MovieDetailScreen(movieId = args.movieId, navController)
        }
        composable<Screens.SearchScreen> {
            SearchScreen(navController)
        }
    }
}

@Composable
fun BottomNavigationBar(
    currentDestination: NavDestination?,
    navigationItems: List<BottomNavigationItem>,
    navController: NavHostController
) {
    NavigationBar(
        containerColor = MBTheme.colors.primary,
    ) {
        navigationItems.forEach { item ->
            NavigationBarItem(
                selected = currentDestination.hasRoute(item),
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = if (currentDestination.hasRoute(item)) item.selectedIcon else item.unSelectedIcon,
                        contentDescription = item.title
                    )
                }
            )
        }
    }
}

@Composable
fun MainScreen(navControllerMain: NavHostController) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MBTheme.colors.secondary,
        bottomBar = {
            BottomNavigationBar(
                currentDestination = currentDestination,
                navigationItems = navigationItems,
                navController = navController
            )
        },
        topBar = { MovieBoxAppBar("Movie Box") },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navControllerMain.navigate(Screens.SearchScreen)
                },
                containerColor = MBTheme.colors.lightBlue
            ) {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp) // Adjust size if needed
                )
            }
        }
    ) { innerPadding ->
        NavHost(navController = navController, startDestination = Screens.NowPlayingScreen) {
            composable<Screens.NowPlayingScreen> {
                NowPlayingScreen(Modifier.padding(innerPadding), navControllerMain)
            }
            composable<Screens.TopRatingScreen> {
                TopRatedScreen(Modifier.padding(innerPadding), navControllerMain)
            }
            composable<Screens.UpcomingScreen> {
                UpComingMovieScreen(Modifier.padding(innerPadding), navControllerMain)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MovieBoxTheme {
    }
}
