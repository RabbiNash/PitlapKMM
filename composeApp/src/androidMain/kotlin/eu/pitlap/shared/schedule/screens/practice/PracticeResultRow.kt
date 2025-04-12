package eu.pitlap.shared.schedule.screens.practice

import androidx.compose.foundation.layout.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import eu.pitlap.shared.R
import eu.pitlap.shared.race.domain.model.GroupedLapModel

@Composable
fun PracticeResultRow(rowModel: GroupedLapModel) {
    Column(
        modifier = Modifier
            .padding(vertical = 10.dp)
    ) {
        DriverInfoSection(modifier = Modifier, rowModel)
        HorizontalDivider(color = Color.Gray.copy(alpha = 0.5f), thickness = 0.5.dp)
        TimesSection(modifier = Modifier.align(Alignment.CenterHorizontally), rowModel)
        HorizontalDivider(color = Color.Gray.copy(alpha = 0.5f), thickness = 0.5.dp)
    }
}

@Composable
private fun DriverInfoSection(modifier: Modifier = Modifier, rowModel: GroupedLapModel) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
    ) {
        AsyncImage(
            model = rowModel.headshotUrl,
            contentDescription = null,
            modifier = Modifier
                .size(48.dp)
                .clip(RectangleShape),
            contentScale = ContentScale.Crop,
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = rowModel.fullName,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun TimesSection(
    modifier: Modifier = Modifier,
    rowModel: GroupedLapModel
) {
    Row(
        modifier = modifier.padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        PracticeTimeView(time = rowModel.bestLapTime)
    }
}

@Composable
private fun PracticeTimeView(time: String?) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = null,
            modifier = Modifier.size(12.dp)
        )

        Spacer(modifier = Modifier.width(4.dp))

        Text(
            text = time ?: "",
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

