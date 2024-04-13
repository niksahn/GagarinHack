package com.niksah.gagarin.screens.history

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.niksah.gagarin.screens.result.TopBarBack

@Composable
fun FullScreenUi(
    state: HistoryState,
    onBack: () -> Unit,
    onItem: (id: String) -> Unit
) {
    Scaffold(
        topBar = {
            TopBarBack(onBack = onBack)
        }
    ) {
        LazyColumn(modifier = Modifier.padding(it).padding(16.dp)) {
            items(items = state.history) {
                HistoryItem(modifier = Modifier.clickable { onItem(it.id) }, item = it)
            }
        }
    }
}