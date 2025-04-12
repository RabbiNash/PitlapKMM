package eu.pitlap.shared.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import eu.pitlap.shared.navigation.destination.BottomNavItem
import eu.pitlap.shared.navigation.destination.ScheduleNavDestination
import eu.pitlap.shared.schedule.screens.current.EventDetailScreen
import eu.pitlap.shared.schedule.screens.current.EventListScreen
import eu.pitlap.shared.schedule.screens.practice.PracticeResultScreen
import eu.pitlap.shared.standings.screens.StandingsListScreen

@Composable
fun PitlapNavHost(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
) {
    NavHost(
        navController = navHostController,
        startDestination = Route.BottomNavGraph.route,
        modifier = modifier
    ) {
        bottomNavigation(navHostController = navHostController)
        scheduleNavigation(navHostController = navHostController)
    }
}

fun NavGraphBuilder.bottomNavigation(navHostController: NavHostController) {
    navigation(
        startDestination = BottomNavItem.Schedule.route,
        route = Route.BottomNavGraph.route
    ) {
        composable(BottomNavItem.Schedule.route) {
            EventListScreen {
                navHostController.navigate(ScheduleNavDestination.EventDetails.createRoute(it.year.toInt(), it.round))
            }
        }
        composable(BottomNavItem.Standings.route) {
            StandingsListScreen()
        }
    }
}

fun NavGraphBuilder.scheduleNavigation(navHostController: NavHostController) {
    navigation(
        startDestination = ScheduleNavDestination.EventDetails.route,
        route = Route.ScheduleNavGraph.route
    ) {
        composable(
            route = ScheduleNavDestination.EventDetails.route,
            arguments = listOf(
                navArgument("year") { type = NavType.IntType },
                navArgument("round") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val year = backStackEntry.arguments?.getInt("year") ?: return@composable
            val round = backStackEntry.arguments?.getInt("round") ?: return@composable
            EventDetailScreen(year = year, round = round) { event, sessionName ->
                navHostController
                    .navigate(
                        ScheduleNavDestination
                            .PracticeResults
                            .createRoute(event.year.toInt(), event.round, sessionName)
                    )
            }
        }

        composable(
            route = ScheduleNavDestination.PracticeResults.route,
            arguments = listOf(
                navArgument("year") { type = NavType.IntType },
                navArgument("round") { type = NavType.IntType },
                navArgument("sessionName") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val year = backStackEntry.arguments?.getInt("year") ?: return@composable
            val round = backStackEntry.arguments?.getInt("round") ?: return@composable
            val sessionName = backStackEntry.arguments?.getString("sessionName") ?: return@composable

            PracticeResultScreen(year = year, round = round, sessionName = sessionName)
        }
    }
}




