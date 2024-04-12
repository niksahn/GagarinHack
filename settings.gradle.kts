rootProject.name = "GagarinHak"
include(":composeApp")

pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://download2.dynamsoft.com/maven/aar")
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}
