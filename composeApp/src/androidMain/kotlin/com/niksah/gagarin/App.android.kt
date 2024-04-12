package com.niksah.gagarin

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.niksah.gagarin.data.domainAndroidModule
import com.niksah.gagarin.data.uiAndroidModule
import com.niksah.gagarin.di.dataModule
import com.niksah.gagarin.di.domainModule
import com.niksah.gagarin.di.uiModule
import moe.tlaster.precompose.PreComposeApp
import org.koin.core.context.GlobalContext.startKoin

class AndroidApp : Application() {
    companion object {
        lateinit var INSTANCE: AndroidApp
    }

    override fun onCreate() {
        startKoin {
            modules(
                listOf(
                    dataModule,
                    domainModule,
                    uiModule,
                    domainAndroidModule,
                    uiAndroidModule
                )
            )
        }
        super.onCreate()
        INSTANCE = this
    }
}

class AppActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PreComposeApp {
                App()
            }
        }
    }


}
