package net.mixednutz.api.discord;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import net.mixednutz.api.core.model.NetworkInfoSmall;

@JsonIgnoreProperties(ignoreUnknown=true)
public class DiscordFeedType extends NetworkInfoSmall {
	
	private static final String DISPLAY_NAME = "Discord";
	private static final String HOST_NAME = "discord.com";
	private static final String ID = "discord";
	private static final String ICON_NAME = "discord";
	
	private static DiscordFeedType instance;

	public DiscordFeedType() {
		super();
		this.setDisplayName(DISPLAY_NAME);
		this.setHostName(HOST_NAME);
		this.setId(ID);
		this.setFontAwesomeIconName(ICON_NAME);
	}

	public static DiscordFeedType getInstance() {
		if (instance==null) {
			instance = new DiscordFeedType();
		}
		return instance;
	}

	@Override
	public String[] compatibleMimeTypes() {
		return new String[] {
				"text/plain", //text and links
				"image/*" //images
				};
	}

}
