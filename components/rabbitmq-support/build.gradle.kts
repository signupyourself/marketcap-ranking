plugins {
    id("java")
}

repositories {
    mavenCentral()
}

dependencies {

    //rabbitmq
    implementation("com.rabbitmq:amqp-client:5.28.0")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}