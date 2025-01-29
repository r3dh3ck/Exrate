enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Exrate"

include(
    ":app",
    ":core",
    ":datastore:currency",
    ":feature:coin",
    ":feature:currency-api",
    ":feature:currency-impl",
    ":feature:select-currency",
    ":feature:settings",
    ":network",
    ":widget"
)