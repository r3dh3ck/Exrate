plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.example.exrate"
    compileSdk = libs.versions.android.sdk.compile.get().toInt()
    defaultConfig {
        applicationId = "com.example.exrate"
        minSdk = libs.versions.android.sdk.min.get().toInt()
        targetSdk = libs.versions.android.sdk.target.get().toInt()
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunnerArguments["clearPackageData"] = "true"
    }
    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            signingConfig = signingConfigs.getByName("debug")
            val androidOptimizeFile = getDefaultProguardFile("proguard-android-optimize.txt")
            proguardFile(androidOptimizeFile)
        }
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
    testOptions {
        execution = "ANDROIDX_TEST_ORCHESTRATOR"
    }
}

dependencies {
    implementation(projects.core)
    implementation(projects.datastore.currency)
    implementation(projects.feature.coin)
    implementation(projects.feature.currencyApi)
    implementation(projects.feature.currencyImpl)
    implementation(projects.feature.selectCurrency)
    implementation(projects.feature.settings)
    implementation(projects.network)
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.material)
    implementation(libs.compose.navigation)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.graphics)
    implementation(libs.dagger)
    implementation(libs.datastore.preferences)
    implementation(libs.kotlinx.collections.immutable)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.okhttp)
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.kotlinx.serialization)
    ksp(libs.dagger.compiler)
    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.mockk)
    testImplementation(libs.turbine)
    androidTestImplementation(projects.widget)
    androidTestImplementation(libs.compose.ui.test.junit4)
    androidTestImplementation(libs.kakao.compose)
    androidTestImplementation(libs.kaspresso)
    androidTestImplementation(libs.kaspresso.compose)
    androidTestUtil(libs.orchestrator)
}