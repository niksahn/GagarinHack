import androidx.compose.ui.window.ComposeUIViewController
import com.niksah.gagarin.App
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController { App() }
