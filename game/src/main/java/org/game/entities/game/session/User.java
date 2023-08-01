package org.game.entities.game.session;

import org.game.common.util.enums.UserRole;
import org.game.entities.game.content.session.Question;

public class User {
    private String IP;
    private String nickname;
    private Room room;
    private UserRole userRole;
    private int point;
    private Question question;

    public User(){

    }

    public User(String IP, String nickname) {
        this.IP = IP;
        this.nickname = nickname;
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

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
