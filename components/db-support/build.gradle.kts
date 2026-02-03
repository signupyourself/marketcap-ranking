plugins {
    id("java")
    id("application")
}

repositories {
    mavenCentral()
}

dependencies {

    implementation(project(":components:utils"))

    implementation("com.rabbitmq:amqp-client:5.28.0")

    implementation("org.postgresql:postgresql:42.7.8")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}