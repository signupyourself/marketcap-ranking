rootProject.name = "project"

include("applications:analyzer")
include("applications:collector")
//include("applications:frontend")
include("applications:webapp")
include("components:db-support")
include("components:utils")
include("components:rabbitmq-support")

pluginManagement {
    repositories {
        maven { url = uri("https://repo.spring.io/snapshot") }
        gradlePluginPortal()
    }
}