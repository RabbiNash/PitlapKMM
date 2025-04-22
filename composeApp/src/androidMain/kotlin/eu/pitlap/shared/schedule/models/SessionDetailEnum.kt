package eu.pitlap.shared.schedule.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.Speed
import androidx.compose.ui.graphics.vector.ImageVector

enum class SessionDetailEnum(
    val title: String,
    val description: String,
    val icon: ImageVector,
) {
    RESULT(
        "Results",
        "View session results, as always the times are ranked from fastest to slowest",
        Icons.Default.Flag,
    ),
    TOP_SPEEDS(
        "Top Speeds",
        "View and analyse session top speeds, this more or less tells who is running less downforce than the other",
        Icons.Default.Speed
    )
}
