package com.niksah.gagarin.di

import com.niksah.gagarin.data.ApiRepository
import com.niksah.gagarin.domain.ApiRepositoryImpl
import org.koin.dsl.module

val domainModule = module {
    single<ApiRepository> { ApiRepositoryImpl(get()) }
}