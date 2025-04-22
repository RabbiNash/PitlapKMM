package eu.pitlap.shared.standings.screens

import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import eu.pitlap.shared.standings.StandingsViewModel
import eu.pitlap.shared.standings.state.StandingListEvent

@Composable
fun StandingsListScreen(viewModel: StandingsViewModel = viewModel()) {
    val state by viewModel.state.collectAsState()

    var selectedTabIndex by remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        viewModel.onEvent(StandingListEvent.LoadDriverStanding)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        TabRow(
            selectedTabIndex = selectedTabIndex,
            modifier = Modifier.fillMaxWidth(),
            indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                    color = Color.Red
                )
            }
        ) {
            Tab(
                selected = selectedTabIndex == 0,
                onClick = {
                    selectedTabIndex = 0
                    viewModel.onEvent(StandingListEvent.LoadDriverStanding)
                },
                text = { Text("Drivers") },
                selectedContentColor = Color.Red,
                unselectedContentColor = Color.Gray
            )
            Tab(
                selected = selectedTabIndex == 1,
                onClick = {
                    selectedTabIndex = 1
                    viewModel.onEvent(StandingListEvent.LoadConstructorStanding)
                },
                text = { Text("Constructors") },
                selectedContentColor = Color.Red,
                unselectedContentColor = Color.Gray
            )
        }

        if (state.isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
                LinearProgressIndicator(color = Color.Red)
            }
        } else if (state.error != null) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Error: ${state.error}", color = MaterialTheme.colorScheme.error)
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(state.standings) { standing ->
                    StandingRow(
                        modifier = Modifier
                            .clickable {  },
                        rowModel = standing
                    )
                }
            }
        }
    }
}

