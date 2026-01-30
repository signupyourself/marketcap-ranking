plugins {
    id("java")
    id("application")
}

repositories {
    mavenCentral()
}

dependencies {

    implementation("io.micrometer:micrometer-registry-prometheus:1.17.0-M1")
    //implementation("io.prometheus:simpleclient_hotspot:0.16.0")
    implementation("io.prometheus:simpleclient_dropwizard:0.16.0")
    implementation("io.prometheus:simpleclient_common:0.16.0")
    implementation("io.dropwizard.metrics:metrics-core:4.2.38")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}