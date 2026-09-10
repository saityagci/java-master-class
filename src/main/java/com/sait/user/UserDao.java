package com.sait.user;



import java.util.UUID;

public class UserDao {
    private static final com.sait.user.User[] users;

    static {
        users = new User[] {
                new User(UUID.fromString("eef2a287-9ef9-4de7-9c20-f02381c33f57"), "James"),
                new User(UUID.fromString("aef0a54b-365e-4ce4-8f5f-5b1146338245"), "Jamila")
        };
    }
    public User [] getUsers () {
        return users;
    }

    public User findUserById(UUID userId){
        for (User user : users) {
            if (user.getId().equals(userId)){
                return user;
            }
        }
        return null;
    }
}
