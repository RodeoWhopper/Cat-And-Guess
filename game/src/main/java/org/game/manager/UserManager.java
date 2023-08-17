package org.game.manager;

import org.game.application.MainApplication;
import org.game.common.util.exceptions.UserNotExistsException;
import org.game.entities.game.session.User;
import org.game.entities.session.SessionToken;

import java.time.LocalDateTime;

import static org.game.application.MainApplication.users;

public class UserManager {
    public User createUser(String IP, String nickname, SessionToken token){
        return new User(IP,nickname,token);
    }
    public static void addUserToList(User user){
        users.add(user);
    }
    public void checkExpirations(){
        users.stream().forEach(user -> {
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime expiration = user.getSessionToken().getExpirationTime();
            if(expiration.isBefore(now)){
                deleteUserFromList(user);
            }
        });
    }
    public static void deleteUserFromList(User user) throws UserNotExistsException {
        if(users.contains(user)){
            users.remove(user);
        } else {
            throw new UserNotExistsException();
        }
    }
}
