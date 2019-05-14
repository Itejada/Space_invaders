package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Assets;
import com.mygdx.game.SoundsConfiguration;
import com.mygdx.game.Timer;
import com.mygdx.game.screen.GameScreen;

import java.util.Random;

public class IABoss {
    int x, y, maxX;

    float speed = 16f;
    boolean win;
    BossAlien bossAlien;
    Array<AlienShoot> shoots;
    SoundsConfiguration soundsConfiguration;
    Timer moveTimer, shootTimer, winTimer;
    Random random = new Random();
    int WORLD_WIDTH;
    int WORLD_HEIGHT;
    int limiteDerechaBoss;

    IABoss(int WORLD_WIDTH, int WORLD_HEIGHT, SoundsConfiguration soundsConfiguration) {

        this.x = WORLD_WIDTH / 2;
        this.y = WORLD_HEIGHT - 90;
        this.maxX = 240;
        this.WORLD_HEIGHT = WORLD_HEIGHT;
        this.WORLD_WIDTH = WORLD_WIDTH;

        this.soundsConfiguration = soundsConfiguration;

        bossAlien = new BossAlien(x, y, 10, WORLD_WIDTH, WORLD_HEIGHT);
        shoots = new Array<AlienShoot>();

        limiteDerechaBoss = WORLD_WIDTH - 10;
        moveTimer = new Timer(0.4f);
        shootTimer = new Timer(random.nextFloat() % 5 + 1);
        winTimer = new Timer(3f);
        win = false;

    }


    void render(SpriteBatch batch) {

        bossAlien.render(batch);


        for (AlienShoot shoot : shoots) {
            shoot.render(batch);
        }
    }

    public void update(float delta, Assets assets) {
        moveTimer.update(delta);
        shootTimer.update(delta);
        if (bossAlien.LIVES_BOSS==-1) {
            winTimer.update(delta);
        }
        // soundsConfiguration.update();

        move();
        shoot(assets);

        bossAlien.update(delta, assets);

        for (AlienShoot shoot : shoots) {
            shoot.update(delta, assets);
        }


        winShip(assets);
        removeShoots();
    }


    void move() {
        if (moveTimer.check()) {
            x += speed;


            if (x >= limiteDerechaBoss - bossAlien.frame.getRegionWidth()) {
                x = limiteDerechaBoss - bossAlien.frame.getRegionWidth();
                speed *= -1;

            } else if (x <= 30) {
                x = 20;
                speed *= -1;
            }

            bossAlien.positionBoss.x += speed;


        }
    }

    void shoot(Assets assets) {
        if (shootTimer.check()) {
            if (bossAlien.isAlive()) { //TODO ya no hay null pointer exception
                //TODO para que aparezca a mitad de la nave
                shoots.add(new AlienShoot(new Vector2(bossAlien.positionBoss.x + (bossAlien.frame.getRegionWidth() / 2), bossAlien.positionBoss.y)));

                assets.alienSound.play(soundsConfiguration.getVolumeAlienShoot());

                shootTimer.set((random.nextFloat() % 0.5f)+0.2f);

            }
        }
    }

    private void winShip(Assets assets) {

        if (!bossAlien.isAlive()) {
            assets.winSound.play();
            if (winTimer.check()) {
                System.out.println("WIN");
                this.win = true;
            }
        }
    }

    public void removeShoots() {
        Array<AlienShoot> shootsToRemove = new Array<AlienShoot>();
        for (AlienShoot shoot : shoots) {
            if (shoot.state == AlienShoot.State.TO_REMOVE) {
                shootsToRemove.add(shoot);
            }
        }

        for (AlienShoot shoot : shootsToRemove) {
            shoots.removeValue(shoot, true);
        }
    }

    public boolean isWin() {
        return win;
    }
}
