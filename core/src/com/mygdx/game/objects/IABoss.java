package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Assets;
import com.mygdx.game.SoundsConfiguration;
import com.mygdx.game.Timer;

import java.util.Random;

public class IABoss {
    int x, y, maxX;

    float speed = 8f;

    BossAlien bossAlien;
    Array<AlienShoot> shoots;
    SoundsConfiguration soundsConfiguration;
    Timer moveTimer, shootTimer;
    Random random = new Random();

    IABoss(int WORLD_WIDTH, int WORLD_HEIGHT, SoundsConfiguration soundsConfiguration) {

        this.x = 0;
        this.y = WORLD_HEIGHT - 30;
        this.maxX = 60;

        this.soundsConfiguration = soundsConfiguration;

        bossAlien = new BossAlien(x,y);
        shoots = new Array<AlienShoot>();

        moveTimer = new Timer(0.8f);
        shootTimer = new Timer(random.nextFloat() % 5 + 1);

        positionBoss();
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
        // soundsConfiguration.update();

        move();
        shoot(assets);

        bossAlien.update(delta, assets);

        for (AlienShoot shoot : shoots) {
            shoot.update(delta, assets);
        }


        winShip();
        removeShoots();
    }


    void positionBoss() {
                bossAlien.setPosition( x, y );
    }


    void move() {
        if (moveTimer.check()) {
            x += speed;

            if (x > maxX) {
                bossAlien.position.y -= 10;
                x = maxX;
                speed *= -1;

            } else if (x < 0) {
                bossAlien.position.y -= 10;
                x = 0;
                speed *= -1;
            }

            bossAlien.position.x += speed;


        }
    }

    void shoot(Assets assets) {
        if (shootTimer.check()) {
            if (!bossAlien.isAlive()) { //FIXME ya no hay null pointer exception
                   shoots.add(new AlienShoot(new Vector2(bossAlien.position)));

                assets.alienSound.play(soundsConfiguration.getVolumeAlienShoot());

                shootTimer.set(random.nextFloat() % 5 + 1);

            }
        }
    }

    private void winShip() {

        if (!bossAlien.isAlive()) {
            System.out.println("WIN");
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
}
