// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false


    //alias(libs.plugins.room) apply false
    alias(libs.plugins.hilt) apply false
    //alias(libs.plugins.glide) apply false
    alias(libs.plugins.ksp) apply false



    alias(libs.plugins.compose.compiler) apply false

    //id("com.google.devtools.ksp") version "1.8.10-1.0.9" apply false


    //classpath "com.google.dagger:hilt-android-gradle-plugin:2.40.5"
    //classpath "androidx.navigation:navigation-safe-args-gradle-plugin:2.5.0-beta01"

}