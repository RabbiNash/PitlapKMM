package eu.pitlap.shared.schedule.screens.current

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import eu.pitlap.shared.schedule.domain.model.EventScheduleModel
import eu.pitlap.shared.schedule.state.ScheduleScreenEvent
import eu.pitlap.shared.schedule.viewmodel.ScheduleViewModel
import eu.pitlap.shared.ui.pitlapTypography

@Composable
fun EventListScreen(
    viewModel: ScheduleViewModel = viewModel(),
    onEventClick: (EventScheduleModel) -> Unit
) {

    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.onEvent(ScheduleScreenEvent.LoadSchedule(year = 2025))
    }

    Column(modifier = Modifier.padding(16.dp)) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            state.nextSession?.let { nextEvent ->
                item {
                    EventHeader(
                        event = nextEvent,
                        modifier = Modifier.fillMaxWidth(),
                        selectedTeamColor = Color.Red
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Calendar",
                        style = MaterialTheme.typography.displaySmall,
                        modifier = Modifier.weight(1f)
                    )

                    TextButton(onClick = {
                        viewModel.onEvent(ScheduleScreenEvent.TogglePastEvents(showPastEvents = !state.showPastEvents))
                    }) {
                        Text(
                            text = if (state.showPastEvents) "Hide past" else "Show all",
                            style = pitlapTypography.titleMedium,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }
            }


            items(state.seasonCalendar) {
                EventListItem(
                    event = it,
                    selectedTeamColor = Color.Red,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onEventClick(it)
                        }
                )
            }
        }
    }
}

@Composable
fun SettingsScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Settings Screen", fontSize = 24.sp, textAlign = TextAlign.Center)
    }
}
