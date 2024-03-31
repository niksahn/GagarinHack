package com.niksah.gagarin.utils.views
//
//import android.Manifest
//import android.widget.Toast
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.stringResource
//import com.google.accompanist.permissions.ExperimentalPermissionsApi
//import com.google.accompanist.permissions.rememberMultiplePermissionsState
//import com.niksahn.seconHack.R
//import com.ramcosta.composedestinations.navigation.DestinationsNavigator
//
//@OptIn(ExperimentalPermissionsApi::class)
//@Composable
//fun MissingPermissionsComponent(
//	navigator: DestinationsNavigator,
//	content: @Composable () -> Unit,
//) {
//	val context = LocalContext.current
//	val permissions = listOf(
//		Manifest.permission.CAMERA,
//		Manifest.permission.RECORD_AUDIO,
//		Manifest.permission.ACCESS_COARSE_LOCATION
//	)
//	val permissionsState = rememberMultiplePermissionsState(permissions = permissions)
//	if (permissionsState.revokedPermissions == permissions) {
//		Toast.makeText(
//			context,
//			stringResource(R.string.set_permissions),
//			Toast.LENGTH_SHORT
//		).show()
//		navigator.navigateUp()
//	}
//	if (permissionsState.allPermissionsGranted) {
//		content()
//	} else {
//		LaunchedEffect(permissionsState) {
//			permissionsState.launchMultiplePermissionRequest()
//		}
//	}
//}