package net.mixednutz.api.discord.client;

import org.springframework.social.connect.ConnectionData;
import org.springframework.social.javacord.connect.DiscordConnectionFactory;

import net.mixednutz.api.client.GroupClient;
import net.mixednutz.api.client.MixednutzClient;
import net.mixednutz.api.client.PostClient;
import net.mixednutz.api.client.TimelineClient;
import net.mixednutz.api.client.UserClient;
import net.mixednutz.api.discord.model.MessageForm;

public class DiscordAdapter extends BaseDiscordAdapter implements MixednutzClient {
	
	private Long defaultChannelId;
	
	private MessageAdapter messageAdapter;

	public DiscordAdapter(DiscordConnectionFactory connectionFactory, 
			ConnectionData connectionData, Long defaultChannelId) {
		super(connectionFactory, connectionData);
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
		messageAdapter = new MessageAdapter(connectionFactory, connectionData, 
				defaultChannelId);
	}

}
