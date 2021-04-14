package net.mixednutz.api.discord.client;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

import org.javacord.api.entity.channel.ChannelType;
import org.javacord.api.entity.channel.TextChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.javacord.connect.DiscordConnectionFactory;

import net.mixednutz.api.client.PostClient;
import net.mixednutz.api.discord.model.DiscordChannel;
import net.mixednutz.api.discord.model.MessageForm;

public class MessageAdapter extends BaseDiscordAdapter implements PostClient<MessageForm> {
	
	private Long defaultChannelId;
	
	private static final Logger log = LoggerFactory.getLogger(MessageAdapter.class);

	public MessageAdapter(DiscordConnectionFactory connectionFactory, 
			ConnectionData connectionData, Long defaultChannelId) {
		super(connectionFactory, connectionData);
		this.defaultChannelId = defaultChannelId;
	}

	@Override
	public MessageForm create() {
		return new MessageForm();
	}

	@Override
	public void postToTimeline(MessageForm post) {
		acceptWithApi((api)->{	
			long channelId = post.getChannelId()!=null ? post.getChannelId() : 
				post.getChannelIdAsString()!=null ? Long.parseLong(post.getChannelIdAsString()) : 
						defaultChannelId;
			TextChannel channel = api.getTextChannelById(
					channelId)
					.orElseThrow(()->new IllegalArgumentException("Channel "+channelId+" not found"));
			try {
				channel.asTextChannel().get().sendMessage(post.toContent())
					.thenAccept((msg)->{
						log.info("Sent message: ID:{}; Timestamp:{}; Channel:{}; Content:{}", 
								msg.getIdAsString(), 
								msg.getCreationTimestamp(),
								msg.getChannel().getIdAsString(),
								msg.getContent());
					}).get();
			} catch (Exception e) {
				log.error("Error sending message to Discord", e);
			} 
		});
	}
	
	public Collection<DiscordChannel> getPostableChannels() {
		return this.applyWithApi((api)->{
			return api.getServerChannels().stream()
					//Text Channels
					.filter(c->ChannelType.SERVER_TEXT_CHANNEL.equals(c.getType()))
					//Bot has permission to post
					.filter(c->c.asTextChannel().get().canYouWrite())
					//convert to custom class
					.map(c->new DiscordChannel(c))
					.collect(Collectors.toList());
		});
	}

	@Override
	public Map<String, Object> referenceDataForPosting() {
		return Collections.singletonMap("channels", getPostableChannels());
	}

}
