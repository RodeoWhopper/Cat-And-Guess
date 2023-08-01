package org.game.manager;

import org.game.entities.game.session.User;

public class UserManager {
    public User createUser(String IP, String nickname){
        return new User(IP,nickname);
    }
}
