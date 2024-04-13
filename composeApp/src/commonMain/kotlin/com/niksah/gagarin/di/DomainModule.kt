package com.niksah.gagarin.di

import com.niksah.gagarin.data.repositories.ApiRepository
import com.niksah.gagarin.data.repositories.ResponseRepository
import com.niksah.gagarin.data.repositories.SettingsRepository
import com.niksah.gagarin.domain.ApiRepositoryImpl
import com.niksah.gagarin.domain.ResponseRepositoryImpl
import com.niksah.gagarin.domain.SettingsRepositoryImpl
import org.koin.dsl.module

val domainModule = module {
    single<ApiRepository> { ApiRepositoryImpl(get()) }
    single<ResponseRepository> { ResponseRepositoryImpl() }
    single<SettingsRepository> { SettingsRepositoryImpl(get()) }
}