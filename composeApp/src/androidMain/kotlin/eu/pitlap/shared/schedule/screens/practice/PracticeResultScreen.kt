package eu.pitlap.shared.schedule.screens.practice

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
import eu.pitlap.shared.schedule.state.practice.PracticeScreenEvent
import eu.pitlap.shared.schedule.viewmodel.practice.PracticeResultViewModel

@Composable
fun PracticeResultScreen(
    modifier: Modifier = Modifier,
    viewModel: PracticeResultViewModel = viewModel(),
    year: Int,
    round: Int,
    sessionName: String
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.onEvent(PracticeScreenEvent.LoadResults(year, round, sessionName))
    }

    LazyColumn(
        modifier = modifier.padding(24.dp)
    ) {
        items(state.results) {
            PracticeResultRow(rowModel = it)
        }
    }
}
