import build.BuildConfig
import build.BuildDimensions
import build.BuildTypes
import dependencies.Dependencies
import dependencies.DependenciesVersions
import dependencies.androidx
import dependencies.hilt
import dependencies.loginModule
import dependencies.okHttp
import dependencies.retrofit
import dependencies.room
import dependencies.testDeps
import dependencies.testImplDeps
import dependencies.testDebugDeps
import dependencies.testImplementation
import flavors.BuildFlavor
import release.RealeaseConfig
import signing.BuildCreator
import signing.BuildSigning
import test.TestBuildConfig
import test.TestDependencies

plugins {
    id(plugs.BuildPlugins.ANDROID_APPLICATION)
    id(plugs.BuildPlugins.KOTLIN_ANDROID)
    id(plugs.BuildPlugins.ANDROID)
    kotlin(plugs.BuildPlugins.KAPT)
}

android {
    namespace = BuildConfig.APP_ID
    compileSdk = BuildConfig.COMPILE_SDK_VERSION

    defaultConfig {
        applicationId = BuildConfig.APP_ID
        minSdk = BuildConfig.MIN_SDK_VERSION
        targetSdk = BuildConfig.TARGET_SDK_VERSION
        versionCode = RealeaseConfig.VERSION_CODE
        versionName = RealeaseConfig.VERSION_NAME

        testInstrumentationRunner = TestBuildConfig.TEST_INSTRUMENTATION_RUNNER
    }

    signingConfigs {
        BuildSigning.Release.create(this, project)
        BuildSigning.ReleaseExternalQa.create(this, project)
        BuildSigning.Debug.create(this, project)
    }

    buildTypes {
        BuildCreator.Release(project).create(this).apply {
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            signingConfig = signingConfigs.getByName(BuildTypes.RELEASE)
        }

        BuildCreator.Debug(project).create(this).apply {
            signingConfig = signingConfigs.getByName(BuildTypes.DEBUG)
        }

        BuildCreator.ReleaseExternalQa(project).create(this).apply {
            signingConfig = signingConfigs.getByName(BuildTypes.RELEASE_EXTERNAL_QA)
        }
    }

    flavorDimensions.add(BuildDimensions.APP)
    flavorDimensions.add(BuildDimensions.STORE)

    productFlavors {
        BuildFlavor.Google.create(this)
        BuildFlavor.Huawei.create(this)
        BuildFlavor.Driver.create(this)
        BuildFlavor.Client.create(this)
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
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = DependenciesVersions.COMPOSE_COMPILER
    }
}



dependencies {
    loginModule()
    androidx()
    hilt()
    room()
    testDeps()
    testImplDeps()
    testDebugDeps()
}