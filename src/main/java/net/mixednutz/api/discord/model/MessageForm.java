package net.mixednutz.api.discord.model;

import java.io.Serializable;

import net.mixednutz.api.model.IPost;

public class MessageForm implements IPost {
	
	Long channelId;
	String channelIdAsString;
	
	//Tweet Builder
	String textPart;
	String urlPart;
	
	public String toContent() {
		if (textPart!=null && urlPart==null) {
			return textPart;
		}
		StringBuffer buffer = new StringBuffer();
		buffer.append(textPart);
		buffer.append(" ").append(urlPart);
		return buffer.toString();
	}

	@Override
	public void setTags(String[] tags) {
	}
	
	public void setComposeBody(String text) {
		setText(text);
	}

	@Override
	public void setText(String text) {
		this.textPart = text;
	}

	@Override
	public void setUrl(String url) {
		this.urlPart = url;
	}

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	public Long getChannelId() {
		return channelId;
	}

	public String getChannelIdAsString() {
		return channelIdAsString;
	}

	public void setChannelIdAsString(String channelIdAsString) {
		this.channelIdAsString = channelIdAsString;
	}

	@Override
	public void setInReplyTo(Serializable inReplyToId) {
		// TODO Auto-generated method stub
		
	}

}
