package eu.pitlap.shared.schedule.screens.analysis

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import eu.pitlap.shared.race.domain.model.TopSpeedModel
import eu.pitlap.shared.schedule.models.SessionModel
import eu.pitlap.shared.schedule.state.analysis.TopSpeedScreenEvent
import eu.pitlap.shared.schedule.viewmodel.analysis.TopSpeedViewModel
import eu.pitlap.shared.ui.pitlapTypography

@Composable
fun TopSpeedScreen(
    modifier: Modifier = Modifier,
    viewModel: TopSpeedViewModel = viewModel(),
    sessionModel: SessionModel
) {
    LaunchedEffect(Unit) {
        viewModel.onEvent(TopSpeedScreenEvent.LoadTopSpeeds(sessionModel.year, sessionModel.round, sessionModel.sessionName))
    }

    val state = viewModel.state.collectAsStateWithLifecycle().value
    if (state.isLoading) {
        LinearProgressIndicator()
    } else {
        TopSpeedList(speeds = state.results, modifier = modifier)
    }
}

@Composable
fun TopSpeedList(
    speeds: List<TopSpeedModel>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(speeds) { speed ->
            SpeedDataCard(speed)
        }
    }
}

@Composable
fun SpeedDataCard(speed: TopSpeedModel) {
    Card (
        modifier = Modifier
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = speed.driver,
                style = pitlapTypography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "${speed.speed.toInt()} km/h",
                style = pitlapTypography.bodyLarge,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

