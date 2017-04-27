package ru.rooh.bsgdx.utils;

import com.badlogic.gdx.Gdx;
import org.java_websocket.handshake.ServerHandshake;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import ru.rooh.bsgdx.Main;
import ru.rooh.bsgdx.objects.Dot;
import ru.rooh.bsgdx.objects.Map;

import java.net.URI;
import java.nio.channels.NotYetConnectedException;
import java.util.Iterator;

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
        JSONParser parser = new JSONParser();

        try {
            JSONObject jsonObject = (JSONObject) parser.parse(s);
            if (jsonObject.get("type").equals("connection")) {
                System.out.println("> Connected " + jsonObject.get("id"));
                Main.myId = ((Long) jsonObject.get("id")).intValue();
            } else if (jsonObject.get("type").equals("newGame")) {
                System.out.println("> New Game " + jsonObject.get("msg"));

                Main.myId = ((Long) jsonObject.get("id")).intValue();
                Main.enId = ((Long) jsonObject.get("e_id")).intValue();
            } else if (jsonObject.get("type").equals("turn")) {

                System.out.println("> turn " + jsonObject.get("result"));
                if (jsonObject.get("result").equals("hit")) {
                    int tile = ((Long) jsonObject.get("tile")).intValue();
                    System.out.println("> turn" + jsonObject.get("result") + " tile " + tile);
                    if (tile != -1) Map.show.add(new Dot(tile, true, 1));
                } else if (jsonObject.get("result").equals("sink")) {
                    int tile = ((Long) jsonObject.get("hTile")).intValue();
                    System.out.println("> turn" + jsonObject.get("result") + " tile " + tile);
                    //if (tile != -1)
                    Map.show.add(new Dot(tile, true, 1));
                    JSONArray ja = (JSONArray) parser.parse(jsonObject.get("tile").toString());

                    //ArrayList<Integer> tile = new ArrayList<Integer>();
                    Iterator<Long> iterator = ja.iterator();
                    while (iterator.hasNext()) {
                        int cur_tile = iterator.next().intValue();
                        System.out.println("> " + cur_tile);
                        Map.showConturOndestroy((int) cur_tile / 10, cur_tile % 10, 3, cur_tile);


                    }

                    System.out.println("> turn" + jsonObject.get("result") + " tile multiple");
                    //if (tile != -1)

                } else if (jsonObject.get("result").equals("miss")) {
                    int tile = ((Long) jsonObject.get("tile")).intValue();
                    System.out.println("> turn" + jsonObject.get("result") + " tile " + tile);
                    //if (tile != -1)
                    Map.show.add(new Dot(tile, true, 3));
                }
            } else if (jsonObject.get("type").equals("turn_from_e")) {
                System.out.println("> turn_from_e " + jsonObject.get("result"));
                if (jsonObject.get("result").equals("hit") || jsonObject.get("result").equals("sink")) {
                    int tile = ((Long) jsonObject.get("tile")).intValue();
                    System.out.println("> turn_from_e" + jsonObject.get("result") + " tile " + tile);
                    //if (tile != -1)
                    Map.show.add(new Dot(tile, true, 2));
                } else if (jsonObject.get("result").equals("miss")) {
                    int tile = ((Long) jsonObject.get("tile")).intValue();
                    System.out.println("> turn_from_e" + jsonObject.get("result") + " tile " + tile);
                    //if (tile != -1)
                    Map.show.add(new Dot(tile, true, 4));
                }
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
