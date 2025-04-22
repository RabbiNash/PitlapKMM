package eu.pitlap.shared.schedule.screens.analysis

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import eu.pitlap.shared.schedule.models.SessionDetailEnum
import eu.pitlap.shared.schedule.models.SessionModel
import eu.pitlap.shared.schedule.state.analysis.SessionDetailScreenEvent
import eu.pitlap.shared.ui.shared.PitlapCard

@Composable
fun SessionDetailScreen(
    modifier: Modifier = Modifier,
    sessionModel: SessionModel,
    onEvent: (SessionDetailScreenEvent) -> Unit
) {
    LazyVerticalGrid(
        modifier = modifier.padding(horizontal = 0.dp),
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        items(SessionDetailEnum.entries) {
            Box(
                modifier = Modifier.clickable {
                    when (sessionModel.sessionName) {
                        "Qualifying" -> {
                            when (it.title) {
                                "Results" -> {
                                    onEvent(SessionDetailScreenEvent.LoadQualifying(sessionModel))
                                }
                                "Top Speeds" -> {
                                    onEvent(SessionDetailScreenEvent.LoadTopSpeeds(sessionModel))
                                }
                            }
                        }
                        "Race" -> {
                            when (it.title) {
                                "Results" -> {
                                    onEvent(SessionDetailScreenEvent.LoadRaceResult(sessionModel))
                                }
                                "Top Speeds" -> {
                                    onEvent(SessionDetailScreenEvent.LoadTopSpeeds(sessionModel))
                                }
                            }
                            onEvent(SessionDetailScreenEvent.LoadRaceResult(sessionModel))
                        }
                        "Practice 1", "Practice 2", "Practice 3"  -> {
                            when (it.title) {
                                "Results" -> {
                                    onEvent(SessionDetailScreenEvent.LoadQualifying(sessionModel))
                                }
                                "Top Speeds" -> {
                                    onEvent(SessionDetailScreenEvent.LoadTopSpeeds(sessionModel))
                                }
                            }
                        }
                    }
                }
            ) {
                PitlapCard(
                    level = "Analysis",
                    icon = it.icon,
                    iconColor = Color.Red,
                    title = it.title,
                    subtitle = it.description,
                    progressColor = Color.Red
                )
            }
        }
    }
}
