package com.mygdx.game.screen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Assets;

public class Hud {


    enum State {
        HEART_SMALL, HEART_BIG;
    }

    State state;

    int lives, score;
    float stateTime;
    TextureRegion frame;

    public Hud(int lives, int score) {
        this.lives = lives;
        this.score = score;
        stateTime = 0;

    }

    public void render(SpriteBatch batch) {
        for (int i = 0; i < lives; i++) {
                batch.draw(frame,20,20);
        }

    }

    public void update(float delta, Assets assets, int lives, int score) {
        stateTime += delta;
        this.lives=lives;
        this.score=score;
        changeHeart();
        setFrame(assets);
    }

    private void changeHeart() {
        state=(state == State.HEART_BIG) ? State.HEART_SMALL : State.HEART_BIG;

    }



    void setFrame(Assets assets) {
        switch (state) {
            case HEART_SMALL:
                frame = assets.hearts.getKeyFrame(stateTime, true);
                break;
            case HEART_BIG:
                frame = assets.hearts.getKeyFrame(stateTime, true);
                break;
        }


    }
}
