plugins {
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    implementation(projects.feature.currencyApi)
    implementation(libs.datastore.preferences)
    implementation(libs.kotlinx.coroutines.core)
}