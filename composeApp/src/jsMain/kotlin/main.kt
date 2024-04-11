import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import com.niksah.gagarin.App
import com.niksah.gagarin.di.dataModule
import com.niksah.gagarin.di.domainModule
import com.niksah.gagarin.di.uiModule
import com.niksah.seconHack.ui.theme.GagarinTheme
import org.jetbrains.skiko.wasm.onWasmReady
import org.koin.core.context.startKoin

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    startKoin {
        modules(listOf(dataModule, domainModule, uiModule))
    }
    onWasmReady {
        CanvasBasedWindow("GagarinHak") {
            App()
        }
    }
}
