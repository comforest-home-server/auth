import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.springframework.boot")
    kotlin("plugin.spring")
}

dependencies {
    val springVersion by properties
    val coroutineVersion by properties

    implementation(project(":adaptor-out:rdb"))
    implementation(project(":adaptor-out:redis"))
    implementation(project(":adaptor-out:social"))
    api(project(":domain"))
    api(project(":port-in"))
    implementation(project(":port-out"))

    implementation("org.springframework.boot:spring-boot-starter:$springVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutineVersion")
    implementation("jakarta.transaction:jakarta.transaction-api:2.0.1")

    testImplementation("org.springframework.boot:spring-boot-starter-test:$springVersion")
}

tasks {
    withType<Jar> { enabled = true }
    withType<BootJar> { enabled = false }
}
