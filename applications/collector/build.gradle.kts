plugins {
    id("java")
    id("application")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

application {
    mainClass = "com.collector.Collector"
}

repositories {
    mavenCentral()
}

dependencies {

    implementation(project(":components:db-support"))
    implementation(project(":components:rabbitmq-support"))

    //rabbitmq
    implementation("com.rabbitmq:amqp-client:5.28.0")

    //junit
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

buildscript {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
    dependencies {
        classpath ("gradle.plugin.com.github.johnrengelman:shadow:7.1.2")
    }
}

tasks.test {
    useJUnitPlatform()
}