package com.niksah.gagarin.di

import com.niksah.gagarin.data.network.NetworkClient
import com.niksah.gagarin.data.network.KtorClient
import com.russhwolf.settings.Settings
import org.koin.dsl.module

val dataModule = module {
    single<NetworkClient> {
        KtorClient()
    }
    single<Settings>{
        Settings()
    }
}