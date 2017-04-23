package ru.rooh.bsgdx.basics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import ru.rooh.bsgdx.ui.StatusBar;

/**
 * Created by rooh on 4/18/17.
 */
public class Renderer {

    protected World myWorld;
    protected OrthographicCamera cam;
    protected ShapeRenderer shapeRenderer;

    protected SpriteBatch batcher;

    protected StatusBar statusBar;

    protected int midPointY;
    protected int midPointX;
    protected int gameHeight;

    public Renderer(World world) {
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float gameWidth = 136;
        float gameHeight = screenHeight / (screenWidth / gameWidth);

        this.midPointY = (int) (gameHeight / 2);
        this.midPointX = (int) (gameWidth / 2);
        myWorld = world;

        this.gameHeight = (int)gameHeight;
        this.midPointY = midPointY;

        cam = new OrthographicCamera();
        cam.setToOrtho(true, 144, 256);

        batcher = new SpriteBatch();
        // привяжите batcher к камере
        batcher.setProjectionMatrix(cam.combined);

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);

        statusBar = new StatusBar();
    }

    public void render(float runTime) {

    }

    public StatusBar getStatusBar() {
        return statusBar;
    }
}