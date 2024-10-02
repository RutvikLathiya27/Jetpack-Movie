package com.example.moviebox.ui.navigations

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute

data class BottomNavigationItem(
    val title : String,
    val selectedIcon : ImageVector,
    val unSelectedIcon : ImageVector,
    val route : Screens
)

fun NavDestination?.hasRoute(destination: BottomNavigationItem): Boolean =
    this?.hasRoute(destination.route::class) ?: false