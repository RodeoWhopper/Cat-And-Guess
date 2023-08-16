package org.game.manager;

import org.game.entities.session.SessionToken;

import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.util.Random;

public class SessionTokenManager {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    public SessionToken generateToken(){
        String random = generateRandomString(20);
        SessionToken token = new SessionToken(random, LocalDateTime.now().plusHours(1));
        return token;
    }

    public static String generateRandomString(int length) {
        Random random = new Random();
        StringBuilder result = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            result.append(randomChar);
        }

        return result.toString();
    }
}
