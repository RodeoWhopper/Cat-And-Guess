package org.game.manager;

import org.game.entities.game.Room;
import org.game.entities.game.Question;
import org.game.entities.game.User;

import java.util.Map;
import java.util.Set;

public class RoomManager {
    public Room createRoom(Set<User> users, User admin, String ID, int round, int maximizer, Map<Question, User> questions){
        return new Room(users, admin, ID, round, maximizer, questions);
    }
}
