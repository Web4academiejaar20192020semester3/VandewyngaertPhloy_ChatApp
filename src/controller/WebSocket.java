package controller;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.Session;


@ServerEndpoint("/Blog")
public class WebSocket {
    private static final Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());

    @OnOpen
    public void onOpen(Session session) {//vanaf dat je op een blog bent opent de connection

        System.out.println(session.getId() + " has opened a connection");
        sessions.add(session);
    }

    @OnMessage
    public void onMessage(String message, Session session) {//als er een event is (bericht) word dit naar alle sessions (schermen) gestuurd
        System.out.println("Message from " + session.getId() + ": " + message);
        sendMessageToAll(message);
    }

    @OnClose
    public void onClose(Session session) {//session afsluiten van persoon die venster sluit
        System.out.println("Chat " + session.getId() + " has ended");
        sessions.remove(session);
    }

    private void sendMessageToAll(String message) {//notificatie (bericht) naar alle sessions sturen
        for (Session s : sessions) {
            try {//die s hier heb ik aangepast
                s.getBasicRemote().sendText(message);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
