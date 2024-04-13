package com.niksah.gagarin.screens.result

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.niksah.gagarin.utils.views.Loader
import gagarinhak.composeapp.generated.resources.Res
import gagarinhak.composeapp.generated.resources.arrow_back
import gagarinhak.composeapp.generated.resources.icon
import org.jetbrains.compose.resources.painterResource


@Composable
fun MobileUI(
    data: ResultState.Content,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 32.dp)
            ) {
                Icon(
                    painter = painterResource(Res.drawable.arrow_back),
                    contentDescription = null,
                    modifier = Modifier.clickable(onClick = onBack)
                )
            }
        }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            data.image?.let { it1 ->
                Image(
                    modifier = Modifier
                        .clip(MaterialTheme.shapes.extraLarge)
                        .height(368.dp)
                        .width(300.dp)
                        .align(Alignment.CenterHorizontally),
                    bitmap = it1,
                    contentDescription = null
                )
            } ?:
//            Box(modifier = Modifier.height(368.dp).width(300.dp)) {
//               Loader()
//            }
            Image(
                modifier = Modifier
                    .clip(MaterialTheme.shapes.extraLarge)
                    .height(368.dp)
                    .width(300.dp)
                    .align(Alignment.CenterHorizontally),
                painter = painterResource(Res.drawable.icon),
                contentDescription = null
            )
            LazyColumn(
                // modifier = Modifier.padding(16.dp),
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
        }
    }
}
