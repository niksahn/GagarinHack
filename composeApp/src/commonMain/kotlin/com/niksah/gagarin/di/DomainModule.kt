package com.niksah.gagarin.di

import com.niksah.gagarin.data.repositories.ApiRepository
import com.niksah.gagarin.data.repositories.FileRepository
import com.niksah.gagarin.data.repositories.SettingsRepository
import com.niksah.gagarin.domain.ApiRepositoryImpl
import com.niksah.gagarin.domain.FileRepositoryImpl
import com.niksah.gagarin.domain.SettingsRepositoryImpl
import org.koin.dsl.module

val domainModule = module {
    single<ApiRepository> { ApiRepositoryImpl(get()) }
    single<FileRepository> { FileRepositoryImpl() }
    single<SettingsRepository> { SettingsRepositoryImpl(get()) }
}