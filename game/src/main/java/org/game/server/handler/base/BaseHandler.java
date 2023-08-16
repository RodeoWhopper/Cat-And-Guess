package org.game.server.handler.base;

import com.sun.net.httpserver.HttpExchange;
import org.game.application.MainApplication;
import org.game.entities.game.session.User;
import org.game.manager.RoomManager;
import org.game.manager.SessionTokenManager;
import org.game.manager.UserManager;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class BaseHandler {
    private final UserManager userManager;
    private final RoomManager roomManager;
    private final SessionTokenManager sessionTokenManager;

    public BaseHandler(UserManager userManager, RoomManager roomManager, SessionTokenManager sessionTokenManager) {
        this.userManager = userManager;
        this.roomManager = roomManager;
        this.sessionTokenManager = sessionTokenManager;
    }

    public void returnPage(Path path, HttpExchange exchange) throws Exception{
        String file = new String(Files.readAllBytes(path));
        exchange.sendResponseHeaders(200, file.length());
        OutputStream outputStream = exchange.getResponseBody();
        outputStream.write(file.getBytes(StandardCharsets.UTF_8));
        outputStream.close();
    }
    public String[] splitBodyMessage(InputStream inputStream) throws IOException {
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
    public boolean checkIfNameExists(String username){
        return MainApplication.users.stream().anyMatch(user -> user.getNickname().equals(username));
    }
}
