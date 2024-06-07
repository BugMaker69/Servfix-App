package com.example.graduationproject.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Message
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.graduationproject.R
import com.example.graduationproject.presentation.common.UserType
import com.example.graduationproject.ui.theme.DarkBlue


data class BottomNavItem(
    val route:String,
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
)


@Composable
fun BottomAppBar(
    navController:NavController, userType:String
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val userItems = listOf(
        BottomNavItem(
            route=ServixScreens.HomeUser.name,
            title = stringResource(id = R.string.home),
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
        ),
        BottomNavItem(
            route = ServixScreens.Favorite.name,
            title = stringResource(id = R.string.favorite),
            selectedIcon = Icons.Filled.Favorite,
            unselectedIcon = Icons.Outlined.FavoriteBorder,
        ),


        BottomNavItem(
            route=ServixScreens.Settings.name,
            title = stringResource(id = R.string.settings),
            selectedIcon = Icons.Filled.Settings,
            unselectedIcon = Icons.Outlined.Settings,
        ),
        BottomNavItem(
            route=ServixScreens.ChatContactScreen.name,
            title = stringResource(id = R.string.messages),
            selectedIcon = Icons.Filled.Message,
            unselectedIcon = Icons.Outlined.Message,
        )
    )
    val providerItems = listOf(
        BottomNavItem(
            route=ServixScreens.HomeProvider.name,
            title = stringResource(id = R.string.home),
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
        ),

        BottomNavItem(
            route=ServixScreens.Settings.name,
            title = stringResource(id = R.string.settings),
            selectedIcon = Icons.Filled.Settings,
            unselectedIcon = Icons.Outlined.Settings,
        ),
        BottomNavItem(
            route=ServixScreens.ChatContactScreen.name,
            title = stringResource(id = R.string.messages),
            selectedIcon = Icons.Filled.Message,
            unselectedIcon = Icons.Outlined.Message,
        )
    )
    val items=if(userType==UserType.OwnerPerson.name) userItems else providerItems


    NavigationBar(
        modifier=Modifier.fillMaxWidth(),
        containerColor = Color.Transparent,
        contentColor = DarkBlue,
    ) {
        items.forEach { screen ->

            NavigationBarItem(
                selected = currentRoute == screen.route,
                onClick = {


                    navController.navigate(screen.route){
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                        launchSingleTop = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = if (currentRoute == screen.route) screen.selectedIcon else screen.unselectedIcon,
                        contentDescription = screen.title,
                        tint = DarkBlue
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.White,
                    selectedTextColor = Color.Black
                ),
                label = { Text(text = screen.title) },
                alwaysShowLabel = true,
            )
        }
    }
}



