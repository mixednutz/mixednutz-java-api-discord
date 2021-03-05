package net.mixednutz.api.discord.client;

import org.javacord.api.DiscordApi;
import org.springframework.social.connect.Connection;

import net.mixednutz.api.client.GroupClient;
import net.mixednutz.api.client.MixednutzClient;
import net.mixednutz.api.client.PostClient;
import net.mixednutz.api.client.TimelineClient;
import net.mixednutz.api.client.UserClient;
import net.mixednutz.api.discord.model.MessageForm;

public class DiscordAdapter implements MixednutzClient {
	
	private Connection<DiscordApi> conn;
	private Long defaultChannelId;
	
	private MessageAdapter messageAdapter;

	public DiscordAdapter(Connection<DiscordApi> conn, Long defaultChannelId) {
		super();
		this.conn = conn;
		this.defaultChannelId = defaultChannelId;
		initSubApis();
	}

	@Override
	public GroupClient getGroupClient() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PostClient<MessageForm> getPostClient() {
		return messageAdapter;
	}

	@Override
	public TimelineClient<?> getTimelineClient() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserClient<?> getUserClient() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void initSubApis() {
		messageAdapter = new MessageAdapter(conn, defaultChannelId);
	}

}
