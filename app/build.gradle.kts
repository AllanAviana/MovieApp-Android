plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.devtools.ksp") version "2.0.0-1.0.21"
    id("com.google.dagger.hilt.android")
    kotlin("kapt")


}

android {
    namespace = "com.example.movieapp_android"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.movieapp_android"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.8.1")

    testImplementation ("app.cash.turbine:turbine:1.0.0")

    implementation("com.google.accompanist:accompanist-navigation-animation:0.32.0")

    implementation ("androidx.core:core-splashscreen:1.0.0")

    implementation ("com.github.MahboubehSeyedpour:jetpack-loading:1.1.0")

    implementation ("androidx.room:room-runtime:2.6.1")
    kapt ("androidx.room:room-compiler:2.6.1")
    implementation ("androidx.room:room-ktx:2.6.1")


    implementation("com.google.accompanist:accompanist-pager-indicators:0.32.0")


    implementation("androidx.compose.foundation:foundation:1.6.1")


    implementation("androidx.compose.material:material-icons-extended")


    // Coil - Library for image loading
    implementation("io.coil-kt:coil-compose:2.0.0") // Coil with support for Jetpack Compose, to efficiently load images

    // Hilt - Library for Dependency Injection (DI)
    implementation("com.google.dagger:hilt-android:2.51.1") // Main Hilt library for dependency injection in Android
    kapt("com.google.dagger:hilt-compiler:2.51.1")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0") // Support for navigation in Jetpack Compose with Hilt


    // Declarative navigation for Jetpack Compose
    implementation("androidx.navigation:navigation-compose:2.7.7")
    
    // Retrofit - Library for network calls (REST API)
    implementation("com.squareup.retrofit2:retrofit:2.11.0") // Main Retrofit dependency for making HTTP requests
    implementation("com.squareup.retrofit2:converter-gson:2.11.0") // Gson converter to serialize and deserialize JSON automatically

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}