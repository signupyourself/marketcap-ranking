import org.gradle.kotlin.dsl.maven

rootProject.name = "market_cap_ranking"

include("applications:analyzer")
include("applications:collector")
include("applications:webapp")
include("components:db-support")
include("components:utils")
include("components:rabbitmq-support")
include("components:metrics")

pluginManagement {
    repositories {
        maven { url = uri("https://repo.spring.io/snapshot") }
        gradlePluginPortal()
    }
}