package com.sait.user;

import java.util.UUID;

public class UserService {
    private final UserDao userDao = new UserDao();

    public User [] getAllUsers() {
        return userDao.getUsers();
    }
    public User findUserById(UUID userId) {
        return userDao.findUserById(userId);
    }
}
