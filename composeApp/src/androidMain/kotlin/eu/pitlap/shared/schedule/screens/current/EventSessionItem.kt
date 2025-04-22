package eu.pitlap.shared.schedule.screens.current

import androidx.compose.foundation.layout.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import eu.pitlap.shared.ui.pitlapTypography
import eu.pitlap.shared.utils.DateUtils
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date

@Composable
fun EventSessionItem(
    modifier: Modifier = Modifier,
    sessionName: String, sessionTime: String
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = sessionName,
                modifier = Modifier.widthIn(max = 120.dp),
                style = pitlapTypography.bodyMedium,
                maxLines = 1
            )

            Spacer(modifier = Modifier.weight(1f))

            if (DateUtils.isPastDate(sessionTime)) {
                Text(text = "View Results", style = pitlapTypography.bodyMedium)
            } else {
                Text(
                    text = DateUtils.getHumanisedDate(sessionTime) ?: "",
                    maxLines = 2,
                    style = pitlapTypography.bodyMedium
                )
            }
        }

        HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
    }
}
