package org.springframework.social.javacord.connect;

import org.javacord.api.DiscordApi;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.support.AbstractConnection;

public class DiscordConnectionFactory extends ConnectionFactory<DiscordApi> {

	public DiscordConnectionFactory(String token) {
		super("discord", new DiscordServiceProvider(token), new DiscordApiAdapter());
	}

	public Connection<DiscordApi> createConnection() {
		return new DiscordConnection(getProviderId(), null,
				(DiscordServiceProvider)getServiceProvider(), 
				this.getApiAdapter());
	}
	
	@Override
	public Connection<DiscordApi> createConnection(ConnectionData data) {
		return new DiscordConnection(data, 
				(DiscordServiceProvider)getServiceProvider(), 
				this.getApiAdapter());
	}
	
	static class DiscordConnection extends AbstractConnection<DiscordApi> {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private transient final DiscordServiceProvider serviceProvider;
		private transient DiscordApi api;
		
		public DiscordConnection(String providerId, String providerUserId,
				DiscordServiceProvider serviceProvider, ApiAdapter<DiscordApi> apiAdapter) {
			super(apiAdapter);
			this.serviceProvider = serviceProvider;
			initApi();
			initKey(providerId, providerUserId);
		}

		
		public DiscordConnection(ConnectionData data, 
				DiscordServiceProvider serviceProvider, ApiAdapter<DiscordApi> apiAdapter) {
			super(data, apiAdapter);
			this.serviceProvider = serviceProvider;
			initApi();
		}

		@Override
		public DiscordApi getApi() {
			return api;
		}

		@Override
		public ConnectionData createData() {
			synchronized (getMonitor()) {
				return new ConnectionData(getKey().getProviderId(), 
						getKey().getProviderUserId(), 
						getDisplayName(), 
						getProfileUrl(), 
						getImageUrl(), 
						null, null, null, null);
			}
		}
		
		private void initApi() {
			api = serviceProvider.getApi();
		}
		
	}

}
