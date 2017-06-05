package ru.rooh.bsgdx.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import ru.rooh.bsgdx.Main;
import ru.rooh.bsgdx.basics.Renderer;
import ru.rooh.bsgdx.basics.World;
import ru.rooh.bsgdx.objects.Background;
import ru.rooh.bsgdx.objects.ScrollHandler;
import ru.rooh.bsgdx.ui.SimpleButton;
import ru.rooh.bsgdx.ui.StatusBar;
import ru.rooh.bsgdx.utils.AssetLoader;

/**
 * Created by rooh on 4/18/17.
 */
public class MainMenuRenderer extends Renderer {
    public SimpleButton play, setup, random;
    private ScrollHandler scroller;
    private Background frontGrass, backGrass;
    private TextureRegion background;

    public MainMenuRenderer(World world) {
        super(world);
        play = new SimpleButton(midPointX, midPointY * 0.5f, 29, 16, AssetLoader.playButtonUp, AssetLoader.playButtonDown);
        setup = new SimpleButton(midPointX, midPointY * 0.5f + 20, 36, 16, AssetLoader.setupBtn, AssetLoader.setupBtn);
        random = new SimpleButton(midPointX, midPointY * 0.5f + 40, 46, 16, AssetLoader.randomBtnBlue, AssetLoader.randomBtnRed);
        initGameObjects();
        initAssets();

    }
    @Override
    public void render(float runTime) {


        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Стартуем ShapeRenderer
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        // Отрисуем Background цвет
        shapeRenderer.setColor(135 / 255.0f, 184 / 255.0f, 223 / 255.0f, 1);
        shapeRenderer.rect(0, 0, Main.gameWidth + 1, midPointY + 66);

        // Заканчиваем ShapeRenderer
        shapeRenderer.end();

        // Стартуем SpriteBatch
        batcher.begin();
        batcher.disableBlending();
        //batcher.draw(AssetLoader.sea, 0,midPointY/2 , 450* midPointY/243, midPointY);
        drawGrass();
        batcher.enableBlending();
        play.draw(batcher);
        setup.draw(batcher);
        random.draw(batcher);
        statusBar.draw(batcher, runTime);


       // AssetLoader.shadow.draw(batcher, "hello world", 0, 0);
        //AssetLoader.font.draw(batcher, "hello world", 0, 0);
        // Заканчиваем SpriteBatch
        batcher.end();

    }

    private void initGameObjects() {
        scroller = ((MenuWorld) myWorld).getScroller();
        frontGrass = scroller.getFrontGrass();
        backGrass = scroller.getBackGrass();
    }

    private void initAssets() {
        background = AssetLoader.sea;
    }

    private void drawGrass() {
        // Отрисовываем траву
        batcher.draw(background, frontGrass.getX(), frontGrass.getY(),
                frontGrass.getWidth(), frontGrass.getHeight());
        batcher.draw(background, backGrass.getX(), backGrass.getY(),
                backGrass.getWidth(), backGrass.getHeight());
    }


    public SimpleButton getPlay() {
        return play;
    }

    public StatusBar getStatusBar() {
        return statusBar;
    }
}
