package com.example.hazelcastdemo.config

import com.hazelcast.config.Config
import com.hazelcast.config.MapConfig
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.concurrent.TimeUnit

@Configuration
class HazelcastConfig {
    private val testMap = "testMap"
    private val cacheGroupNamePrefix = "test-group"

    @Bean
    fun devHazelcastConfig(): Config {
        val config = Config()
        config.clusterName = cacheGroupNamePrefix

        config.setProperty("hazelcast.logging.type", "slf4j")
        config.setProperty("hazelcast.shutdownhook.enabled", "false")
        config.setProperty("hazelcast.phone.home.enabled", "false")

        config.addMapConfig(MapConfig().setName(testMap)
                .setAsyncBackupCount(1)
                .setReadBackupData(false)
                .setTimeToLiveSeconds(Math.toIntExact(TimeUnit.MINUTES.toSeconds(30))))

        val join = config.networkConfig.join
        join.multicastConfig.isEnabled = false
        join.awsConfig.isEnabled = false
        join.tcpIpConfig
                .setMembers(listOf("localhost")).isEnabled = true
        return config
    }
}