package com.SE2024.SocialBookStore.service.userAuth;

import org.springframework.stereotype.Service;

import com.SE2024.SocialBookStore.model.User;

@Service
public interface UserService {
	public void saveUser(User user);
    public boolean isUserPresent(User user);
    public void saveAdmin(User admin);
}
