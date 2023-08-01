package org.game.server.handler;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.game.manager.UserManager;
import org.game.entities.game.session.Room;
import org.game.entities.game.session.User;
import org.game.manager.RoomManager;
import org.game.server.application.MainApplication;


import java.io.IOException;

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


        } else if ("GET".equals(exchange.getRequestMethod())) {

            if(exchange.getRequestMethod().equals("/")){

            }
        }
    }
}
