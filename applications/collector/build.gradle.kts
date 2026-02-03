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

    implementation("com.rabbitmq:amqp-client:5.28.0")

    //log4j classic
    implementation("ch.qos.logback:logback-classic:1.5.26")

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