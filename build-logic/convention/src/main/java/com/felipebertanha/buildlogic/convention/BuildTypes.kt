package com.felipebertanha.buildlogic.convention

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.BuildType
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryExtension
import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

internal fun Project.configBuildTypes(
    commonExtensions: CommonExtension<*, *, *, *, *, *>, extensionType: ExtensionType
) {
    commonExtensions.run {

        buildFeatures {
            buildConfig = true
        }

        val apiKey = gradleLocalProperties(rootDir, providers).getProperty("API_KEY")
        when (extensionType) {
            ExtensionType.APPLICATION -> {
                extensions.configure<ApplicationExtension> {
                    buildTypes {
                        debug {
                            configureDebugBuildType(apiKey)
                        }

                        release {
                            configureReleaseBuildType(commonExtensions, apiKey)
                        }
                    }
                }
            }

            ExtensionType.LIBRARY -> {
                extensions.configure<LibraryExtension> {
                    buildTypes {
                        debug {
                            configureDebugBuildType(apiKey)
                        }

                        release {
                            configureReleaseBuildType(commonExtensions, apiKey)
                        }
                    }
                }
            }
        }

    }

}

private fun BuildType.configureDebugBuildType(apiKey: String) {
    buildConfigField("String", "API_KEY", apiKey)
    buildConfigField("String", "BASE_URL", "\"https://runique.pl-coding.com:8080\"")
}

private fun BuildType.configureReleaseBuildType(
    commonExtensions: CommonExtension<*, *, *, *, *, *>, apiKey: String
) {
    buildConfigField("String", "API_KEY", apiKey)
    buildConfigField("String", "BASE_URL", "\"https://runique.pl-coding.com:8080\"")

    isMinifyEnabled = true
    proguardFiles(
        commonExtensions.getDefaultProguardFile("proguard-android-optimize.txt"),
        "proguard-rules.pro"
    )
}