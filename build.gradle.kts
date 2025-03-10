buildscript {
    val agp_version by extra("8.7.0")
}
plugins {
    id("com.android.application") version "8.7.3" apply false
    id("org.jetbrains.kotlin.android") version "2.0.0" apply false
    id("org.jetbrains.kotlin.plugin.compose") version "2.1.10" apply false
    id("com.google.dagger.hilt.android") version "2.51.1" apply false
}