package net.mixednutz.api.discord.provider;

import org.javacord.api.DiscordApi;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.javacord.connect.DiscordConnectionFactory;

import net.mixednutz.api.core.provider.AbstractApiProvider;
import net.mixednutz.api.discord.DiscordFeedType;
import net.mixednutz.api.discord.client.DiscordAdapter;
import net.mixednutz.api.model.INetworkInfoSmall;
import net.mixednutz.api.provider.IOauth1Credentials;

public class DiscordProvider extends AbstractApiProvider<DiscordAdapter, IOauth1Credentials> {

	private DiscordConnectionFactory connectionFactory;
	private Long defaultChannelId;
	
	public DiscordProvider(DiscordConnectionFactory connectionFactory, Long defaultChannelId) {
		super(DiscordAdapter.class, IOauth1Credentials.class);
		this.connectionFactory = connectionFactory;
		this.defaultChannelId = defaultChannelId;
	}

	@Override
	public DiscordAdapter getApi(IOauth1Credentials creds) {
		return new DiscordAdapter(
				connectionFactory, createConnectionData(creds), defaultChannelId);
	}
	
	protected ConnectionData createConnectionData(IOauth1Credentials creds) {
		/*
		 * For Discord Bots, we don't have any auth, because the owner has already authorized us
		 */
		return new ConnectionData(creds.getProviderId(), null, null, null, null, 
				null, null, null, null);
	}

	@Override
	public String getProviderId() {
		return connectionFactory.getProviderId();
	}
	
	protected Connection<DiscordApi> createConnection(ConnectionData cd) {
		return connectionFactory.createConnection(cd);
	}
	
	@Override
	public INetworkInfoSmall getNetworkInfo() {
		return new DiscordFeedType();
	}

}
