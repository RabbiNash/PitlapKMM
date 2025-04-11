package eu.pitlap.shared

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import eu.pitlap.shared.standings.StandingsViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: StandingsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            val standings = viewModel.driverStandings.collectAsStateWithLifecycle()

            App(standings = standings.value, schedule = emptyList())
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App(standings = emptyList())
}
