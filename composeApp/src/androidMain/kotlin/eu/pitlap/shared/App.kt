package eu.pitlap.shared

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import eu.pitlap.shared.schedule.domain.model.EventScheduleModel
import eu.pitlap.shared.standings.domain.model.DriverStandingModel
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import pitlap.composeapp.generated.resources.Res
import pitlap.composeapp.generated.resources.compose_multiplatform

@Composable
@Preview
fun App(standings: List<DriverStandingModel>, schedule: List<EventScheduleModel> = emptyList()) {
    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = { showContent = !showContent }) {
                Text("Click me!")
            }
            AnimatedVisibility(showContent) {
                val greeting = remember { Greeting().greet() }
                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(painterResource(Res.drawable.compose_multiplatform), null)
                    Text("Compose: $greeting")
                }

                LazyColumn {
                    items(schedule.size) {
                        Text(schedule[it].country)
                    }
                }
            }
        }
    }
}
