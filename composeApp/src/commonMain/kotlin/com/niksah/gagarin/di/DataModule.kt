package com.niksah.gagarin.di

import com.niksah.gagarin.data.NetworkClient
import com.niksah.gagarin.data.network.KtorClient
import org.koin.dsl.module

val dataModule = module {
    single<NetworkClient> {
        KtorClient()
    }
}