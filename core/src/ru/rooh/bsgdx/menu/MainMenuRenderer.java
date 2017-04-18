package ru.rooh.bsgdx.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import ru.rooh.bsgdx.assets.AssetLoader;
import ru.rooh.bsgdx.basics.Renderer;
import ru.rooh.bsgdx.game.GameWorld;
import ru.rooh.bsgdx.ui.SimpleButton;

/**
 * Created by rooh on 4/18/17.
 */
public class MainMenuRenderer extends Renderer {
    private SimpleButton play;
    public MainMenuRenderer(GameWorld world) {
        super(world);
        play = new SimpleButton(0,0,29,16, AssetLoader.playButtonUp,AssetLoader.playButtonDown);

    }
    @Override
    public void render(float runTime) {


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
        play.draw(batcher);


       // AssetLoader.shadow.draw(batcher, "hello world", 0, 0);
        //AssetLoader.font.draw(batcher, "hello world", 0, 0);
        // Заканчиваем SpriteBatch
        batcher.end();

    }

    public SimpleButton getPlay() {
        return play;
    }
}
