import com.android.build.gradle.internal.dsl.decorator.SupportedPropertyType.Collection.List.type

buildscript {

    dependencies {
        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.40.5")
    }
}// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id ("com.android.application") version "7.1.0" apply false
    id ("com.android.library" ) version "7.1.0" apply false
    id ("org.jetbrains.kotlin.android") version "1.6.10" apply false
    id ("com.google.dagger.hilt.android") version "2.44" apply false
}

