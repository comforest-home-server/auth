pluginManagement {
    val springVersion: String by settings
    val kotlinVersion: String by settings
    plugins {
        id("org.springframework.boot") version springVersion
        kotlin("jvm") version kotlinVersion
        kotlin("kapt") version kotlinVersion
        kotlin("plugin.spring") version kotlinVersion
        kotlin("plugin.jpa") version kotlinVersion
        kotlin("plugin.allopen") version kotlinVersion
        kotlin("plugin.noarg") version kotlinVersion
    }
}

include(
    "app:api",
    "adaptor:rdb",
    "adaptor:redis",
    "adaptor:social",
    "core",
    "domain",
    "port",
    "support:cache",
    "support:yaml",
)
