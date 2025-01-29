plugins {
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    implementation(projects.datastore.currency)
    implementation(projects.feature.currencyApi)
    implementation(projects.network)
    implementation(libs.datastore.preferences)
    implementation(libs.kotlinx.coroutines.core)
}