package com.niksah.gagarin.data

import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions
import com.niksah.gagarin.screens.camera.CameraViewModel
import org.koin.dsl.module


val uiAndroidModule = module {
    factory {
        CameraViewModel(get(), get(), get(), get())
    }
    single {
        GmsDocumentScannerOptions.Builder()
            .setScannerMode(GmsDocumentScannerOptions.SCANNER_MODE_BASE)
            .setResultFormats(GmsDocumentScannerOptions.RESULT_FORMAT_JPEG)
            .build()
    }

    single {

    }
}

val domainAndroidModule = module {
    single<FileStorageRepository> { FileStorageRepository() }

}