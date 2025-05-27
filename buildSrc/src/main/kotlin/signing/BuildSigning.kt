package signing

import com.android.build.api.dsl.ApkSigningConfig
import extensions.getSigningProperty
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Project

sealed class BuildSigning(val name: String) {

    abstract fun create(container: NamedDomainObjectContainer<out ApkSigningConfig>, project: Project)

    object Release : BuildSigning(SigningTypes.RELEASE) {
        override fun create(container: NamedDomainObjectContainer<out ApkSigningConfig>, project: Project) {
            container.maybeCreate(name).apply {
                storeFile = project.rootProject.file(project.getSigningProperty("release_key.store"))
                storePassword = project.getSigningProperty("release_store.password")
                keyAlias = project.getSigningProperty("release_key.alias")
                keyPassword = project.getSigningProperty("release_key.password")
                enableV1Signing = true
                enableV2Signing = true
            }
        }
    }

    object ReleaseExternalQa : BuildSigning(SigningTypes.RELEASE_EXTERNAL_QA) {
        override fun create(container: NamedDomainObjectContainer<out ApkSigningConfig>, project: Project) {
            container.maybeCreate(name).apply {
                storeFile = project.rootProject.file(project.getSigningProperty("qa_key.store"))
                storePassword = project.getSigningProperty("qa_store.password")
                keyAlias = project.getSigningProperty("qa_key.alias")
                keyPassword = project.getSigningProperty("qa_key.password")
                enableV1Signing = true
                enableV2Signing = true
            }
        }
    }

    object Debug : BuildSigning(SigningTypes.DEBUG) {
        override fun create(container: NamedDomainObjectContainer<out ApkSigningConfig>, project: Project) {
            container.getByName(name).apply {
                storeFile = project.rootProject.file(project.getSigningProperty("dev_key.store"))
                storePassword = project.getSigningProperty("dev_store.password")
                keyAlias = project.getSigningProperty("dev_key.alias")
                keyPassword = project.getSigningProperty("dev_key.password")
                enableV1Signing = true
                enableV2Signing = true
            }
        }
    }
}
