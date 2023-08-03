package org.game.server.handler;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.game.manager.UserManager;
import org.game.entities.game.session.Room;
import org.game.entities.game.session.User;
import org.game.manager.RoomManager;
import org.game.application.MainApplication;


import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GameHandler implements HttpHandler {
    private final UserManager userManager;
    private final RoomManager roomManager;

    public GameHandler(UserManager userManager, RoomManager roomManager) {
        this.userManager = userManager;
        this.roomManager = roomManager;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if("POST".equals(exchange.getRequestMethod())){
            String message = new String(exchange.getRequestBody().readAllBytes());
            String[] codes = message.split(":");
            String hostIP = exchange.getRemoteAddress().getAddress().getHostAddress();

            //message="CREATE_USER:'CUSTOM_NICKNAME'"
            if(codes[0] == "CREATE_USER"){
                User user = this.userManager.createUser(hostIP,codes[1]);
                MainApplication.users.add(user);
            }
            //message="CREATE_ROOM:'ROOM_ID':MAXIMIZER"
            else if (codes[0] == "CREATE_ROOM") {
                User admin = null;
                for(User user : MainApplication.users){
                    if(user.getIP().equals(hostIP)){
                        admin = user;
                    }
                }
                Room room = this.roomManager.createRoom(Sets.newHashSet(admin),
                        admin,
                        codes[1],
                        0,
                        Integer.parseInt(codes[2]),
                        Maps.newHashMap()
                );
                MainApplication.rooms.add(room);
            }
            //message="JOIN_ROOM:'ROOM_ID'"



        } else if ("GET".equals(exchange.getRequestMethod())) {
            String requestURI = exchange.getRequestURI().toString();
            if(requestURI.equals("/")){
                Path file = Paths.get("./src/main/resources/index.html");
                try {
                    GameHandler.returnPage(file,exchange);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            if(requestURI.equals("/login")){
                Path file = Paths.get("./src/main/resources/login/login.html");
                try {
                    GameHandler.returnPage(file,exchange);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void returnPage(Path path, HttpExchange exchange) throws Exception{
        String file = new String(Files.readAllBytes(path));
        exchange.sendResponseHeaders(200, file.length());
        OutputStream outputStream = exchange.getResponseBody();
        outputStream.write(file.getBytes());
        outputStream.close();
    }
}
