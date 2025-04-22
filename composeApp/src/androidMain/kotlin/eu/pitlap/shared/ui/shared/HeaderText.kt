package eu.pitlap.shared.ui.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import eu.pitlap.shared.ui.pitlapTypography

@Composable
fun HeaderText(text: String) {
    var textWidth by remember { mutableIntStateOf(0) }
    Column(
        modifier = Modifier
            .padding(bottom = 12.dp)
    ) {
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onBackground,
            style = pitlapTypography.displayMedium,
            modifier = Modifier
                .onGloballyPositioned { coordinates ->
                    textWidth = coordinates.size.width
                }
        )

        Box(
            modifier = Modifier
                .width(with(LocalDensity.current) { textWidth.toDp() })
                .height(4.dp)
                .background(Color.Red)
        )
    }
}


