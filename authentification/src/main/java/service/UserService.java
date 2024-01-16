package service;

import entity.User;

public interface UserService {
	User registerUser(String username, String email, String password, String role);

	User seConnecter(String email, String password);

}
