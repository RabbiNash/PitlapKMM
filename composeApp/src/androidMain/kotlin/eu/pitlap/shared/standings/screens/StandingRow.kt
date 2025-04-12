package eu.pitlap.shared.standings.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.pitlap.shared.standings.models.StandingRowUiModel

@Composable
fun StandingRow(
    modifier: Modifier = Modifier,
    rowModel: StandingRowUiModel
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
//            .background(
//                brush = Brush.horizontalGradient(
//                    colors = listOf(Color(0xFFFFA500), Color(0xFFFF4500))
//                ),
//                shape = RoundedCornerShape(16.dp)
//            )
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = rowModel.position,
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(end = 16.dp)
            )

            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start,
            ) {
                if (rowModel.fullName != null) {
                    Text(
                        text = rowModel.fullName,
                        style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
                    )
                    Text(
                        text = rowModel.constructorName,
                        style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Light)
                    )
                } else {
                    Text(
                        text = rowModel.constructorName,
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.End,
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "${rowModel.points} pts",
                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                    text = "${rowModel.wins} wins",
                    style = MaterialTheme.typography.headlineSmall
                )
            }
        }
    }
}
