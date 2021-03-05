package org.springframework.social.javacord.connect;

import org.javacord.api.DiscordApi;
import org.javacord.api.entity.user.User;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.UserProfileBuilder;

public class DiscordApiAdapter implements ApiAdapter<DiscordApi> {

	@Override
	public boolean test(DiscordApi api) {
		try {
			api.getUserById(api.getClientId()).get();
			return true;
		} catch (Exception e) {
			return false;
		}	
	}

	@Override
	public void setConnectionValues(DiscordApi api, ConnectionValues values) {
		try {
			User user = api.getUserById(api.getClientId()).get();
			values.setProviderUserId(String.valueOf(api.getClientId()));
			values.setDisplayName(user.getName());
			values.setImageUrl(user.getAvatar().getUrl().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

	@Override
	public UserProfile fetchUserProfile(DiscordApi api) {
		try {
			User user = api.getUserById(api.getClientId()).get();
			return new UserProfileBuilder()
					.setName(user.getName())
					.setUsername(user.getDiscriminatedName()).build();
		} catch (Exception e) {
			return null;
		}	
	}

	@Override
	public void updateStatus(DiscordApi api, String message) {
		// TODO Auto-generated method stub
		
	}

}
