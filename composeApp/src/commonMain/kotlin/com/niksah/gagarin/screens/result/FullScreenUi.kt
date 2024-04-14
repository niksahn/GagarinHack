package com.niksah.gagarin.screens.result

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.niksah.gagarin.screens.main.TopBar
import gagarinhak.composeapp.generated.resources.Res
import gagarinhak.composeapp.generated.resources.arrow_back
import gagarinhak.composeapp.generated.resources.doc_scanner
import gagarinhak.composeapp.generated.resources.go_back
import gagarinhak.composeapp.generated.resources.icon
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun FullScreenUi(
    data: ResultState.Content,
    onBack: () -> Unit,
    onSave: () -> Unit
) {
    Scaffold(topBar = {
        TopBarBack(onBack = onBack)
    }) {
        Row(
            modifier = Modifier.padding(it).padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            data.image?.let { it1 ->
                Image(
                    modifier = Modifier.weight(1F), bitmap = it1, contentDescription = null
                )
            } ?: Image(
                modifier = Modifier.weight(1F),
                painter = painterResource(Res.drawable.icon),
                contentDescription = null
            )
            Column {
                LazyColumn(
                    modifier = Modifier.weight(1F),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(
                        items = data.fields
                    ) { item ->
                        Column {
                            Row(modifier = Modifier.fillMaxWidth()) {
                                Text(
                                    modifier = Modifier.weight(1f),
                                    text = item.title,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                Text(
                                    modifier = Modifier,
                                    text = item.value,
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            }
                            HorizontalDivider()
                        }
                    }
                }
//                Button(
//                    onClick = onSave,
//                    content = {
//                        Text("Загрузить отчет")
//                    }
//                )
            }
        }
    }
}


@Composable
fun TopBarBack(
    onBack: () -> Unit
) {
    Column {
        Row(modifier = Modifier.fillMaxWidth().padding(32.dp)) {
            Text(
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.headlineLarge,
                text = stringResource(Res.string.doc_scanner)
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.clickable(onClick = onBack)
            ) {
                Icon(
                    modifier = Modifier.size(56.dp),
                    painter = painterResource(Res.drawable.arrow_back),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.secondary
                )
                Text(
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary,
                    text = stringResource(Res.string.go_back),
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
        }
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(), thickness = 1.dp, color = Color.Black
        )
    }
}