package org.game.entities.game.content.session;

import org.game.entities.game.session.User;

public class Question {
    private User owner;
    private String content;

    public Question(User owner, String content) {
        this.owner = owner;
        this.content = content;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
