package eu.pitlap.shared.schedule.screens.qualifying

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import eu.pitlap.shared.race.domain.model.QualifyingResultModel

@Composable
fun QualifyingResultRow(rowModel: QualifyingResultModel) {
    Column(
        modifier = Modifier
            .padding(vertical = 10.dp)
    ) {
        DriverInfoSection(modifier = Modifier, rowModel)
        HorizontalDivider(color = Color.Gray.copy(alpha = 0.5f), thickness = 0.5.dp)
        TimesSection(rowModel = rowModel)
        HorizontalDivider(color = Color.Gray.copy(alpha = 0.5f), thickness = 0.5.dp)
    }
}

@Composable
private fun DriverInfoSection(modifier: Modifier = Modifier, rowModel: QualifyingResultModel) {
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
            model = rowModel.headshotUrl,
            contentDescription = null,
            modifier = Modifier
                .size(48.dp)
                .clip(RectangleShape),
            contentScale = ContentScale.Crop,
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column {
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
    }
}

@Composable
private fun TimesSection(
    modifier: Modifier = Modifier,
    rowModel: QualifyingResultModel
) {
    Row(
        modifier = modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        TimeView(time = rowModel.q1, session = "Q1: " )
        TimeView(time = rowModel.q2, session = "Q2: ")
        TimeView(time = rowModel.q3, session = "Q3: ")
    }
}

@Composable
private fun TimeView(time: String?, session: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        Text(
            text = session,
            style = MaterialTheme.typography.bodyMedium,
        )

        Spacer(modifier = Modifier.width(4.dp))

        Text(
            text = time ?: "",
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}
