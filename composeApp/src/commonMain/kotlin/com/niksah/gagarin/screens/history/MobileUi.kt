package com.niksah.gagarin.screens.history

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource

@Composable
fun MobileUi(state: HistoryState.Content) {
    Scaffold {
        LazyColumn(modifier = Modifier.padding(16.dp)) {
            items(items = state.history) {
                HistoryItem(modifier = Modifier, item = it)
            }
        }
    }
}

@Composable
fun HistoryItem(
    modifier: Modifier,
    item: History
) {
    Column(modifier) {
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
            Image(
                modifier = Modifier.weight(1f),
                painter = painterResource(item.status.toDrawable()),
                contentDescription = null
            )
        }
        HorizontalDivider()
    }
}