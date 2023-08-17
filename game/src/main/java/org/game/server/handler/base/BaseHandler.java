package org.game.server.handler.base;

import com.sun.net.httpserver.HttpExchange;
import org.game.entities.game.User;
import org.game.manager.RoomManager;
import org.game.manager.SessionTokenManager;
import org.game.manager.UserManager;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;

import static org.game.application.MainApplication.users;

public class BaseHandler {
    private final UserManager userManager;
    private final RoomManager roomManager;
    private final SessionTokenManager sessionTokenManager;

    public BaseHandler(UserManager userManager, RoomManager roomManager, SessionTokenManager sessionTokenManager) {
        this.userManager = userManager;
        this.roomManager = roomManager;
        this.sessionTokenManager = sessionTokenManager;
    }

    protected static void returnPage(Path path, HttpExchange exchange) throws Exception{
        String file = new String(Files.readAllBytes(path));
        exchange.sendResponseHeaders(200, file.length());
        OutputStream outputStream = exchange.getResponseBody();
        outputStream.write(file.getBytes(StandardCharsets.UTF_8));
        outputStream.close();
    }
    protected static String[] splitBodyMessage(InputStream inputStream) throws IOException {
        InputStream requestBody = inputStream;
        StringBuilder content = new StringBuilder();
        int bytesRead;
        byte[] buffer = new byte[1024];

        while ((bytesRead = requestBody.read(buffer)) != -1) {
            content.append(new String(buffer, 0, bytesRead));
        }
        String[] messageList = content.toString().split(":");
        return messageList;
    }
    protected static boolean checkIfNameExists(String username){
        boolean bool = users.isEmpty();
        if(bool){
            return false;
        }else{
            return users.stream().anyMatch(user ->
                    user.getUsername().equals(username) && !isNameExpired(user));
        }
    }
    protected static boolean isNameExpired(User user){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime exp = user.getSessionToken().getExpirationTime();
        if(exp.isBefore(now)){
            return true;
        }else{
            return false;
        }
    }
}
