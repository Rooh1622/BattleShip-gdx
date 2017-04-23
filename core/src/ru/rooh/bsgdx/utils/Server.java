package ru.rooh.bsgdx.utils;

import com.badlogic.gdx.Gdx;
import org.java_websocket.handshake.ServerHandshake;
import ru.rooh.bsgdx.Main;

import java.net.URI;
import java.nio.channels.NotYetConnectedException;

/**
 * Created by rooh on 4/23/17.
 */
public class Server extends org.java_websocket.client.WebSocketClient {
    public Server() throws Exception {
        super(new URI("ws://127.0.0.1:8081"));
        // this.connect();

    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {

        Gdx.app.log("Server", "opened: " + serverHandshake.toString());
        Main.connectionCallback();

    }

    @Override
    public void onMessage(String s) {
        Gdx.app.log("Server", s);
    }

    @Override
    public void onClose(int i, String s, boolean b) {

        Gdx.app.log("Server", "closed: " + s);
    }

    @Override
    public void onError(Exception e) {

        Gdx.app.log("Server", "error");
        e.printStackTrace();
    }

    @Override
    public void send(String s) throws NotYetConnectedException {
        if (this.getReadyState().toString().equals("NOT_YET_CONNECTED")) Gdx.app.log("Server", "Not Connected");
        if (this.getReadyState().toString().equals("OPEN")) super.send(s);

    }

}
