import dependencies.androidx
import dependencies.hilt
import dependencies.loginModule
import dependencies.room
import dependencies.testDebugDeps
import dependencies.testDeps
import dependencies.testImplDeps

plugins {
   id(plugs.BuildPlugins.ANDROID_LIBRARY)
   id(plugs.BuildPlugins.KOTLIN_ANDROID)
   kotlin(plugs.BuildPlugins.KAPT)
}

android {
    namespace = "com.sistem.login"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
}

dependencies {
    androidx()
    hilt()
    room()
    testDeps()
    testImplDeps()
    testDebugDeps()
}