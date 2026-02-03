plugins {
    java
    war
    id("org.springframework.boot") version "4.1.0-SNAPSHOT"
    id("io.spring.dependency-management") version "1.1.7"
}


repositories {
    mavenCentral()
    maven { url = uri("https://repo.spring.io/snapshot") }
}

dependencies {


    implementation(project(":components:db-support"))
    implementation(project(":components:utils"))
    implementation(project(":components:metrics"))

    implementation("org.postgresql:postgresql:42.7.8")

    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-webmvc")
    providedRuntime("org.springframework.boot:spring-boot-starter-tomcat-runtime")

    testImplementation("org.springframework.boot:spring-boot-starter-thymeleaf-test")
    testImplementation("org.springframework.boot:spring-boot-starter-webmvc-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

}

tasks.withType<Test> {
    useJUnitPlatform()
}
