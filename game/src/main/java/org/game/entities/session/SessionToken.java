package org.game.entities.session;

import java.io.Serializable;
import java.time.LocalDateTime;

public class SessionToken implements Serializable {
    private String tokenContent;
    private LocalDateTime expirationTime;

    public SessionToken(String tokenContent, LocalDateTime expirationTime) {
        this.tokenContent = tokenContent;
        this.expirationTime = expirationTime;
    }

    public String getTokenContent() {
        return tokenContent;
    }

    public void setTokenContent(String tokenContent) {
        this.tokenContent = tokenContent;
    }

    public LocalDateTime getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(LocalDateTime expirationTime) {
        this.expirationTime = expirationTime;
    }
}
