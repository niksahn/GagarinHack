package com.niksah.gagarin.data.repositories

import com.niksah.gagarin.domain.FileRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow

interface FileRepository {
    val file: MutableStateFlow<FileRepositoryImpl.File?>
}