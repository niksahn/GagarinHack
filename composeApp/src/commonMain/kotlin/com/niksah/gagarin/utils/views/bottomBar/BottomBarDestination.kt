package com.niksah.gagarin.utils.views.bottomBar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.niksah.seconHack.ui.theme.SelectedIcon
import com.niksah.seconHack.ui.theme.SelectedPurple
import com.niksah.seconHack.ui.theme.UnSelected
import com.niksah.seconHack.ui.theme.UnSelectedIcon
import gagarinhak.composeapp.generated.resources.Res
import gagarinhak.composeapp.generated.resources.icon_container
import kotlinx.coroutines.flow.last
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import moe.tlaster.precompose.navigation.Navigator
import org.jetbrains.compose.resources.painterResource


@Composable
fun BottomBar(
    navController: Navigator,
) {
    val currentDestination = navController.currentEntry.collectAsStateWithLifecycle(null)

    Column {
        NavigationBar(
            containerColor = Color.White
        ) {
            BottomBarDestination.entries.forEach { destination ->
                NavigationBarItem(
                    selected = currentDestination.value?.route?.route == destination.direction,
                    onClick = {
                        navController.navigate(destination.direction)
                    },
                    icon = {
                        val isSelected =
                            currentDestination.value?.route?.route == destination.direction
                        val selectedColorIcon = SelectedIcon
                        val unSelectedColorIcon = UnSelectedIcon
                        val selectedColor = SelectedPurple
                        val unselectedColor = UnSelected
                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                               .size(32.dp)
                        ) {
                            Icon(
                                painter = painterResource(Res.drawable.icon_container),
                                contentDescription = null,
                                tint = if (isSelected) selectedColorIcon else unSelectedColorIcon
                            )
                        }
                    },
                    label = {
                        Text(
                            text = destination.title,
                            color = Color.Black,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = SelectedPurple,
                        unselectedIconColor = UnSelected,
                    ),
                )
            }
        }
    }
}
