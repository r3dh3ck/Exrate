plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.example.feature.selectcurrency"
    compileSdk = libs.versions.android.sdk.compile.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.sdk.min.get().toInt()
    }
    compileOptions {
        sourceCompatibility(libs.versions.java.source.get())
        targetCompatibility(libs.versions.java.target.get())
    }
    kotlinOptions {
        jvmTarget = libs.versions.kotlin.target.get()
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(projects.core)
    implementation(projects.feature.currencyApi)
    implementation(projects.widget)
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.material)
    implementation(libs.compose.material.icons.core)
    implementation(libs.compose.navigation)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.graphics)
    implementation(libs.compose.ui.tooling)
    implementation(libs.dagger)
    implementation(libs.kotlinx.collections.immutable)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.serialization.core)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.lifecycle.viewmodel.compose)
    ksp(libs.dagger.compiler)
}