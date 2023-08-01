package org.game.server.application;

import com.sun.net.httpserver.HttpServer;
import org.game.entities.game.session.Room;
import org.game.entities.game.session.User;

import java.net.InetSocketAddress;
import java.util.ArrayList;

public class MainApplication {
    public static ArrayList<User> users;
    public static ArrayList<Room> rooms;
    public static void main(String[] args) throws Exception {
        final int port = 8080;
        HttpServer GameServer = HttpServer.create(new InetSocketAddress(port),0);





    }
}
