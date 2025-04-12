package eu.pitlap.shared.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.pitlap.shared.navigation.destination.BottomNavItem

@Composable
fun CustomBottomBar(
    items: List<BottomNavItem>,
    selectedItem: String,
    onItemSelected: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(Color(0xFFFAFAFA), shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        items.forEach { item ->
            Column(
                modifier = Modifier.clickable { onItemSelected(item.route) },
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = item.icon,
                    contentDescription = item.label,
                    tint = if (item.route == selectedItem) Color(0xFFFF9800) else Color.Gray,
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = item.label,
                    fontSize = 12.sp,
                    color = if (item.route == selectedItem) Color(0xFFFF9800) else Color.Gray
                )
            }
        }
    }
}
