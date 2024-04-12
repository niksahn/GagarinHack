package com.niksah.gagarin.data.repositories

import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SettingsListener

interface SettingsRepository {
    val id: NullableStringSettingConfig
    fun clear()
}

class NullableStringSettingConfig(settings: Settings, key: String) :
    NullableSettingConfig<String>(settings, key) {

    override fun getStringValue(settings: Settings, key: String): String? =
        settings.getStringOrNull(key)

    override fun setStringValue(settings: Settings, key: String, value: String) =
        settings.putString(key, value)

    override fun addListener(
        settings: ObservableSettings,
        key: String,
        callback: (String?) -> Unit
    ): SettingsListener =
        settings.addStringOrNullListener(key, callback)
}

sealed class NullableSettingConfig<T : Any>(
    settings: Settings,
    key: String
) : SettingConfig<T?>(settings, key, null) {

    protected abstract fun getStringValue(settings: Settings, key: String): String?

    final override fun getStringValue(settings: Settings, key: String, defaultValue: T?): String? =
        getStringValue(settings, key)

    protected abstract fun addListener(
        settings: ObservableSettings,
        key: String,
        callback: (T?) -> Unit
    ): SettingsListener

    final override fun addListener(
        settings: ObservableSettings,
        key: String,
        defaultValue: T?,
        callback: (T?) -> Unit
    ): SettingsListener =
        addListener(settings, key, null, callback)
}

sealed class SettingConfig<T>(
    private val settings: Settings,
    val key: String,
    private val defaultValue: T
) {
    protected abstract fun getStringValue(settings: Settings, key: String, defaultValue: T): String?
    protected abstract fun setStringValue(settings: Settings, key: String, value: String)
    protected abstract fun addListener(
        settings: ObservableSettings,
        key: String,
        defaultValue: T,
        callback: (T) -> Unit
    ): SettingsListener

    private var listener: SettingsListener? = null

    fun remove() = settings.remove(key)
    fun exists(): Boolean = settings.hasKey(key)

    fun get(): String? = getStringValue(settings, key, defaultValue)
    fun set(value: String): Boolean {
        return try {
            setStringValue(settings, key, value)
            true
        } catch (exception: Exception) {
            false
        }
    }

    var isLoggingEnabled: Boolean
        get() = listener != null
        set(value) {
            val settings = settings as? ObservableSettings ?: return
            listener = if (value) {
                listener?.deactivate() // just in case
                addListener(settings, key, defaultValue) { println("$key = ${get()}") }
            } else {
                listener?.deactivate()
                null
            }
        }

    override fun toString() = key
}