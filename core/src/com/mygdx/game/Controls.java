package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Controls {
    public static boolean isLeftPressed() {
        return Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A);
    }

    public static boolean isRightPressed() {
        return Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D);
    }

    public static boolean isShootPressed() {
        return Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)|| Gdx.input.isKeyPressed(Input.Keys.SPACE);
    }

    public static boolean isMegaShootPressed() {
        return Gdx.input.isKeyPressed(Input.Keys.S)|| Gdx.input.isKeyPressed(Input.Keys.DOWN);
    }

    public static boolean notKeyPressed(){
        return !isLeftPressed() && ! isRightPressed() && !isMegaShootPressed() && !isShootPressed();
    }

    public static boolean isMinusPressed(){
        return Gdx.input.isKeyPressed(Input.Keys.MINUS);
    }
    public static boolean isPluslePressed(){
        return Gdx.input.isKeyPressed(Input.Keys.PLUS);
    }
}
