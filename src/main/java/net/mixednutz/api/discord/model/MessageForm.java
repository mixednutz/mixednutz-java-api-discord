package net.mixednutz.api.discord.model;

import net.mixednutz.api.model.IPost;

public class MessageForm implements IPost {
	
	Long channelId;
	
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

}
