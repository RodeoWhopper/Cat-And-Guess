package org.game.entities.game.session;

import org.game.entities.game.content.Question;

import java.util.Map;
import java.util.Set;

public class Room {
    private Set<User> users;
    private User admin;
    private String ID;
    private int round;
    private int maximizer;
    private Map<Question, User> questions;

    public Room(Set<User> users, User admin, String ID, int round, int maximizer, Map<Question, User> questions) {
        this.users = users;
        this.admin = admin;
        this.ID = ID;
        this.round = round;
        this.maximizer = maximizer;
        this.questions = questions;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public int getMaximizer() {
        return maximizer;
    }

    public void setMaximizer(int maximizer) {
        this.maximizer = maximizer;
    }

    public Map<Question, User> getQuestions() {
        return questions;
    }

    public void setQuestions(Map<Question, User> questions) {
        this.questions = questions;
    }
}
