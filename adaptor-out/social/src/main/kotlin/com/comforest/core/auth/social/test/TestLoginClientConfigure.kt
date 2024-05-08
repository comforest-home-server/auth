package com.comforest.core.auth.social.test

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Configuration
class TestLoginClientConfigure {

    @Bean
    @Profile("local", "sandbox")
    internal fun sandboxTestLoginClient(): TestLoginClient = SandboxTestLoginClient()

    @Bean
    @ConditionalOnMissingBean
    internal fun productionTestLoginClient(): TestLoginClient = ProdcutionTestLoginClient()
}
