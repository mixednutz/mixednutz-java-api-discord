package net.mixednutz.api.discord.model;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.javacord.api.entity.message.Message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import net.mixednutz.api.core.model.TimelineElement;
import net.mixednutz.api.discord.DiscordFeedType;
import net.mixednutz.api.model.ITimelineElement;

@JsonIgnoreProperties(ignoreUnknown=true)
public class MessageElement extends TimelineElement implements ITimelineElement {
	
	private static TimelineElement.Type TYPE = new TimelineElement.Type(){
		DiscordFeedType feedType = DiscordFeedType.getInstance();
		@Override
		public String getName() {return "message";}
		@Override
		public String getNamespace() {return feedType.getHostName();}
		@Override
		public String getId() {return feedType.getId()+"_"+getName();}
		};

	public MessageElement() {
		super();
	}
	
	public MessageElement(Message message) {
		super();
		this.setType(TYPE);
		this.setPostedOnDate(ZonedDateTime.ofInstant(
				message.getCreationTimestamp(), ZoneId.systemDefault()));
		this.setPaginationId(message.getIdAsString());
		this.setDescription(message.getContent());
		this.setProviderId(message.getIdAsString());
		this.setUrl(message.getLink().toString());
		try {
			this.setUri(message.getLink().toURI().getPath());
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

}
