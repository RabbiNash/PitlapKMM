package eu.pitlap.shared.navigation

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import eu.pitlap.shared.navigation.destination.BottomNavItem

@Composable
fun BottomBar(navController: NavController) {
    val items = listOf(
        BottomNavItem.Schedule,
        BottomNavItem.Standings,
        BottomNavItem.House
    )
    
    NavigationBar {
        items.forEach { item ->
            val currentRoute = navController.currentBackStackEntry?.destination?.route
            
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) },
                selected = currentRoute == item.route,
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}
