package org.game.entities.game;

import org.game.common.util.enums.UserRole;
import org.game.entities.session.SessionToken;

public class User {
    private String IP;
    private String username;
    private SessionToken sessionToken;
    private Room room;
    private UserRole userRole;
    private Question question;

    public User(String IP){
        this.IP = IP;
    }

    public User(String IP, String username, SessionToken sessionToken) {
        this.IP = IP;
        this.username = username;
        this.sessionToken = sessionToken;
    }

    public User(String IP, String username, Room room, UserRole userRole) {
        this.IP = IP;
        this.username = username;
        this.room = room;
        this.userRole = userRole;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public SessionToken getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(SessionToken sessionToken) {
        this.sessionToken = sessionToken;
    }

}
