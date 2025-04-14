package eu.pitlap.shared.schedule.screens.race

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import eu.pitlap.shared.schedule.state.race.RaceScreenEvent
import eu.pitlap.shared.schedule.viewmodel.race.RaceResultViewModel


@Composable
fun RaceResultScreen(
    modifier: Modifier = Modifier,
    viewModel: RaceResultViewModel = viewModel(),
    year: Int,
    round: Int,
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.onEvent(RaceScreenEvent.LoadResults(year, round))
    }

    LazyColumn(
        modifier = modifier.padding(24.dp)
    ) {
        items(state.results) {
            RaceResultRow(rowModel = it)
        }
    }
}
