package org.springframework.social.javacord.connect;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.springframework.social.ServiceProvider;

public class DiscordServiceProvider implements ServiceProvider<DiscordApi> {
	
	private final String token;

	public DiscordServiceProvider(String token) {
		super();
		this.token = token;
	}

	public DiscordApi getApi() {
		return new DiscordApiBuilder().setToken(token).login().join();
	}
	
}
