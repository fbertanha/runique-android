import com.felipebertanha.buildlogic.convention.configureJvmKotlin
import org.gradle.api.Plugin
import org.gradle.api.Project

class JvmLibraryConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        target.run {
            pluginManager.apply("org.jetbrains.kotlin.jvm")

            configureJvmKotlin()
        }
    }
}