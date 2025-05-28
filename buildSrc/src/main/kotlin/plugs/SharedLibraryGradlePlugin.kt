package plugs

import build.BuildConfig
import build.BuildDimensions
import build.BuildTypes
import com.android.build.gradle.LibraryExtension
import dependencies.DependenciesVersions
import flavors.BuildFlavor
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.kotlin
import org.gradle.kotlin.dsl.withType
import signing.BuildCreator
import signing.BuildSigning
import test.TestBuildConfig

class SharedLibraryGradlePlugin: Plugin<Project> {
    override fun apply(project: Project) {

        project.addConfigurations()
        project.applyAndroidConfigurations()
        project.applyKotlinOptions()

    }

    fun Project.addConfigurations() {
        plugins.apply(plugs.BuildPlugins.KOTLIN_ANDROID)
        plugins.apply(plugs.BuildPlugins.KAPT)
        plugins.apply(plugs.BuildPlugins.KTLINT)
        plugins.apply(plugs.BuildPlugins.SPOTLESS)
        plugins.apply(plugs.BuildPlugins.DETEKT)
        plugins.apply(plugs.BuildPlugins.UPDATE_DEPS_VERSIONS)
        plugins.apply(plugs.BuildPlugins.DOKKA)
    }

    fun Project.applyAndroidConfigurations() {
        extensions.getByType(LibraryExtension::class.java).apply {
            compileSdk = BuildConfig.COMPILE_SDK_VERSION

            defaultConfig {
                minSdk = BuildConfig.MIN_SDK_VERSION
                testInstrumentationRunner = TestBuildConfig.TEST_INSTRUMENTATION_RUNNER
            }

            signingConfigs {
                BuildSigning.Release.create(this, project)
                BuildSigning.ReleaseExternalQa.create(this, project)
                BuildSigning.Debug.create(this, project)
            }

            buildTypes {
                BuildCreator.Release(project).createLibrary(this).apply {
                    proguardFiles(
                        getDefaultProguardFile("proguard-android-optimize.txt"),
                        "proguard-rules.pro"
                    )

                    signingConfig = signingConfigs.getByName(BuildTypes.RELEASE)
                }

                BuildCreator.Debug(project).createLibrary(this).apply {
                    signingConfig = signingConfigs.getByName(BuildTypes.DEBUG)
                }

                BuildCreator.ReleaseExternalQa(project).createLibrary(this).apply {
                    signingConfig = signingConfigs.getByName(BuildTypes.RELEASE_EXTERNAL_QA)
                }
            }

            flavorDimensions.add(BuildDimensions.APP)
            flavorDimensions.add(BuildDimensions.STORE)

            productFlavors {
                BuildFlavor.Google.createLibrary(this)
                BuildFlavor.Huawei.createLibrary(this)
                BuildFlavor.Driver.createLibrary(this)
                BuildFlavor.Client.createLibrary(this)
            }

            composeOptions {
                kotlinCompilerExtensionVersion = DependenciesVersions.KOTLIN_COMPILER
            }

            buildFeatures {
                compose = true
                buildConfig = true
            }

            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_1_8
                targetCompatibility = JavaVersion.VERSION_1_8
            }
        }
    }

    fun Project.applyKotlinOptions() {
        tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_1_8.toString()
            }
        }
    }
}