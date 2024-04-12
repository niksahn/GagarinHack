package com.niksah.gagarin.domain

import com.niksah.gagarin.data.repositories.NullableStringSettingConfig
import com.niksah.gagarin.data.repositories.SettingsRepository
import com.russhwolf.settings.Settings

class SettingsRepositoryImpl(private val settings: Settings) : SettingsRepository {

    override val id: NullableStringSettingConfig =
        NullableStringSettingConfig(settings, "ID")

    override fun clear() = settings.clear()
}