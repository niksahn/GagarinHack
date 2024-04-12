package com.niksah.gagarin.screens.history

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

class MobileUi {
}

@Composable
fun HistoryItem(
    item: History
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.height(72.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Image(
            bitmap = item.image,
            contentDescription = null,
            modifier = Modifier
                .size(32.dp, 72.dp)
                .clip(MaterialTheme.shapes.extraLarge),
        )
        Column(
            modifier = Modifier.padding(4.dp),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(text = item.name)
            Text(text = item.data)
            Text(text = item.date)
        }
    }
}