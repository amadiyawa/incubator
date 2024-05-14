rootProject.name = "IncubaTor"

include(
    ":app",
    ":feature_auth",
    ":feature_incubator",
    ":feature_base"
)

pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenLocal()
        mavenCentral()
    }
}

// Enables the feature preview for type-safe project accessors in Gradle.
// Type-safe project accessors provide a type-safe and refactoring-friendly way to access other projects.
// This feature is particularly useful in a multi-module project setup, where you often need to declare dependencies on other projects.
// By enabling this feature, you can access other projects in your settings.gradle.kts file in a type-safe manner.
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")