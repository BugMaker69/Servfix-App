package com.example.graduationproject.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Message
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.graduationproject.R
import com.example.graduationproject.ui.theme.DarkBlue


data class BottomNavItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomAppBar(
    navController:NavController,
) {

    var selectedIconIndex by rememberSaveable { mutableIntStateOf(0) }

    val items = listOf(
        BottomNavItem(
//            title = "Home",
            title = stringResource(id = R.string.home),
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
        ),
        BottomNavItem(
            title = stringResource(id = R.string.favorite),
            selectedIcon = Icons.Filled.Favorite,
            unselectedIcon = Icons.Outlined.FavoriteBorder,
        ),


        BottomNavItem(
            title = stringResource(id = R.string.settings),
            selectedIcon = Icons.Filled.Settings,
            unselectedIcon = Icons.Outlined.Settings,
        ),
        BottomNavItem(
            title = stringResource(id = R.string.messages),
            selectedIcon = Icons.Filled.Message,
            unselectedIcon = Icons.Outlined.Message,
        )
    )
    NavigationBar(
        containerColor = Color.Transparent,
        contentColor = DarkBlue,
    ) {

        items.forEachIndexed { index, item ->

            NavigationBarItem(
                selected = selectedIconIndex == index,
                onClick = {
                    selectedIconIndex = index


                    navController.navigate(item.title)
                },
                icon = {
                    Icon(
                        imageVector = if (selectedIconIndex == index) item.selectedIcon else item.unselectedIcon,
                        contentDescription = item.title,
                        tint = Color.Black
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.White,
                    selectedTextColor = Color.Black
                ),
                label = { Text(text = item.title) },
                alwaysShowLabel = true,
            )
        }
    }
}



