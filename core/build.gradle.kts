plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.core"
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
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.ui)
}