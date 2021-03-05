package net.mixednutz.api.discord.client;

import org.javacord.api.DiscordApi;
import org.javacord.api.entity.channel.Channel;
import org.springframework.social.connect.Connection;

import net.mixednutz.api.client.PostClient;
import net.mixednutz.api.discord.model.MessageForm;

public class MessageAdapter implements PostClient<MessageForm> {
	
	private Connection<DiscordApi> conn;
	private Long defaultChannelId;

	public MessageAdapter(Connection<DiscordApi> conn, Long defaultChannelId) {
		super();
		this.conn = conn;
		this.defaultChannelId = defaultChannelId;
	}

	@Override
	public MessageForm create() {
		return new MessageForm();
	}

	@Override
	public void postToTimeline(MessageForm post) {
		Channel channel = conn.getApi().getChannelById(
				post.getChannelId()!=null?post.getChannelId():defaultChannelId)
				.orElseThrow(()->new IllegalArgumentException("Channel not found"));
		
		channel.asTextChannel().get().sendMessage(post.toContent());
	}

}
