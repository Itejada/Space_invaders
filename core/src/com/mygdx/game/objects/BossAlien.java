package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Assets;

public class BossAlien {

    public enum StateBoss {
        LIVE, DYING, DEAD
    }

     Vector2 positionBoss;
    float stateTime;
     TextureRegion frame;
    StateBoss stateBoss;

    public BossAlien(int x, int y) {
        positionBoss = new Vector2(x, y);
        stateBoss = StateBoss.LIVE;
    }

    public void render(SpriteBatch batch) {
        batch.draw(frame, positionBoss.x, positionBoss.y,100,75);
    }

    void update(float delta, Assets assets) {
        stateTime += delta;
        if (stateBoss == StateBoss.LIVE) {
            frame = assets.bossAlien.getKeyFrame(stateTime, true);
        } else if (stateBoss == StateBoss.DYING) {
            frame = assets.bossAlien.getKeyFrame(stateTime, false);
        }

        if (stateBoss == StateBoss.DYING) {
            if (assets.bossAliendie.isAnimationFinished(stateTime)) {
                stateBoss = StateBoss.DEAD;
            }
        }
    }

    void shoot() {

    }

    public void kill(Ship ship) {
        stateBoss = StateBoss.DYING;
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
        return stateBoss == StateBoss.LIVE;
    }

    public Vector2 getPositionBoss() {
        return positionBoss;
    }

    public void setPosition(int x, int y) {
        Vector2 v=new Vector2(x,y);
        this.positionBoss = v;
    }
}
