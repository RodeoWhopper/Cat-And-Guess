package org.game.application;

import com.sun.net.httpserver.HttpServer;
import org.game.entities.game.session.Room;
import org.game.entities.game.session.User;
import org.game.entities.session.SessionToken;
import org.game.manager.RoomManager;
import org.game.manager.SessionTokenManager;
import org.game.manager.UserManager;
import org.game.server.handler.LoginHandler;
import org.game.server.handler.RootHandler;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Map;

public class MainApplication {
    public static ArrayList<User> users;
    public static Map<User, SessionToken> tokenList;
    public static ArrayList<Room> rooms;

    public static void main(String[] args) throws Exception {
        final int port = 8080;
        HttpServer server = HttpServer.create(new InetSocketAddress(port),0);
        System.out.println("Server started on port " + port);
        server.createContext("/", new RootHandler(new UserManager(),new RoomManager(),new SessionTokenManager()));
        server.createContext("/login",new LoginHandler(new UserManager(),new RoomManager(),new SessionTokenManager()));
        server.start();
    }
}
