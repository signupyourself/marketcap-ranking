plugins {
    id("java")
    id("application")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

application {
    mainClass = "com.analyzer.Analyzer"
}

repositories {
    mavenCentral()
}

dependencies {

    //rabbitmq
    implementation("com.rabbitmq:amqp-client:5.28.0")

    implementation(project(":components:rabbitmq-support"))

    implementation("ch.qos.logback:logback-classic:1.2.11")
    implementation(project(":components:db-support"))
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

buildscript {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
    dependencies {
        classpath ("gradle.plugin.com.github.johnrengelman:shadow:7.1.2")
	classpath ("gradle.plugin.com.github.johnrengelman:shadow:7.1.2")
 
   }
}
