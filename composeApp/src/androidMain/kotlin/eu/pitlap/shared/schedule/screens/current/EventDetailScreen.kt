package eu.pitlap.shared.schedule.screens.current

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import eu.pitlap.shared.schedule.domain.model.EventScheduleModel
import eu.pitlap.shared.schedule.models.SessionType
import eu.pitlap.shared.schedule.models.sessionName
import eu.pitlap.shared.schedule.models.sessionTime
import eu.pitlap.shared.schedule.state.EventDetailScreenEvent
import eu.pitlap.shared.schedule.viewmodel.EventDetailViewModel

@Composable
fun EventDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: EventDetailViewModel = viewModel(),
    year: Int,
    round: Int,
    onEventClick: (EventScheduleModel, String) -> Unit
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.onEvent(EventDetailScreenEvent.LoadEvent(year, round))
    }

    LazyColumn(
        modifier = modifier
            .padding(24.dp)
            .fillMaxSize()
    ) {
        state.event?.let {
            item {
                EventHeader(
                    event = it,
                    selectedTeamColor = Color.Red,
                )
            }

            item {
                SessionTimesView(event = it, onEventClick = onEventClick)
            }

            item {
                RaceSummaryView("Let me know if you're using a custom navigation setup (e.g., Hilt, multiple NavHosts, or rememberSaveable for nav state), and I’ll tweak the code for you.")
            }

            item {
                TrackFactsView("Let me know if you're using a custom navigation setup (e.g., Hilt, multiple NavHosts, or rememberSaveable for nav state), and I’ll tweak the code for you.")
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun SessionTimesView(event: EventScheduleModel, onEventClick: (EventScheduleModel, String) -> Unit) {
    Column(modifier = Modifier
        .padding(vertical = 16.dp)
        .fillMaxWidth()) {
        Text(text = event.eventName, style = MaterialTheme.typography.displaySmall)
        SessionType.entries.forEach { sessionType ->
            val sessionTime = event.sessionTime(sessionType)
            val sessionName = event.sessionName(sessionType)

            if (sessionName != "None" && sessionTime != null) {
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .clickable {  }
//                        .padding(vertical = 16.dp),
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Text(text = sessionName, modifier = Modifier.weight(1f))
//                    Text(text = sessionTime, style = MaterialTheme.typography.bodySmall)
//                }
//                HorizontalDivider()
                EventSessionItem(
                    modifier = Modifier.clickable {
                        onEventClick(event, sessionName)
                    },
                    sessionName = sessionName,
                    sessionTime = sessionTime
                )
            }
        }
    }
}

@Composable
fun RaceSummaryView(raceSummary: String?) {
    raceSummary?.let {
        SummaryView(title = "Race Summary", content = raceSummary)
    }
}

@Composable
fun TrackFactsView(trackSummary: String?) {
    trackSummary?.let {
        SummaryView(title = "Track Facts", content = trackSummary)
    }
}

@Composable
fun SummaryView(title: String, content: String) {
    Column(modifier = Modifier
        .padding(vertical = 8.dp)
    ) {
        Text(text = title, style = MaterialTheme.typography.displaySmall)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = content, style = MaterialTheme.typography.bodyMedium)
    }
}

