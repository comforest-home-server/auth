import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.springframework.boot")
    kotlin("plugin.spring")
}

dependencies {
    val springVersion by properties
    val coroutineVersion by properties

    implementation(project(":adaptor:rdb"))
    implementation(project(":adaptor:redis"))
    implementation(project(":adaptor:social"))
    api(project(":domain"))
    api(project(":port"))
    implementation(project(":port"))

    implementation("org.springframework.boot:spring-boot-starter:$springVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutineVersion")
    implementation("jakarta.transaction:jakarta.transaction-api:2.0.1")

    testImplementation("org.springframework.boot:spring-boot-starter-test:$springVersion")
}

tasks {
    withType<Jar> { enabled = true }
    withType<BootJar> { enabled = false }
}
