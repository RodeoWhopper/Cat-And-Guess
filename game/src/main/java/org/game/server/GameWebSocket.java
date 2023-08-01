package org.game.server;

import org.eclipse.jetty.websocket.api.annotations.*;
import org.eclipse.jetty.websocket.api.Session;
import org.game.application.MainApplication;
import org.game.entities.game.session.User;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.InetSocketAddress;
@WebSocket
public class GameWebSocket {

    public void onConnect(Session session){
        String clientIP = session.getRemoteAddress().getHostName();
        MainApplication.users.add(new User(clientIP));
        //todo logging
    }
}
