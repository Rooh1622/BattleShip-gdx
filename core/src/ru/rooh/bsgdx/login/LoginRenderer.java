package ru.rooh.bsgdx.login;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import ru.rooh.bsgdx.basics.Renderer;
import ru.rooh.bsgdx.basics.World;
import ru.rooh.bsgdx.objects.Background;
import ru.rooh.bsgdx.objects.ScrollHandler;
import ru.rooh.bsgdx.ui.InputBox;
import ru.rooh.bsgdx.ui.SimpleButton;
import ru.rooh.bsgdx.ui.StatusBar;
import ru.rooh.bsgdx.utils.AssetLoader;

/**
 * Created by rooh on 4/18/17.
 */
public class LoginRenderer extends Renderer {
    public InputBox lb;
    public InputBox pas;
    public SimpleButton go;
    private ScrollHandler scroller;
    private Background frontGrass, backGrass;
    private TextureRegion background;

    public LoginRenderer(World world) {
        super(world);
        lb = new InputBox(72, 35 + 12, 144, 24, AssetLoader.inputBox, AssetLoader.inputBox, false);
        pas = new InputBox(72, 70 + 12, 144, 24, AssetLoader.inputBox, AssetLoader.inputBox, true);
        go = new SimpleButton(midPointX, midPointY - 12, 29, 16, AssetLoader.goBtn, AssetLoader.goBtn);
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
        //shapeRenderer.setColor(255 / 255.0f, 185 / 255.0f, 85 / 255.0f, 1);
        //shapeRenderer.rect(0, 0, Main.gameWidth, midPointY);

        // Заканчиваем ShapeRenderer
        shapeRenderer.end();

        // Стартуем SpriteBatch
        batcher.begin();
        batcher.draw(AssetLoader.inputBd, 0, -8);
        // Отменим прозрачность
        // Это хорошо для производительности, когда отрисовываем картинки без прозрачности
        batcher.disableBlending();
        //batcher.draw(AssetLoader.sea, 0,midPointY/2 , 450* midPointY/243, midPointY);
        drawGrass();
        // Птичке нужна прозрачность, поэтому включаем ее
        batcher.enableBlending();
        lb.draw(batcher);
        pas.draw(batcher);
        go.draw(batcher);
        statusBar.draw(batcher, runTime);


        AssetLoader.shadow.draw(batcher, "Login", 0, 15);
        AssetLoader.font.draw(batcher, "Login", 0, 15);
        // Заканчиваем SpriteBatch
        batcher.end();

    }

    private void initGameObjects() {
        scroller = ((LoginWorld) myWorld).getScroller();
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


    public InputBox getPlay() {
        return lb;
    }

    public StatusBar getStatusBar() {
        return statusBar;
    }
}
