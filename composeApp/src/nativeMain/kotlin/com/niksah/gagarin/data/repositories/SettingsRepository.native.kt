package com.niksah.gagarin.data.repositories

import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.Settings


actual val factory: Settings.Factory = NSUserDefaultsSettings.Factory()
