package org.game.server.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.game.application.MainApplication;
import org.game.entities.game.session.User;
import org.game.entities.session.SessionToken;
import org.game.manager.RoomManager;
import org.game.manager.SessionTokenManager;
import org.game.manager.UserManager;
import org.game.server.handler.base.BaseHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LoginHandler extends BaseHandler implements HttpHandler {
    private final UserManager userManager;
    private final RoomManager roomManager;
    private final SessionTokenManager sessionTokenManager;
    public LoginHandler(UserManager userManager, RoomManager roomManager, SessionTokenManager sessionTokenManager) {
        super(userManager, roomManager, sessionTokenManager);
        this.userManager = userManager;
        this.roomManager = roomManager;
        this.sessionTokenManager = sessionTokenManager;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String successful = "Login Successful!";
        OutputStream os = exchange.getResponseBody();
        //Getting the method
        String requestMethod = exchange.getRequestMethod();
        //Getting the message and requestIP
        String clientIP = exchange.getRemoteAddress().getAddress().toString();
        String[] messageList = splitBodyMessage(exchange.getRequestBody());
        if(requestMethod.equals("POST")){
            if(messageList[0].equals("LOGIN")){
                String username = messageList[1];
                System.out.println("User " + username + " joined to server");
                SessionToken token = sessionTokenManager.generateToken();
                exchange.getResponseHeaders().add("Authorization",token.getTokenContent());
                exchange.sendResponseHeaders(200,successful.length());
                os.write(successful.getBytes(StandardCharsets.UTF_8));
                os.close();
            }

        } else if (requestMethod.equals("GET")) {
            Path file = Paths.get("./src/main/resources/login/login.html");
            try {
                returnPage(file,exchange);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}