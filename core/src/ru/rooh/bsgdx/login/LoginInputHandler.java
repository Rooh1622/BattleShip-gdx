package ru.rooh.bsgdx.login;

import com.badlogic.gdx.Gdx;
import ru.rooh.bsgdx.basics.InputHandler;
import ru.rooh.bsgdx.ui.InputBox;
import ru.rooh.bsgdx.ui.SimpleButton;
import ru.rooh.bsgdx.ui.StatusBar;

/**
 * Created by rooh on 4/18/17.
 */
public class LoginInputHandler extends InputHandler {
    private InputBox login;
    private InputBox passwd;
    private SimpleButton go;

    public LoginInputHandler(InputBox login, InputBox passwd, SimpleButton go, StatusBar statusBar) {
        super();
        this.login = login;
        this.passwd = passwd;
        this.go = go;
        this.statusBar = statusBar;
    }


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        login.isTouchDown(screenX, screenY);
        passwd.isTouchDown(screenX, screenY);
        go.sendLogin(screenX, screenY);
        statusBar.onClick(screenX, screenY);

        Gdx.app.log("Handler", screenX + " " + screenY);

        //Gdx.app.log("Server", Main.server.getReadyState().toString());
        //Main.server.send(Main.json.toJSONString());
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        login.isTouchUp(screenX, screenY);
        return false;
    }

}
