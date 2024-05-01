package com.SE2024.SocialBookStore;

import com.SE2024.SocialBookStore.model.*;
import com.SE2024.SocialBookStore.dao.*;
import com.SE2024.SocialBookStore.service.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.aspectj.lang.annotation.Before;
import org.junit.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class ProfilesTests {

	User user1 = new User();
	User admin = new User();
	UserProfile userProfile = new UserProfile();
	BookCategory category = new BookCategory("Fiction");

	@Mock
	private UserDAO userDAO;

	@Mock
	private UserProfileDAO userProfileDAO;

	@Mock
	private BookAuthorDAO bookAuthorDAO;

	@Mock
	private BookCategoryDAO bookCategoryDAO;

	@InjectMocks
	private UserProfileServiceImpl userProfileService;
	@InjectMocks
	private UserServiceImpl userService;

	@Mock
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@BeforeEach
	public void setup() {
		// MockitoAnnotations.initMocks(this);
		user1.setUsername("alice");
		user1.setPassword("password123");
		user1.setRole(Role.USER);
		admin.setUsername("admin");
		admin.setPassword("admin123");

	}

	@Test
	public void testSaveAdmin() {
		when(bCryptPasswordEncoder.encode(admin.getPassword())).thenReturn("admin123");

		userService.saveAdmin(admin);

		verify(bCryptPasswordEncoder).encode(admin.getPassword());
		verify(userDAO).save(admin);

		assertTrue(admin.getRole() == Role.ADMIN);
	}

	@Test
	void testIsUserPresent() {
		when(bCryptPasswordEncoder.encode(user1.getPassword())).thenReturn("password123");

		userService.saveUser(user1);

		verify(bCryptPasswordEncoder).encode(user1.getPassword());
		verify(userDAO).save(user1);

		assertTrue(user1.getRole() == Role.USER);

	}

	@Test
	public void testLoadUserByUsernameUserFound() {
		User user = new User();
		user.setUsername("user1");

		when(userDAO.findByUsername("user1")).thenReturn(Optional.of(user));

		assertTrue(userService.loadUserByUsername("user1") == user);
	}

	@Test
	public void testRetrieveUserProfile() {

		userProfile.setUsername("user1");

		when(userProfileDAO.findByUsername("user1")).thenReturn(userProfile);

		UserProfile result = userProfileService.retrieveUserProfile("user1");

		assertEquals(userProfile, result);
	}

}
