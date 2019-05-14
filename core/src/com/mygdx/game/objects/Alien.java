package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Assets;

public class Alien {

    public enum State {
        LIVE, DYING, DEAD
    }

     Vector2 positionAlien;
    float stateTime;
     TextureRegion frame;
     State state;

    public Alien(int x, int y) {
        positionAlien = new Vector2(x, y);
        state = State.DYING;
    }

    public void render(SpriteBatch batch) {
        batch.draw(frame, positionAlien.x, positionAlien.y);
    }

    void update(float delta, Assets assets) {
        stateTime += delta;
        if (state == State.LIVE) {
            frame = assets.alien.getKeyFrame(stateTime, true);
        } else if (state == State.DYING) {
            frame = assets.aliendie.getKeyFrame(stateTime, false);
        }

        if (state == State.DYING) {
            if (assets.aliendie.isAnimationFinished(stateTime)) {
                state = State.DEAD;
            }
        }
    }

    void shoot() {

    }

    public void kill(Ship ship) {
        state = State.DYING;
        stateTime = 0;
        switch (ship.getLives()) {

            case 3:
                ship.setScore(ship.getScore() + 100);
                break;
            case 2:
                ship.setScore(ship.getScore() + 50);
                break;
            case 1:
                ship.setScore(ship.getScore() + 25);
                break;
            case 0:
                ship.setScore(ship.getScore() + 10);
                break;
            default:
                ship.setScore(ship.getScore() + 150);
                break;
        }
    }

    public boolean isAlive() {
        return state == State.LIVE;
    }

}
