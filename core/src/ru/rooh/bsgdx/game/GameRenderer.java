package ru.rooh.bsgdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import ru.rooh.bsgdx.Main;
import ru.rooh.bsgdx.basics.Renderer;
import ru.rooh.bsgdx.basics.World;
import ru.rooh.bsgdx.objects.Background;
import ru.rooh.bsgdx.objects.DecorativeShip;
import ru.rooh.bsgdx.objects.Map;
import ru.rooh.bsgdx.objects.ScrollHandler;
import ru.rooh.bsgdx.utils.AssetLoader;

/**
 * Created by rooh on 4/18/17.
 */
public class GameRenderer extends Renderer {
    private ScrollHandler scroller;
    private Background frontGrass, backGrass;
    private TextureRegion background;

    public GameRenderer(World world) {
        super(world);

        initGameObjects();
        initAssets();
    }
    @Override
    public void render(float runTime) {

        // мы уберем это из цикла далее, для улучшения производительности
        DecorativeShip decorativeShip = ((GameWorld) myWorld).getDecorativeShip();
        Map map = ((GameWorld) myWorld).getMap();

        // Заполним задний фон одним цветом
        // Заполним задний фон одним цветом
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Стартуем ShapeRenderer
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        // Отрисуем Background цвет
        shapeRenderer.setColor(135 / 255.0f, 184 / 255.0f, 223 / 255.0f, 1);
        shapeRenderer.rect(0, 0, Main.gameWidth, midPointY + 66);

        // Заканчиваем ShapeRenderer
        shapeRenderer.end();

        // Стартуем SpriteBatch
        batcher.begin();

        statusBar.draw(batcher, runTime);
        drawGrass();
        // Отменим прозрачность
        // Это хорошо для производительности, когда отрисовываем картинки без прозрачности
        batcher.disableBlending();
        //batcher.draw(AssetLoader.bg, 0, midPointY + 23, 136, 43);

        // Птичке нужна прозрачность, поэтому включаем ее
        batcher.enableBlending();

        // Отрисуем птичку на ее координатах. Получим Animation объект из AssetLoader
        // Передадим runTime переменную чтобы получить текущий кадр.
        map.draw(batcher);
        batcher.draw((TextureRegion) AssetLoader.ship,
                decorativeShip.getX(), decorativeShip.getY(), decorativeShip.getWidth() * 3, decorativeShip.getHeight() * 3);


        //AssetLoader.shadow.draw(batcher, "hello world", 0, 0);
        //AssetLoader.font.draw(batcher, "hello world", 0, 0);
        // Заканчиваем SpriteBatch
        batcher.end();

    }

    private void initGameObjects() {
        scroller = myWorld.getScroller();
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

}
