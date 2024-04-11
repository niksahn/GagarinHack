package com.niksah.gagarin.screens.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.niksah.gagarin.data.CurrentPlatform
import com.niksah.gagarin.data.Platform
import gagarinhak.composeapp.generated.resources.Res
import gagarinhak.composeapp.generated.resources.file_earmark_arrow_up
import gagarinhak.composeapp.generated.resources.file_scan
import gagarinhak.composeapp.generated.resources.load_file
import gagarinhak.composeapp.generated.resources.scan
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun MobileUi(
    onShowFilePicker: () -> Unit, onCamera: () -> Unit
) {
    Scaffold {
        Box(modifier = Modifier.padding(it)) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .padding(top = 64.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Box(
                    modifier = Modifier
                        .clip(MaterialTheme.shapes.extraLarge)
                        .clickable(onClick = {
                            if (CurrentPlatform.current == Platform.Android) {
                                onCamera()
                            } else {
                                onShowFilePicker()
                            }
                        })
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.onPrimary),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Image(
                            painter = painterResource(Res.drawable.file_scan),
                            contentDescription = null,
                            // modifier = Modifier.size(loaderIconSize.first, loaderIconSize.second)
                        )
                        Text(
                            text = stringResource(Res.string.scan),
                            color = MaterialTheme.colorScheme.secondary,
                            softWrap = false,
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.titleLarge,
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .clip(MaterialTheme.shapes.extraLarge)
                        .border(1.dp, color = Color.Black, shape = MaterialTheme.shapes.extraLarge)
                        .clickable(onClick = onShowFilePicker)
                        .fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Image(
                            painter = painterResource(Res.drawable.file_earmark_arrow_up),
                            contentDescription = null,
                        )
                        Text(
                            text = stringResource(Res.string.load_file),
                            color = Color.Black,
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.titleMedium,
                        )
                    }
                }
            }
        }
    }
}