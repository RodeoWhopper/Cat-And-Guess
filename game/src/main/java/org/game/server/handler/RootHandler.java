package org.game.server.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.game.manager.SessionTokenManager;
import org.game.manager.UserManager;
import org.game.manager.RoomManager;
import org.game.server.handler.base.BaseHandler;


import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class RootHandler extends BaseHandler implements HttpHandler {

    public RootHandler(UserManager userManager, RoomManager roomManager, SessionTokenManager sessionTokenManager) {
        super(userManager, roomManager, sessionTokenManager);
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        if("POST".equals(exchange.getRequestMethod())){
            //TODO
        } else if ("GET".equals(exchange.getRequestMethod())) {
            String requestURI = exchange.getRequestURI().toString();
            if(requestURI.equals("/")){
                Path file = Paths.get("./src/main/resources/root/index.html");
                try {
                    returnPage(file,exchange);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }


}
