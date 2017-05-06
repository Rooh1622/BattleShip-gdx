package ru.rooh.bsgdx;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.json.simple.JSONObject;
import ru.rooh.bsgdx.objects.Ship;
import ru.rooh.bsgdx.utils.AssetLoader;
import ru.rooh.bsgdx.utils.Server;

import java.util.ArrayList;

public class Main extends Game {
    public static float scaleX, scaleY;
    public static float midPointY, midPointX, gameWidth, gameHeight;
    public static Server server;
    public static int server_status = 0; // 0 - not connected; 1 - connecting; 2 - connected;
    public static int game_status = 0; // 0 - not connected; 1 - queued; 2 - playing; 3 - interrupted;
    public static int turn = 0; // 0 - not playing; 1 - red; 2 - blue;
    public static JSONObject json;
    public static ArrayList<Ship> Ships = new ArrayList<Ship>();
    public static int myId = -1;
    public static int enId = -1;
    public static String session = "";
    public static Game game;
    SpriteBatch batch;
	Texture img;


    public static void changeScreen(String n) {
        game.getScreen().dispose();

        scaleX = Gdx.graphics.getWidth() / 144f;
        scaleY = Gdx.graphics.getHeight() / 256f;
        game.setScreen(new mScreen(n));
    }

    public static String getScreenState() {
        mScreen s = (mScreen) game.getScreen();

        //Gdx.app.log("Name", s.getRendererName());
        return s.getRendererName();
    }

    public static void connectionCallback() {

        json = new JSONObject();
        json.put("type", "connection");
        Main.server.send(Main.json.toJSONString());
        reset();
    }

    public static void reset() {
        turn = 0; // 0 - not playing; 1 - red; 2 - blue;
        Ships = new ArrayList<Ship>();
        myId = -1;
        enId = -1;
        session = "";
    }

    public static void dropGame() {
        if (game_status != 2) return;
        turn = 0; // 0 - not playing; 1 - red; 2 - blue;
        game_status = 3;
        Ships = new ArrayList<Ship>();
        json = new JSONObject();
        json.put("type", "endGame");
        json.put("drop", "clear");
        json.put("ses_id", session);
        json.put("myId", myId);
        Main.server.send(Main.json.toJSONString());
    }

	@Override
	public void create () {
		Gdx.app.log("Main", "created");
        try {
            server = new Server();

            //server.connect(); // TODO DEBUG
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Gdx.app.log("Main", server.getReadyState().toString());

        AssetLoader.load();
        scaleX = Gdx.graphics.getWidth() / 144f;
        scaleY = Gdx.graphics.getHeight() / 256f;
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        this.gameWidth = 144;
        this.gameHeight = screenHeight / (screenWidth / gameWidth);

        this.midPointY = (int) (gameHeight / 2);
        this.midPointX = (int) (gameWidth / 2);
        game = this;
		setScreen(new mScreen("menu"));

		//Gdx.app.log("Main", scale + "");
	}

    @Override
	public void dispose() {
		super.dispose();
        AssetLoader.dispose();
    }
}
