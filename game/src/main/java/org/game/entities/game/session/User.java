package org.game.entities.game.session;

import org.game.common.util.enums.UserRole;
import org.game.entities.game.content.session.Question;
import org.game.entities.session.SessionToken;

public class User {
    private String IP;
    private String nickname;
    private SessionToken sessionToken;
    private Room room;
    private UserRole userRole;
    private Question question;

    public User(String IP){
        this.IP = IP;
    }

    public User(String IP, String nickname, SessionToken sessionToken) {
        this.IP = IP;
        this.nickname = nickname;
        this.sessionToken = sessionToken;
    }

    public User(String IP, String nickname, Room room, UserRole userRole) {
        this.IP = IP;
        this.nickname = nickname;
        this.room = room;
        this.userRole = userRole;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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
}
