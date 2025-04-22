package eu.pitlap.shared.navigation.destination.bottom_nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.TrendingUp
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Gamepad
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val label: String, val icon: ImageVector) {
    data object Home : BottomNavItem("home", "Home", Icons.Outlined.Home)
    data object Schedule : BottomNavItem("schedule", "Schedule", Icons.Default.CalendarToday)
    data object Standings : BottomNavItem("standings", "Standings", Icons.AutoMirrored.Default.TrendingUp)
    data object Trivia : BottomNavItem("trivia", "Trivia", Icons.Default.Gamepad)
}
