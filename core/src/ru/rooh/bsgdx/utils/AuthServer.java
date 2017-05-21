package ru.rooh.bsgdx.utils;

import com.badlogic.gdx.Gdx;
import org.java_websocket.handshake.ServerHandshake;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import ru.rooh.bsgdx.Main;

import java.net.URI;
import java.nio.channels.NotYetConnectedException;

/**
 * Created by rooh on 4/23/17.
 */
public class AuthServer extends org.java_websocket.client.WebSocketClient {
    public static int delay = 1;
    private String token = "";

    public AuthServer(String token) throws Exception {
        super(new URI("ws://" + Main.ip + ":8082/?access_token=" + token));
        // this.connect();

    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {

        Gdx.app.log("AuthServer", "opened: " + serverHandshake.toString());
        //Main.authCallback();

        Main.auth_server_status = 2;
        Main.game_status = 0;

    }

    @Override
    public void onMessage(String s) {
        Gdx.app.log("AuthServer", s);
        JSONParser parser = new JSONParser();

        try {
            JSONObject jsonObject = (JSONObject) parser.parse(s);
            if (jsonObject.get("type").equals("loginSuccess")) {
                System.out.println("> AUTHED TOKEN " + jsonObject.get("token"));

                Main.authCallback(jsonObject.get("token") + "");

            }


            /*JSONArray array = (JSONArray) parser.parse(s);
            Iterator<JSONObject> iterator = array.iterator();
            while (iterator.hasNext()) {
                JSONObject jsonShip = iterator.next();
                System.out.println("> " );
                Main.Ships.add(new Ship(((Long) jsonShip.getOrDefault("cHash", Long.valueOf(-1))).intValue(),
                        ((Long) jsonShip.getOrDefault("c2Hash", Long.valueOf(-1))).intValue(),
                        ((Long) jsonShip.getOrDefault("c3Hash", Long.valueOf(-1))).intValue(),
                        ((Long) jsonShip.getOrDefault("c4Hash", Long.valueOf(-1))).intValue()));
            }*/
        } catch (ParseException ex) {

        }
    }

    @Override
    public void onClose(int i, String s, boolean b) {

        Gdx.app.log("AuthServer", "closed: " + s);

        Main.server_status = 0;
        Main.game_status = 0;
    }

    @Override
    public void onError(Exception e) {

        Gdx.app.log("AuthServer", "error");
        e.printStackTrace();
        Main.server_status = 1;
        Main.game_status = 3;

        delay += 3;
        try {
            System.out.println(delay);
            Thread.sleep(1000 * delay);

            Main.auth_server = new AuthServer(Main.getAuth_token());
            Main.auth_server.connect();
        } catch (Exception e1) {
            //e1.printStackTrace();
            Gdx.app.log("AuthServer", "Connection error");
        }
    }

    public void reconnect() {
        Main.server_status = 1;

        delay = 1;
        try {
            System.out.println(delay);

            Main.auth_server = new AuthServer(Main.getAuth_token());
            Main.auth_server.connect();
        } catch (Exception e1) {
            //me1.printStackTrace();
            Gdx.app.log("AuthServer", "Reconnection error");
        }

    }


    @Override
    public void send(String s) throws NotYetConnectedException {
        if (this.getReadyState().toString().equals("NOT_YET_CONNECTED")) Gdx.app.log("AuthServer", "Not Connected");
        if (this.getReadyState().toString().equals("OPEN")) super.send(s);

    }

}
