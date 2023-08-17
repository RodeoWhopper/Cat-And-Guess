package org.game.server.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.game.common.util.exceptions.AlreadyExistsException;
import org.game.entities.game.User;
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
        OutputStream os = exchange.getResponseBody();

        //Getting the method
        String requestMethod = exchange.getRequestMethod();
        //Getting the message and requestIP
        String clientIP = exchange.getRemoteAddress().getAddress().toString();
        String[] messageList = splitBodyMessage(exchange.getRequestBody());
        if(requestMethod.equals("POST")){
            if(messageList[0].equals("LOGIN")){
                String username = messageList[1];
                try{
                    User user = login(exchange,userManager,username);
                    System.out.println("User " + username + " joined to server");
                    userManager.addUserToList(user); //Added to list of user
                }catch (Exception e){
                    exchange.sendResponseHeaders(409,e.getMessage().length());
                    os.write(e.getMessage().getBytes(StandardCharsets.UTF_8));
                }
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
    private User login(HttpExchange exchange, UserManager userManager, String username) throws AlreadyExistsException, IOException {
        String IP = exchange.getRemoteAddress().getAddress().toString();
        String successfulMessage = "Login Successful!";
        OutputStream os = exchange.getResponseBody();
        if(checkIfNameExists(username)){
            throw new AlreadyExistsException();
        }else{
            userManager.checkExpirations();
            SessionToken token = sessionTokenManager.generateToken();
            //Response
            exchange.getResponseHeaders().add("Authorization",token.getTokenContent());
            exchange.sendResponseHeaders(200,successfulMessage.length());
            os.write(successfulMessage.getBytes(StandardCharsets.UTF_8));
            return userManager.createUser(IP,username,token);
        }
    }
}
