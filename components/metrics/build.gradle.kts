plugins {
    id("java")
    id("application")
}

repositories {
    mavenCentral()
}

dependencies {

    implementation("io.dropwizard.metrics:metrics-core:4.2.38")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}