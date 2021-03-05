package net.mixednutz.api;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix="mixednutz.social")
public class DiscordConfig {

}
