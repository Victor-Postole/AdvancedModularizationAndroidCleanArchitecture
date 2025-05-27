package extensions

import com.android.build.api.dsl.ApplicationBuildType
import org.gradle.api.Project
import java.util.Properties

private const val SIGNING_PROPERTIES_FILE_NAME = "dev_credentials.properties"

fun Project.getSigningProperty(propertyName: String): String {
    val props = Properties()
    val file = rootProject.file(SIGNING_PROPERTIES_FILE_NAME)
    if (file.exists()) {
        props.load(file.inputStream())
    }
    return props.getProperty(propertyName)
        ?: error("Property '$propertyName' not found in $SIGNING_PROPERTIES_FILE_NAME")
}

fun ApplicationBuildType.buildConfigStringField(name: String, value: String) {
    this.buildConfigField("String",name, value)
}

fun ApplicationBuildType.buildConfigIntField(name: String, value: String) {
    this.buildConfigField("int",name, value)
}

fun ApplicationBuildType.buildConfigBooleanField(name: String, value: String) {
    this.buildConfigField("boolean",name, value)
}

