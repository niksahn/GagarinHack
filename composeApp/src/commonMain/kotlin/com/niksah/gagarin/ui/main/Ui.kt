package com.niksah.gagarin.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.niksah.gagarin.utils.views.Spacer
import gagarinhak.composeapp.generated.resources.Res
import gagarinhak.composeapp.generated.resources.camera
import gagarinhak.composeapp.generated.resources.file_upload
import gagarinhak.composeapp.generated.resources.fix_violation
import gagarinhak.composeapp.generated.resources.history
import moe.tlaster.precompose.koin.koinViewModel
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun Main() {
    val viewModel = koinViewModel(MainViewModel::class)

    Scaffold {
        Column(
            modifier = Modifier.padding(24.dp).fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(size = 24.dp)
            Box(
                modifier = Modifier
                    .size(368.dp, 304.dp)
                    .clip(MaterialTheme.shapes.extraLarge)
                    .background(MaterialTheme.colorScheme.onPrimary)
                  //  .clickable(onClick = viewModel::onCamera)
                ,
                contentAlignment = Alignment.Center
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(Res.drawable.camera),
                        contentDescription = null,
                        modifier = Modifier.size(144.dp, 120.dp)
                    )
                    Text(
                        text = stringResource(Res.string.fix_violation),
                        color = MaterialTheme.colorScheme.secondary,
                        softWrap = false,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleLarge,
                    )
                }
            }
            Spacer(size = 24.dp)
            Box(
                modifier = Modifier
                    .size(368.dp, 152.dp)
                    .clip(MaterialTheme.shapes.extraLarge)
                    .background(MaterialTheme.colorScheme.onPrimary)
                  //  .clickable(onClick = viewModel::onLoader)
                ,
                contentAlignment = Alignment.Center
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(Res.drawable.file_upload),
                        contentDescription = null,
                        modifier = Modifier.size(77.dp, 60.dp)
                    )
                    Text(
                        text = "Загрузить файл",
                        color = MaterialTheme.colorScheme.secondary,
                        softWrap = false,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleLarge,
                    )
                }
            }
            Spacer(size = 32.dp)
            Row {
                Text(
                    text = stringResource(Res.string.history),
                    color = MaterialTheme.colorScheme.surface,
                    textAlign = TextAlign.Left,
                    style = MaterialTheme.typography.titleMedium,
                )
            }
            Spacer(size = 16.dp)
//            FilterRow(
//                currentFilter = state.currentFilter,
//                filters = state.filters,
//                onSelected = onFilter
//            )
//            when (state.operation) {
//                is Operation.Failure -> TODO()
//                Operation.Preparing -> Loading(it)
//                is Operation.Success -> LazyColumn {
//                    items(items = state.filteredList) {
//                        HistoryItem(it)
//                    }
//                }
//            }
        }
    }
}
