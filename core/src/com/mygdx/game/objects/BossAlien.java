package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Assets;

public class BossAlien {

    enum State {
        LIVE, DYING, DEAD
    }

    Vector2 position;
    float stateTime;
    TextureRegion frame;
    BossAlien.State state;

    public BossAlien(int x, int y) {
        position = new Vector2(x, y);
        state = BossAlien.State.LIVE;
    }

    public void render(SpriteBatch batch) {
        batch.draw(frame, position.x, position.y,100,100);
    }

    void update(float delta, Assets assets) {
        stateTime += delta;
        if (state == BossAlien.State.LIVE) {
            frame = assets.alien.getKeyFrame(stateTime, true);
        } else if (state == BossAlien.State.DYING) {
            frame = assets.aliendie.getKeyFrame(stateTime, false);
        }

        if (state == BossAlien.State.DYING) {
            if (assets.aliendie.isAnimationFinished(stateTime)) {
                state = BossAlien.State.DEAD;
            }
        }
    }

    void shoot() {

    }

    public void kill(Ship ship) {
        state = BossAlien.State.DYING;
        stateTime = 0;
        switch (ship.getLives()) {

            case 3:
                ship.setScore(ship.getScore() + 10000);
                break;
            case 2:
                ship.setScore(ship.getScore() + 5000);
                break;
            case 1:
                ship.setScore(ship.getScore() + 2500);
                break;
            case 0:
                ship.setScore(ship.getScore() + 1000);
                break;
            default:
                ship.setScore(ship.getScore() + 15000);
                break;
        }
    }

    public boolean isAlive() {
        return state == BossAlien.State.LIVE;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(int x, int y) {
        Vector2 v=new Vector2(x,y);
        this.position = v;
    }
}
