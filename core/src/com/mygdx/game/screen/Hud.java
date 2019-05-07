package com.mygdx.game.screen;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Assets;

public class Hud {

    enum State {
        HEART_SMALL, HEART_BIG;

    }

    private BitmapFont font;
    private float WORLD_WIDTH, WORLD_HEIGHT;
    State state;

    int lives, score;
    float stateTime;
    TextureRegion frame;


    public Hud(int lives, int score,float WORLD_WIDTH, int WORLD_HEIGHT) {
        this.lives = lives;
        this.score = score;
        stateTime = 0;
        this.WORLD_WIDTH=WORLD_WIDTH;
        this.WORLD_HEIGHT=WORLD_HEIGHT;
        font= new BitmapFont();


    }

    public void render(SpriteBatch batch) {
        for (int i = 0; i < lives; i++) {
               batch.draw(frame, (20*i)+10, WORLD_HEIGHT-(WORLD_HEIGHT/14), 20,15);

        }
        font.draw(batch,score+"", WORLD_WIDTH-15,  WORLD_HEIGHT-10,0,(int)WORLD_WIDTH,false);

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
