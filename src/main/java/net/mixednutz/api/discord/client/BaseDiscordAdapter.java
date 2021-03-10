package net.mixednutz.api.discord.client;

import java.util.function.Consumer;
import java.util.function.Function;

import org.javacord.api.DiscordApi;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.javacord.connect.DiscordConnectionFactory;

public class BaseDiscordAdapter {
	
	protected DiscordConnectionFactory connectionFactory;
	protected ConnectionData connectionData;

	public BaseDiscordAdapter(DiscordConnectionFactory connectionFactory, 
			ConnectionData connectionData) {
		super();
		this.connectionFactory = connectionFactory;
		this.connectionData = connectionData;
	}

	protected <T> T applyWithApi(Function<DiscordApi, T> function) {
		Connection<DiscordApi> conn = connectionFactory.createConnection(connectionData);
		try {
			return function.apply(conn.getApi());
		} catch (Exception e) {
			throw e;
		} finally {
			connectionFactory.destroyConnection(conn);
		}
	}
	
	protected void acceptWithApi(Consumer<DiscordApi> consumer) {
		Connection<DiscordApi> conn = connectionFactory.createConnection(connectionData);
		try {
			consumer.accept(conn.getApi());
		} catch (Exception e) {
			throw e;
		} finally {
			connectionFactory.destroyConnection(conn);
		}
	}
	
}
