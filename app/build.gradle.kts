plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)

    //alias(libs.plugins.room)
    alias(libs.plugins.hilt)
    //alias(libs.plugins.glide)
    alias(libs.plugins.ksp)


    alias(libs.plugins.compose.compiler)

    id("kotlin-parcelize")
    //id 'kotlin-android-extensions'
    //id 'kotlin-kapt'
    //id 'androidx.navigation.safeargs.kotlin'
    //id 'dagger.hilt.android.plugin'

}

android {
    namespace = "ru.ayuandrey.neewsapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "ru.ayuandrey.neewsapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        viewBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

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


// Lifecycle
    implementation (libs.androidx.lifecycle.viewmodel.ktx)
    implementation (libs.androidx.lifecycle.runtime.ktx.v241)
    implementation (libs.androidx.lifecycle.viewmodel.ktx.v241)

// Room
    implementation (libs.androidx.room.runtime)
    implementation (libs.androidx.room.ktx)
    ksp (libs.androidx.room.compiler)

// Coroutines
    implementation (libs.kotlinx.coroutines.core)
    implementation (libs.kotlinx.coroutines.android)

// Hilt
    implementation (libs.hilt.android)
    implementation (libs.androidx.hilt.navigation.compose)
    ksp (libs.hilt.compiler)


// Retrofit
    implementation (libs.retrofit)
    implementation (libs.converter.gson)

// OkHTTP
    implementation (libs.logging.interceptor)

// Navigation Components
    implementation (libs.androidx.navigation.fragment.ktx)
    implementation (libs.androidx.navigation.ui.ktx)

// Glide
    implementation (libs.glide)
    ksp (libs.compiler)

    //Splash Api
    implementation (libs.androidx.core.splashscreen)


    //Coil
    implementation(libs.coil.compose.v240)

    //Datastore
    implementation (libs.androidx.datastore.preferences.v111)

    //Compose Foundation
    implementation (libs.androidx.foundation.v143)

    //Accompanist
    implementation (libs.google.accompanist.systemuicontroller)

    //Paging 3

    implementation (libs.androidx.paging.runtime)
    implementation (libs.androidx.paging.compose.v320rc01)

    implementation (libs.androidx.core.splashscreen)

    implementation (libs.androidx.datastore.preferences)
}