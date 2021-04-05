package net.mixednutz.api;

import org.javacord.api.DiscordApi;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.social.connect.web.CredentialsCallback;
import org.springframework.social.connect.web.CredentialsInterceptor;
import org.springframework.social.javacord.connect.DiscordConnectionFactory;

import net.mixednutz.api.discord.provider.DiscordProvider;
import net.mixednutz.api.provider.IOauth1Credentials;

@Profile("discord")
@Configuration
@ConfigurationProperties(prefix="mixednutz.social")
@ComponentScan("net.mixednutz.discord")
public class DiscordConfig {
	
	private DiscordConnectionProperties discord = new DiscordConnectionProperties();
	
	@Bean
	public DiscordConnectionFactory discordConnectionFactory() {
		return new DiscordConnectionFactory(discord.token);
	}
	@Bean
	public DiscordProvider discordService() {
		return new DiscordProvider(discordConnectionFactory(), discord.defaultChannelId);
	}
	
	@Bean
	public CredentialsInterceptor<DiscordApi, IOauth1Credentials> discordCredentialsInterceptor(CredentialsCallback callback) {
		return new DiscordCredentialsInterceptor(callback);
	}
	
	public DiscordConnectionProperties getDiscord() {
		return discord;
	}
	public void setDiscord(DiscordConnectionProperties discord) {
		this.discord = discord;
	}
	
	public static class DiscordCredentialsInterceptor extends CredentialsInterceptor<DiscordApi, IOauth1Credentials> {

		public DiscordCredentialsInterceptor(CredentialsCallback callback) {
			super(DiscordApi.class, IOauth1Credentials.class, callback);
		}
		
	}

	public static class DiscordConnectionProperties {
		
		private String token;
		private Long defaultChannelId;
		
		public String getToken() {
			return token;
		}
		public void setToken(String token) {
			this.token = token;
		}
		public Long getDefaultChannelId() {
			return defaultChannelId;
		}
		public void setDefaultChannelId(Long defaultChannelId) {
			this.defaultChannelId = defaultChannelId;
		}		
	}

}
