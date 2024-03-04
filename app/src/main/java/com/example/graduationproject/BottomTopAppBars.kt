package com.example.graduationproject

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.graduationproject.ui.theme.DarkBlue


data class BottomNavItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomAppBar(
    modifier: Modifier = Modifier,
    onBottomNavigationItemClick: (String) -> Unit,
) {
    //  Filled Icon When Its Selected Else Use OutLined

    var selectedIconIndex by remember { mutableStateOf(0) }

    val items = listOf(
        BottomNavItem(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
        ),
        BottomNavItem(
            title = "Favorite",
            selectedIcon = Icons.Filled.Favorite,
            unselectedIcon = Icons.Outlined.FavoriteBorder,
        ),
        BottomNavItem(
            title = "Settings",
            selectedIcon = Icons.Filled.Settings,
            unselectedIcon = Icons.Outlined.Settings,
        ),
    )
    NavigationBar(
        containerColor = DarkBlue,
        contentColor = Color.White,
    ) {

        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedIconIndex == index,
                onClick = {
                    selectedIconIndex = index
                    //  Navigation
//                      navController.navigate(item.title)
                    onBottomNavigationItemClick(item.title)
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
                    selectedTextColor = Color.White
                ),
                label = { Text(text = item.title) },
                alwaysShowLabel = false,
            )
        }

    }
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(
    modifier: Modifier = Modifier,
    onNotificationClick: () -> Unit,
    onMessageClick: () -> Unit,
) {
    CustomTopAppBar(
        title = "Servfix",
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = DarkBlue,
            actionIconContentColor = Color.White,
            titleContentColor = Color.White
        ),
        actions = {
            IconButton(onClick = onNotificationClick) {
                Icon(imageVector = Icons.Filled.Notifications, contentDescription = "Notifications")
            }
            IconButton(onClick = onMessageClick) {
                Icon(imageVector = Icons.Filled.Message, contentDescription = "Message")
            }
        }
    )
}