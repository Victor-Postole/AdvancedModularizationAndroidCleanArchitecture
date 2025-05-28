/**
 * Precompiled [ktlint-settings.gradle.kts][Ktlint_settings_gradle] script plugin.
 *
 * @see Ktlint_settings_gradle
 */
public
class KtlintSettingsPlugin : org.gradle.api.Plugin<org.gradle.api.Project> {
    override fun apply(target: org.gradle.api.Project) {
        try {
            Class
                .forName("Ktlint_settings_gradle")
                .getDeclaredConstructor(org.gradle.api.Project::class.java, org.gradle.api.Project::class.java)
                .newInstance(target, target)
        } catch (e: java.lang.reflect.InvocationTargetException) {
            throw e.targetException
        }
    }
}
