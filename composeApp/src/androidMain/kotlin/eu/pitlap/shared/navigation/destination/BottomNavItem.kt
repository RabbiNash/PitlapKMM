package eu.pitlap.shared.navigation.destination

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val label: String, val icon: ImageVector) {
    data object Schedule : BottomNavItem("schedule", "Schedule", Icons.Outlined.DateRange)
    data object Standings : BottomNavItem("standings", "Standings", Icons.Outlined.Person)
    data object Trivia : BottomNavItem("trivia", "Trivia", Icons.Outlined.Face)
}
