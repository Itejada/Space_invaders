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
    TextureRegion frame, live;
    StateBoss stateBoss;
    int LIVES_BOSS;
    int WIDTH_WORLD,HEIGTH_WORLD;


    public BossAlien(int x, int y, int LIVES_BOSS, int WIDTH_WORLD, int HEIGTH_WORLD) {
        positionBoss = new Vector2(x, y);
        stateBoss = StateBoss.LIVE;
        this.LIVES_BOSS=LIVES_BOSS;
        this.WIDTH_WORLD=WIDTH_WORLD;
        this.HEIGTH_WORLD=HEIGTH_WORLD;


    }

    public void render(SpriteBatch batch) {
        batch.draw(frame, positionBoss.x, positionBoss.y);
        for (int i = 0; i < LIVES_BOSS; i++) {
            batch.draw(live,30+(((live.getRegionWidth()*2)+1)*i),HEIGTH_WORLD-35,live.getRegionWidth()*2,live.getRegionHeight()*2);
        }
    }

    void update(float delta, Assets assets) {
        stateTime += delta;
        if (stateBoss == StateBoss.LIVE) {
            frame = assets.bossAlien.getKeyFrame(stateTime, true);
        } else if (stateBoss == StateBoss.DYING) {
            frame = assets.bossAliendie.getKeyFrame(stateTime, false);
        }
        live=assets.livesBoss.getKeyFrame(stateTime, true);

        if (stateBoss == StateBoss.DYING) {
            if (assets.bossAliendie.isAnimationFinished(stateTime)) {
                stateBoss = StateBoss.DEAD;
            }
        }
    }

    void shoot() {

    }

    public void touchToBoss(Ship ship) {


        LIVES_BOSS = ((LIVES_BOSS >= 0) ? (LIVES_BOSS - 1): (-1));
        if (LIVES_BOSS==-1) {
                stateBoss = StateBoss.DYING;
                stateTime = 0;

        }
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
        Vector2 v = new Vector2(x, y);
        this.positionBoss = v;
    }
}
