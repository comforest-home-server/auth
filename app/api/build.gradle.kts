import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.springframework.boot")
    kotlin("plugin.spring")
}

dependencies {
    val springVersion by properties
    val coroutineVersion by properties
    implementation(project(":core"))
    implementation(project(":support:cache"))
    implementation(project(":support:yaml"))

    implementation("jakarta.servlet:jakarta.servlet-api:6.0.0")
    implementation("org.springframework.boot:spring-boot-starter-webflux:$springVersion")
    runtimeOnly("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:$coroutineVersion")
    implementation("org.springdoc:springdoc-openapi-starter-webflux-ui:2.5.0")

    testImplementation("org.springframework.boot:spring-boot-starter-test:$springVersion")
}

tasks {
    withType<Jar> { enabled = false }
    withType<BootJar> { enabled = true }
}
