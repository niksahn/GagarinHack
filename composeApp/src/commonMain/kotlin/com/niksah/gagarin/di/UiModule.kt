package com.niksah.gagarin.di

import com.niksah.gagarin.screens.history.HistoryViewModel
import com.niksah.gagarin.screens.main.MainViewModel
import com.niksah.gagarin.screens.result.ResultViewModel
import org.koin.dsl.module

val uiModule = module {
    factory {
        MainViewModel(get(), get(), get())
    }
    factory {
        ResultViewModel(get())
    }
    factory {
        HistoryViewModel(get(), get())
    }
}