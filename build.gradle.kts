// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript{
    extra.apply{
        set("compose_ui_version", "1.5.1")
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.0")
    }
}
plugins {
    id("com.android.application") version "8.2.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("com.google.dagger.hilt.android") version "2.48" apply false
    kotlin("kapt") version "1.9.0"
}