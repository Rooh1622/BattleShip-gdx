package ru.rooh.bsgdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by rooh on 4/18/17.
 */
public class GameRenderer {

    private GameWorld myWorld;
    private OrthographicCamera cam;
    private ShapeRenderer shapeRenderer;

    private SpriteBatch batcher;

    private int midPointY;
    private int gameHeight;

    public GameRenderer(GameWorld world, int gameHeight, int midPointY) {
        myWorld = world;

        this.gameHeight = gameHeight;
        this.midPointY = midPointY;

        cam = new OrthographicCamera();
        cam.setToOrtho(true, 137, 204);

        batcher = new SpriteBatch();
        // привяжите batcher к камере
        batcher.setProjectionMatrix(cam.combined);

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);
    }
    public void render(float runTime) {

        // мы уберем это из цикла далее, для улучшения производительности
        Ship ship = myWorld.getShip();

        // Заполним задний фон одним цветом
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Стартуем ShapeRenderer
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        // Отрисуем Background цвет
        shapeRenderer.setColor(55 / 255.0f, 80 / 255.0f, 100 / 255.0f, 1);
        shapeRenderer.rect(0, 0, 136, midPointY + 66);

        // Отрисуем Grass
        shapeRenderer.setColor(111 / 255.0f, 186 / 255.0f, 45 / 255.0f, 1);
        shapeRenderer.rect(0, midPointY + 66, 136, 11);

        // Отрисуем Dirt
        shapeRenderer.setColor(147 / 255.0f, 80 / 255.0f, 27 / 255.0f, 1);
        shapeRenderer.rect(0, midPointY + 77, 136, 52);

        // Заканчиваем ShapeRenderer
        shapeRenderer.end();

        // Стартуем SpriteBatch
        batcher.begin();
        // Отменим прозрачность
        // Это хорошо для производительности, когда отрисовываем картинки без прозрачности
        batcher.disableBlending();
        batcher.draw(AssetLoader.bg, 0, midPointY + 23, 136, 43);

        // Птичке нужна прозрачность, поэтому включаем ее
        batcher.enableBlending();

        // Отрисуем птичку на ее координатах. Получим Animation объект из AssetLoader
        // Передадим runTime переменную чтобы получить текущий кадр.
        batcher.draw((TextureRegion) AssetLoader.shipAnimation.getKeyFrame(runTime),
                ship.getX(), ship.getY(), ship.getWidth(), ship.getHeight());



        AssetLoader.shadow.draw(batcher, "hello world", 0, 0);
        AssetLoader.font.draw(batcher, "hello world", 0, 0);
        // Заканчиваем SpriteBatch
        batcher.end();

    }
}