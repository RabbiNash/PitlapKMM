package eu.pitlap.shared.schedule.screens.current

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import eu.pitlap.shared.schedule.domain.model.EventScheduleModel
import eu.pitlap.shared.utils.DateUtils

@Composable
fun EventHeader(
    event: EventScheduleModel,
    selectedTeamColor: Color,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(
                Brush.sweepGradient(
                    colors = listOf(
                        selectedTeamColor,
                        selectedTeamColor.copy(alpha = 0.5f),
                        selectedTeamColor,
                        selectedTeamColor.copy(alpha = 0.5f)
                    )
                )
            )
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Round ${event.round}",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White
            )

            Text(
                text = event.officialEventName,
                style = MaterialTheme.typography.displayMedium,
                color = Color.White
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = event.country,
                        style = MaterialTheme.typography.headlineMedium,
                        color = Color.White
                    )

                    Text(
                        text = humanisedDate(event.session1DateUTC),
                        style = MaterialTheme.typography.headlineMedium,
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
private fun RoundedCornerBoxBackground(color: Color) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        color.copy(alpha = 0.9f),
                        color.copy(alpha = 0.6f)
                    )
                )
            )
            .shadow(8.dp, RoundedCornerShape(16.dp))
    )
}

fun humanisedDate(dateString: String?): String {
    return dateString?.let {
        DateUtils.getHumanisedDate(it)
    } ?: ""
}
