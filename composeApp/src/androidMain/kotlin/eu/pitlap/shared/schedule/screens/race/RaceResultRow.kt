package eu.pitlap.shared.schedule.screens.race

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import eu.pitlap.shared.race.domain.model.RaceResultModel

@Composable
fun RaceResultRow(rowModel: RaceResultModel) {
    Column(
        modifier = Modifier
            .padding(vertical = 10.dp)
    ) {
        ResultSection(modifier = Modifier, rowModel)
    }
}

@Composable
private fun ResultSection(modifier: Modifier = Modifier, rowModel: RaceResultModel) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
    ) {
        Text(
            modifier = Modifier.width(30.dp),
            text = rowModel.position.toString(),
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold
        )

        AsyncImage(
            model = rowModel.headshotURL,
            contentDescription = null,
            modifier = Modifier
                .size(48.dp)
                .clip(RectangleShape),
            contentScale = ContentScale.Crop,
        )

        Spacer(modifier = Modifier.width(8.dp))

        // Wrap the name + team in a Row with weight(1f) to push the last Text to the end
        Row(
            modifier = Modifier
                .weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f) // Pushes the points to the end within this row
            ) {
                Text(
                    text = rowModel.fullName,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = rowModel.teamName,
                    style = MaterialTheme.typography.bodyLarge,
                )
            }

            Text(
                text = rowModel.points.toString(),
                style = MaterialTheme.typography.bodyLarge,
            )
        }
    }
}
