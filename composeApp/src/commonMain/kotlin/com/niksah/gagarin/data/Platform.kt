package com.niksah.gagarin.data

enum class Platform {
    Ios, Android, Web, Pc;
}

object CurrentPlatform {
    val current: Platform
        get() = getCurrentPlatform()
}

expect fun getCurrentPlatform(): Platform