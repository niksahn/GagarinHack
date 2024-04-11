package com.niksah.gagarin.di

import com.niksah.gagarin.data.repositories.ApiRepository
import com.niksah.gagarin.data.repositories.FileRepository
import com.niksah.gagarin.domain.ApiRepositoryImpl
import com.niksah.gagarin.domain.FileRepositoryImpl
import org.koin.dsl.module

val domainModule = module {
    single<ApiRepository> { ApiRepositoryImpl(get()) }
    single<FileRepository> { FileRepositoryImpl() }
}