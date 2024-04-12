import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import java.awt.Dimension
import com.niksah.gagarin.App
import com.niksah.gagarin.di.dataModule
import com.niksah.gagarin.di.domainModule
import com.niksah.gagarin.di.uiModule
import org.koin.core.context.startKoin

fun main() = application {
    startKoin {
        modules(listOf(dataModule, domainModule, uiModule))
    }
    Window(
        title = "GagarinHak",
        state = rememberWindowState(width = 1200.dp, height = 1000.dp),
        onCloseRequest = ::exitApplication,
    ) {
        window.minimumSize = Dimension(350, 600)
        App()
    }
}