package signing

import build.Build
import build.BuildTypes
import build.BuildVariables
import com.android.build.api.dsl.ApplicationBuildType
import extensions.buildConfigBooleanField
import extensions.buildConfigIntField
import extensions.buildConfigStringField
import extensions.getSigningProperty
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Project

sealed class BuildCreator(val name: String) {
    abstract fun create(container: NamedDomainObjectContainer<out ApplicationBuildType>): ApplicationBuildType

    class Debug(private val project: Project) : BuildCreator(BuildTypes.DEBUG) {
        override fun create(container: NamedDomainObjectContainer<out ApplicationBuildType>): ApplicationBuildType {
            return container.getByName(name) {
                applicationIdSuffix = Build.Debug.applicationIdSuffix
                versionNameSuffix = Build.Debug.versionNameSuffix
                isMinifyEnabled = Build.Debug.isMinifyEnabled
                enableUnitTestCoverage = Build.Debug.enableUnitTestCoverage
                isDebuggable = Build.Debug.isDebuggable

                buildConfigStringField(BuildVariables.BASE_URL, project.getSigningProperty("dev.debug_endpoint"))
                buildConfigStringField(BuildVariables.MAP_KEY, project.getSigningProperty("dev.map"))
                buildConfigIntField(BuildVariables.DB_VERSION, project.getSigningProperty("dev.db_version"))
                buildConfigBooleanField(BuildVariables.CAN_CLEAR_CACHE, project.getSigningProperty("dev.clear_cache"))
            }
        }
    }

    class Release(private val project: Project) : BuildCreator(BuildTypes.RELEASE) {
        override fun create(container: NamedDomainObjectContainer<out ApplicationBuildType>): ApplicationBuildType {
            return container.getByName(name) {
                isMinifyEnabled = Build.Release.isMinifyEnabled
                enableUnitTestCoverage = Build.Release.enableUnitTestCoverage
                isDebuggable = Build.Release.isDebuggable

                buildConfigStringField(BuildVariables.BASE_URL, project.getSigningProperty("dev.prod_endpoint"))
                buildConfigStringField(BuildVariables.MAP_KEY, project.getSigningProperty("release.map_key"))
                buildConfigIntField(BuildVariables.DB_VERSION, project.getSigningProperty("dev.db_version"))
                buildConfigBooleanField(BuildVariables.CAN_CLEAR_CACHE, project.getSigningProperty("dev.clear_cache"))
            }
        }
    }

    class ReleaseExternalQa(private val project: Project) : BuildCreator(BuildTypes.RELEASE_EXTERNAL_QA) {
        override fun create(container: NamedDomainObjectContainer<out ApplicationBuildType>): ApplicationBuildType {
            return container.create(name) {
                applicationIdSuffix = Build.ReleaseExternalQa.applicationIdSuffix
                versionNameSuffix = Build.ReleaseExternalQa.versionNameSuffix
                isMinifyEnabled = Build.ReleaseExternalQa.isMinifyEnabled
                enableUnitTestCoverage = Build.ReleaseExternalQa.enableUnitTestCoverage

                buildConfigStringField(BuildVariables.BASE_URL, project.getSigningProperty("dev.qa_endpoint"))
                buildConfigStringField(BuildVariables.MAP_KEY, project.getSigningProperty("release.map_key"))
                buildConfigIntField(BuildVariables.DB_VERSION, project.getSigningProperty("dev.db_version"))
                buildConfigBooleanField(BuildVariables.CAN_CLEAR_CACHE, project.getSigningProperty("dev.clear_cache"))
            }
        }
    }
}
