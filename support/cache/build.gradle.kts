import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.springframework.boot")
    kotlin("plugin.spring")
}

dependencies {
    val springVersion by properties

    implementation("org.springframework.boot:spring-boot-starter-cache:$springVersion")
    implementation("com.github.ben-manes.caffeine:caffeine:3.1.8")
}

tasks {
    withType<Jar> { enabled = true }
    withType<BootJar> { enabled = false }
}
