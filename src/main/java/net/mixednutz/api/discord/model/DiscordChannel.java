package net.mixednutz.api.discord.model;

import org.javacord.api.entity.channel.ServerChannel;

public class DiscordChannel {
	
	String id;
	String name;
	
	public DiscordChannel(ServerChannel channel) {
		super();
		this.id = String.valueOf(channel.getId());
		this.name = channel.getName();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
