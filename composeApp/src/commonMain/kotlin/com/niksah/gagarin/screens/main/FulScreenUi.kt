package com.niksah.gagarin.screens.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.unit.sp
import gagarinhak.composeapp.generated.resources.Res
import gagarinhak.composeapp.generated.resources.doc_scanner
import gagarinhak.composeapp.generated.resources.file_earmark_arrow_up
import gagarinhak.composeapp.generated.resources.load_file
import gagarinhak.composeapp.generated.resources.load_file_
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun FullScreenUi(
    onShowFilePicker: ()-> Unit
) {
    Scaffold(
        topBar = {
            TopBar(onShowFilePicker)
        }
    ) {
        Box(
            modifier = Modifier.padding(it).fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                style = MaterialTheme.typography.headlineLarge.copy(fontSize = 100.sp),
                text = stringResource(Res.string.load_file_)
            )
        }
    }
}


@Composable
fun TopBar(onShowFilePicker: () -> Unit){
    Column {
        Row(modifier = Modifier.fillMaxWidth().padding(32.dp)) {
            Text(
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.headlineLarge,
                text = stringResource(Res.string.doc_scanner)
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.clickable(onClick = onShowFilePicker)
            ) {
                Icon(
                    modifier = Modifier.size(56.dp),
                    painter = painterResource(Res.drawable.file_earmark_arrow_up),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.secondary
                )
                Text(
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary,
                    text = stringResource(Res.string.load_file),
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
        }
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = Color.Black
        )
    }
}