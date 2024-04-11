package com.niksah.gagarin.data

import com.niksah.gagarin.screens.camera.CameraViewModel
import org.koin.dsl.module

val uiAndroidModule = module {
    factory {
        CameraViewModel(get(), get(), get())
    }
}

val domainAndroidModule = module { single<FileStorageRepository> { FileStorageRepository() } }