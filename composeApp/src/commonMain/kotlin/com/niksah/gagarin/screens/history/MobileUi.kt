package com.niksah.gagarin.screens.history

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import com.niksah.gagarin.utils.views.Loader
import com.niksah.gagarin.utils.views.PullToRefreshState
import com.niksah.gagarin.utils.views.PullToRefreshes
import org.jetbrains.compose.resources.painterResource

@Composable
fun MobileUi(
    state: HistoryState,
    onItem: (id: String) -> Unit,
    onRefresh: () -> Unit,
    isLoading: Boolean
) {
    Scaffold {
        PullToRefreshes.Primary(
            onRefresh = onRefresh,
            state = PullToRefreshState(isRefreshing = isLoading)
        ) {
            LazyColumn(modifier = Modifier.padding(it).padding(16.dp)) {
                items(items = state.history) {
                    HistoryItem(modifier = Modifier.clickable { onItem(it.id) }, item = it)
                }
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
            item.image?.let {
                Image(
                    bitmap = it,
                    contentDescription = null,
                    modifier = Modifier
                        .size(70.dp, 70.dp)
                        .clip(MaterialTheme.shapes.extraLarge),
                )
            } ?: Box(modifier = Modifier.size(120.dp)) {
                Loader()
            }

            Column(
                modifier = Modifier.padding(4.dp).weight(1f),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(text = item.name)
                item.data?.let { Text(text = it) }
                //  Text(text = item.)
            }
            Image(
                modifier = Modifier,
                painter = painterResource(item.status.toDrawable()),
                contentDescription = null
            )
        }
        HorizontalDivider()
    }
}