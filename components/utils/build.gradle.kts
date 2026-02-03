plugins {
    id("java")
    id("application")
}


repositories {
    mavenCentral()
}

dependencies {
    implementation("org.apache.commons:commons-text:1.15.0")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}