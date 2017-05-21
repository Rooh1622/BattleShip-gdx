package ru.rooh.bsgdx.utils;

import com.badlogic.gdx.Gdx;
import org.java_websocket.handshake.ServerHandshake;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import ru.rooh.bsgdx.Main;
import ru.rooh.bsgdx.game.GameWorld;
import ru.rooh.bsgdx.mScreen;
import ru.rooh.bsgdx.objects.Dot;
import ru.rooh.bsgdx.objects.Map;

import java.net.URI;
import java.nio.channels.NotYetConnectedException;
import java.util.Iterator;

/**
 * Created by rooh on 4/23/17.
 */
public class Server extends org.java_websocket.client.WebSocketClient {
    public static int delay = 1;
    private String token = "";

    public Server(String token) throws Exception {
        super(new URI("ws://" + Main.ip + ":8081/?access_token=" + token));
        this.token = token;
        // this.connect();

    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {

        Gdx.app.log("Server", "opened: " + serverHandshake.toString());
        Main.connectionCallback();

        Main.server_status = 2;
        Main.game_status = 0;

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

                Main.game_status = 2;
                Main.myId = ((Long) jsonObject.get("id")).intValue();
                Main.enId = ((Long) jsonObject.get("e_id")).intValue();
                Main.turn = ((Long) jsonObject.get("turn")).intValue();
                Main.session = (String) jsonObject.get("ses_id");
            } else if (jsonObject.get("type").equals("loginSuccess")) {
                System.out.println("> TOKEN" + jsonObject.get("token"));
                Main.requestScreenSwap = "menu";
            } else if (jsonObject.get("type").equals("saveFieldOK")) {
                System.out.println("> FIELD " + jsonObject.get("result"));
                Main.requestScreenSwap = "menu";

            } else if (jsonObject.get("type").equals("queue")) {
                System.out.println("> Queue " + jsonObject.get("msg"));

                int enId = ((Long) jsonObject.get("e_id")).intValue();
                Main.game_status = 1;
                if (enId == -1) return;

                Main.enId = enId;
                JSONObject json = new JSONObject();
                json.put("type", "newGame");
                json.put("myId", Main.myId);
                json.put("enId", Main.enId);
                Main.server.send(json.toJSONString());
            } else if (jsonObject.get("type").equals("message")) {
                System.out.println("> Message " + jsonObject.get("msg"));


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
                    GameWorld gw = ((GameWorld) ((mScreen) Main.game.getScreen()).world);
                    while (iterator.hasNext()) {
                        int cur_tile = iterator.next().intValue();
                        System.out.println("> " + cur_tile);
                        gw.getMap().showConturOndestroy((int) cur_tile / 10, cur_tile % 10, 3, cur_tile);


                    }

                    System.out.println("> turn" + jsonObject.get("result") + " tile multiple");
                    //if (tile != -1)

                } else if (jsonObject.get("result").equals("miss")) {
                    int tile = ((Long) jsonObject.get("tile")).intValue();
                    System.out.println("> turn" + jsonObject.get("result") + " tile " + tile);
                    //if (tile != -1)
                    Map.show.add(new Dot(tile, true, 3));

                    Main.turn = Main.enId;
                }
            } else if (jsonObject.get("type").equals("turn_from_e")) {

                System.out.println("> turn_from_e " + jsonObject.get("result"));
                if (jsonObject.get("result").equals("hit")) {
                    int tile = ((Long) jsonObject.get("tile")).intValue();
                    System.out.println("> turn_from_e" + jsonObject.get("result") + " tile " + tile);
                    if (tile != -1) Map.show_e.add(new Dot(tile, true, 2));
                } else if (jsonObject.get("result").equals("sink")) {
                    int tile = ((Long) jsonObject.get("hTile")).intValue();
                    System.out.println("> turn_from_e" + jsonObject.get("result") + " tile " + tile);
                    //if (tile != -1)
                    Map.show_e.add(new Dot(tile, true, 2));
                    JSONArray ja = (JSONArray) parser.parse(jsonObject.get("tile").toString());

                    //ArrayList<Integer> tile = new ArrayList<Integer>();
                    Iterator<Long> iterator = ja.iterator();

                    GameWorld gw = ((GameWorld) ((mScreen) Main.game.getScreen()).world);
                    while (iterator.hasNext()) {
                        int cur_tile = iterator.next().intValue();
                        System.out.println("> " + cur_tile);
                        gw.getMap().showConturOndestroy((int) cur_tile / 10, cur_tile % 10, 4, cur_tile);


                    }

                    System.out.println("> turn_from_e" + jsonObject.get("result") + " tile multiple");
                    //if (tile != -1)

                } else if (jsonObject.get("result").equals("miss")) {
                    int tile = ((Long) jsonObject.get("tile")).intValue();
                    System.out.println("> turn_from_e" + jsonObject.get("result") + " tile " + tile);
                    //if (tile != -1)
                    Map.show_e.add(new Dot(tile, true, 4));

                    Main.turn = Main.myId;
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

        Main.server_status = 0;
        Main.game_status = 0;
    }

    @Override
    public void onError(Exception e) {

        Gdx.app.log("Server", "error");
        e.printStackTrace();
        Main.server_status = 1;
        Main.game_status = 3;

        delay += 3;
        try {
            System.out.println(delay);
            Thread.sleep(1000 * delay);

            Main.server = new Server(Main.getToken());
            Main.server.connect();
        } catch (Exception e1) {
            //e1.printStackTrace();
            Gdx.app.log("Server", "Connection error");
        }
    }

    public void reconnect() {
        Main.server_status = 1;

        delay = 1;
        try {
            System.out.println(delay);

            Main.server = new Server(Main.getToken());
            Main.server.connect();
        } catch (Exception e1) {
            //me1.printStackTrace();
            Gdx.app.log("Server", "Reconnection error");
        }

    }


    @Override
    public void send(String s) throws NotYetConnectedException {
        if (this.getReadyState().toString().equals("NOT_YET_CONNECTED")) Gdx.app.log("Server", "Not Connected");
        if (this.getReadyState().toString().equals("OPEN")) super.send(s);


    }

    public void sendJson(JSONObject json) {
        json.put("login", Main.getLogin());
        send(json.toJSONString());
    }

    public void sendJson(JSONArray json) {
        JSONObject payload = new JSONObject();
        payload.put("type", "saveField");
        payload.put("login", Main.getLogin());
        payload.put("iss", "java");
        //json.add(payload);
        payload.put("ships", json);
        System.out.println("JSON>>>  " + payload);
        send(payload.toJSONString());
    }

}
