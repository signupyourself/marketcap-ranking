plugins {
    id("java")
}

repositories {
    mavenCentral()
}

dependencies {

    //rabbitmq
    implementation("com.rabbitmq:amqp-client:5.28.0")

    //log4j classic
    implementation("ch.qos.logback:logback-classic:1.5.26")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}