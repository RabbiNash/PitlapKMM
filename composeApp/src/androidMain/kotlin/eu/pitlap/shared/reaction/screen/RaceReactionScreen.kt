package eu.pitlap.shared.reaction.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import eu.pitlap.shared.reaction.state.ReactionEvent
import eu.pitlap.shared.reaction.viewmodel.RaceReactionViewModel

@Composable
fun RaceReactionScreen(viewModel: RaceReactionViewModel = viewModel()) {
    val state by viewModel.state.collectAsState()

    DisposableEffect(Unit) {
        onDispose {
            viewModel.clearSequence()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        RaceLights(activeIndex = state.activeIndex)
        Spacer(modifier = Modifier.height(16.dp))
        JumpStartView(reactionTime = state.reactionTime, isJumpStart = state.isJumpStart)
        Spacer(modifier = Modifier.weight(1f))
        ControlButton(
            isSequenceRunning = state.isSequenceRunning,
            onClick = { viewModel.onEvent(ReactionEvent.ButtonPressed) }
        )
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
fun RaceLights(activeIndex: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        repeat(4) { index ->
            StartLight(isActive = activeIndex >= index)
        }
    }
}

@Composable
fun StartLight(isActive: Boolean) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .background(
                color = if (isActive) Color.Red else Color.Transparent,
                shape = CircleShape
            )
            .border(1.dp, Color.Red, CircleShape)
    )
}

@SuppressLint("DefaultLocale")
@Composable
fun JumpStartView(reactionTime: Double?, isJumpStart: Boolean) {
    if (reactionTime != null) {
        Text(
            text = String.format("%.3f s", reactionTime),
            style = MaterialTheme.typography.displayMedium,
        )
    } else if (isJumpStart) {
        Text(
            text = "Oops Jump Start",
            style = MaterialTheme.typography.displayMedium
        )
    }
}

@Composable
fun ControlButton(
    isSequenceRunning: Boolean,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .size(240.dp),
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
    ) {
        Text(
            text = if (isSequenceRunning) "Go Go" else "Start",
            style = MaterialTheme.typography.displayLarge,
            color = Color.White
        )
    }
}



