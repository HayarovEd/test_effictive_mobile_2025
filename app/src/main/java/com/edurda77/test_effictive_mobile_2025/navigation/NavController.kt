package com.edurda77.test_effictive_mobile_2025.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.edurda77.favorite_screen.FavoriteScreenRoot
import com.edurda77.login_screen.LoginScreenRoot
import com.edurda77.main_screen.MainScreenRoot
import com.edurda77.profile_screen.ProfileScreenRoot
import com.edurda77.resources.R
import com.edurda77.resources.model.NavigationRoute
import com.edurda77.resources.model.TopLevelRoute
import com.edurda77.resources.uikit.UiBottomNavigation

@Composable
fun NavController(
    startDestination: NavigationRoute = NavigationRoute.Login,
) {
    val navController = rememberNavController()
    val routes = listOf(
        TopLevelRoute(
            stringResource(R.string.main),
            NavigationRoute.Main,
            ImageVector.vectorResource(R.drawable.main)
        ),
        TopLevelRoute(
            stringResource(R.string.favorite),
            NavigationRoute.Favorite,
            ImageVector.vectorResource(R.drawable.favorite)
        ),
        TopLevelRoute(
            stringResource(R.string.profile),
            NavigationRoute.Profile,
            ImageVector.vectorResource(R.drawable.profile)
        )
    )
    NavHost(navController = navController, startDestination = startDestination) {

        composable<NavigationRoute.Login> {
            LoginScreenRoot(
                onNavigateToMain = {
                    navController.navigate(NavigationRoute.Main)
                },
            )
        }
        composable<NavigationRoute.Main> {
            MainScreenRoot(
                bottomBarContent = {
                    UiBottomNavigation(
                        navController = navController,
                        routes = routes
                    )
                }
            )
        }
        composable<NavigationRoute.Favorite> {
            FavoriteScreenRoot (
                bottomBarContent = {
                    UiBottomNavigation(
                        navController = navController,
                        routes = routes
                    )
                }
            )
        }

        composable<NavigationRoute.Profile> {
            ProfileScreenRoot (
                bottomBarContent = {
                    UiBottomNavigation(
                        navController = navController,
                        routes = routes
                    )
                }
            )
        }
    }
}