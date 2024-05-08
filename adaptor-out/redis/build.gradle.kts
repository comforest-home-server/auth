import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    kotlin("kapt")
}

dependencies {
    val springVersion by properties

    api(project(":domain"))
    api(project(":port-out"))

    implementation("org.springframework.boot:spring-boot-starter-data-redis:$springVersion")

    testImplementation("org.springframework.boot:spring-boot-starter-test:$springVersion")
}

tasks {
    withType<Jar> { enabled = true }
    withType<BootJar> { enabled = false }
}
