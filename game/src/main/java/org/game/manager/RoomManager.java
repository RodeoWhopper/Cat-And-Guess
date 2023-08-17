package org.game.manager;

import org.game.entities.game.session.Room;
import org.game.entities.game.content.Question;
import org.game.entities.game.session.User;

import java.util.Map;
import java.util.Set;

public class RoomManager {
    public Room createRoom(Set<User> users, User admin, String ID, int round, int maximizer, Map<Question, User> questions){
        return new Room(users, admin, ID, round, maximizer, questions);
    }
}
