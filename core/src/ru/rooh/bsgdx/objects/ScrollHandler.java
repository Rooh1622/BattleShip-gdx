package ru.rooh.bsgdx.objects;

import ru.rooh.bsgdx.Main;

/**
 * Created by rooh on 4/20/17.
 */
public class ScrollHandler {

    // заглавные буквы используются по договоренности об именовании переменных
    public static final int scroll_speed = -20;

    // ScrollHandler будет использовать следующие константы
    // чтобы определить, как быстро на перемещать объекты
    // и какой промежуток между трубами
    public static final int PIPE_GAP = 49;
    // ScrollHandler создаст все необходимые нам объекты
    private Background frontGrass, backGrass;

    // конструктор получает значение по Y оси, где нам необходимо создать наши
    // Grass и Pipe объекты.
    public ScrollHandler(float yPos, int speed) {
        frontGrass = new Background(0, yPos, (int) Main.gameWidth, (int) (486 * Main.gameWidth / 450), speed);
        backGrass = new Background(frontGrass.getTailX(), yPos, (int) Main.gameWidth, (int) (486 * Main.gameWidth / 450), speed);
    }

    public void update(float delta) {
        frontGrass.update(delta);
        backGrass.update(delta);

        if (frontGrass.isScrolledLeft()) {
            frontGrass.reset(backGrass.getTailX());

        } else if (backGrass.isScrolledLeft()) {
            backGrass.reset(frontGrass.getTailX());

        }
    }

    // методы доступа к переменным класса
    public Background getFrontGrass() {
        return frontGrass;
    }

    public Background getBackGrass() {
        return backGrass;
    }

}