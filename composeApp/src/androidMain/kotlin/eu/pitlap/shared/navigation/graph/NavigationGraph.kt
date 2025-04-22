package eu.pitlap.shared.navigation.graph

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.net.toUri
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import eu.pitlap.shared.articles.screens.ArticleDetail
import eu.pitlap.shared.home.screens.HomeScreen
import eu.pitlap.shared.home.state.HomeScreenEvent
import eu.pitlap.shared.navigation.destination.bottom_nav.BottomNavItem
import eu.pitlap.shared.navigation.destination.home.HomeNavDestination
import eu.pitlap.shared.navigation.destination.schedule.ScheduleNavDestination
import eu.pitlap.shared.navigation.route.Route
import eu.pitlap.shared.reaction.screen.RaceReactionScreen
import eu.pitlap.shared.schedule.models.SessionModel
import eu.pitlap.shared.schedule.screens.analysis.SessionDetailScreen
import eu.pitlap.shared.schedule.screens.analysis.TopSpeedScreen
import eu.pitlap.shared.schedule.screens.current.EventDetailScreen
import eu.pitlap.shared.schedule.screens.current.EventListScreen
import eu.pitlap.shared.schedule.screens.practice.PracticeResultScreen
import eu.pitlap.shared.schedule.screens.qualifying.QualifyingResultScreen
import eu.pitlap.shared.schedule.screens.race.RaceResultScreen
import eu.pitlap.shared.schedule.state.EventDetailScreenEvent
import eu.pitlap.shared.schedule.state.analysis.SessionDetailScreenEvent
import eu.pitlap.shared.standings.screens.StandingsListScreen
import eu.pitlap.shared.videos.screens.VideoDetail

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
        homeNavigation(navHostController = navHostController)
    }
}

fun NavGraphBuilder.bottomNavigation(navHostController: NavHostController) {
    navigation(
        startDestination = BottomNavItem.Home.route,
        route = Route.BottomNavGraph.route
    ) {
        composable(BottomNavItem.Home.route) {
            HomeScreen {
                when (it) {
                    is HomeScreenEvent.LoadVideo -> {
                        navHostController.navigate(HomeNavDestination.VideoDetails.createRoute(it.video))
                    }

                    is HomeScreenEvent.LoadArticle -> {
                        navHostController.navigate(HomeNavDestination.ArticleDetails.createRoute(it.article))
                    }

                    else -> Unit
                }
            }
        }

        composable(BottomNavItem.Schedule.route) {
            EventListScreen {
                navHostController.navigate(
                    ScheduleNavDestination.EventDetails.createRoute(
                        it.year.toInt(),
                        it.round
                    )
                )
            }
        }

        composable(BottomNavItem.Standings.route) {
            StandingsListScreen()
        }

        composable(BottomNavItem.Trivia.route) {
            RaceReactionScreen()
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
            EventDetailScreen(year = year, round = round) { event ->
                when (event) {
                    is EventDetailScreenEvent.LoadSessionDetail -> {
                        navHostController.navigate(
                            ScheduleNavDestination
                                .SessionDetail
                                .createRoute(event.year, event.round, event.sessionName)
                        )
                    }

                    else -> Unit
                }
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
            val sessionName =
                backStackEntry.arguments?.getString("sessionName") ?: return@composable

            PracticeResultScreen(year = year, round = round, sessionName = sessionName)
        }

        composable(
            route = ScheduleNavDestination.QualifyingResults.route,
            arguments = listOf(
                navArgument("year") { type = NavType.IntType },
                navArgument("round") { type = NavType.IntType },
            )
        ) { backStackEntry ->
            val year = backStackEntry.arguments?.getInt("year") ?: return@composable
            val round = backStackEntry.arguments?.getInt("round") ?: return@composable

            QualifyingResultScreen(year = year, round = round)
        }

        composable(
            route = ScheduleNavDestination.RaceResults.route,
            arguments = listOf(
                navArgument("year") { type = NavType.IntType },
                navArgument("round") { type = NavType.IntType },
            )
        ) { backStackEntry ->
            val year = backStackEntry.arguments?.getInt("year") ?: return@composable
            val round = backStackEntry.arguments?.getInt("round") ?: return@composable

            RaceResultScreen(year = year, round = round)
        }

        composable(
            route = ScheduleNavDestination.SessionDetail.route,
            arguments = listOf(
                navArgument("year") { type = NavType.IntType },
                navArgument("round") { type = NavType.IntType },
                navArgument("sessionName") { type = NavType.StringType },
            )
        ) { backStackEntry ->
            val year = backStackEntry.arguments?.getInt("year") ?: return@composable
            val round = backStackEntry.arguments?.getInt("round") ?: return@composable
            val sessionName =
                backStackEntry.arguments?.getString("sessionName") ?: return@composable

            SessionDetailScreen(
                sessionModel = SessionModel(round, year, sessionName)
            ) {
                when (it) {
                    is SessionDetailScreenEvent.LoadQualifying -> {
                        navHostController.navigate(
                            ScheduleNavDestination
                                .QualifyingResults
                                .createRoute(it.sessionModel.year, it.sessionModel.round)
                        )
                    }

                    is SessionDetailScreenEvent.LoadRaceResult -> {
                        navHostController.navigate(
                            ScheduleNavDestination
                                .RaceResults
                                .createRoute(it.sessionModel.year, it.sessionModel.round)                        )
                    }

                    is SessionDetailScreenEvent.LoadPracticeLaps -> {
                        navHostController.navigate(
                            ScheduleNavDestination
                                .PracticeResults
                                .createRoute(it.sessionModel.year, it.sessionModel.round, it.sessionModel.sessionName)
                        )
                    }

                    is SessionDetailScreenEvent.LoadTopSpeeds -> {
                        navHostController.navigate(
                            ScheduleNavDestination
                                .TopSpeed
                                .createRoute(it.sessionModel.year, it.sessionModel.round, it.sessionModel.sessionName)
                        )
                    }
                }
            }
        }

        composable(
            route = ScheduleNavDestination.TopSpeed.route,
            arguments = listOf(
                navArgument("year") { type = NavType.IntType },
                navArgument("round") { type = NavType.IntType },
                navArgument("sessionName") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val year = backStackEntry.arguments?.getInt("year") ?: return@composable
            val round = backStackEntry.arguments?.getInt("round") ?: return@composable
            val sessionName =
                backStackEntry.arguments?.getString("sessionName") ?: return@composable

            TopSpeedScreen(sessionModel = SessionModel(round, year, sessionName))
        }
    }
}

fun NavGraphBuilder.homeNavigation(navHostController: NavHostController) {
    navigation(
        startDestination = HomeNavDestination.ArticleDetails.route,
        route = Route.HomeNavGraph.route
    ) {
        composable(
            route = HomeNavDestination.ArticleDetails.route,
            arguments = listOf(
                navArgument("id") { type = NavType.StringType },
            )
        ) { backStackEntry ->
            val articleId = backStackEntry.arguments?.getString("id") ?: return@composable

            val context = LocalContext.current

            ArticleDetail(articleId = articleId) { url ->
                val intent = Intent(Intent.ACTION_VIEW, url.toUri())
                context.startActivity(intent)
            }
        }

        composable(
            route = HomeNavDestination.VideoDetails.route,
            arguments = listOf(
                navArgument("id") { type = NavType.StringType },
            )
        ) { backStackEntry ->
            val videoId = backStackEntry.arguments?.getString("id") ?: return@composable
            VideoDetail(videoId = videoId)
        }
    }
}






