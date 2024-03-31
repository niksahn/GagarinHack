package com.niksah.gagarin.di

import com.niksah.gagarin.ui.main.MainViewModel
import org.koin.dsl.module

val uiModule = module {
    factory {
        MainViewModel(get())
    }
}