package com.niksah.gagarin.domain

import com.niksah.gagarin.data.repositories.FileRepository
import kotlinx.coroutines.flow.MutableStateFlow

class FileRepositoryImpl : FileRepository {
    override val file: MutableStateFlow<File?> = MutableStateFlow(null)

    data class File(
        val id: String
    )
}
