package eu.pitlap.shared.navigation.route

import kotlinx.serialization.Serializable

sealed interface Route {
    val route: String

    @Serializable
    data object BottomNavGraph : Route {
        override val route = "bottom_nav_graph"
    }

    @Serializable
    data object ScheduleNavGraph : Route {
        override val route = "schedule_nav_graph"
    }

    @Serializable
    data object HomeNavGraph : Route {
        override val route = "home_nav_graph"
    }
}
