package net.mixednutz.api.discord.client;

import java.util.Collections;
import java.util.List;

import org.springframework.social.connect.ConnectionData;
import org.springframework.social.javacord.connect.DiscordConnectionFactory;

import net.mixednutz.api.client.GroupClient;
import net.mixednutz.api.client.MixednutzClient;
import net.mixednutz.api.client.PostClient;
import net.mixednutz.api.client.RoleClient;
import net.mixednutz.api.client.TimelineClient;
import net.mixednutz.api.client.UserClient;
import net.mixednutz.api.discord.model.MessageForm;
import net.mixednutz.api.model.IExternalRole;

public class DiscordAdapter extends BaseDiscordAdapter implements MixednutzClient {
	
	private Long defaultChannelId;
	
	private MessageAdapter messageAdapter;
	private RoleClient roleClient = new RoleClient() {
		@Override
		public boolean hasRoles() {
			return false;
		}
		@Override
		public List<? extends IExternalRole> getAvailableRoles() {
			return null;
		}
		@Override
		public List<? extends IExternalRole> getRolesAssigned() {
			return Collections.emptyList();
		}};

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
	
	@Override
	public RoleClient getRoleClient() {
		return roleClient;
	}
	
	private void initSubApis() {
		messageAdapter = new MessageAdapter(connectionFactory, connectionData, 
				defaultChannelId);
	}

}
