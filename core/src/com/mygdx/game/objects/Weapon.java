package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Assets;
import com.mygdx.game.Timer;

public class Weapon {

    Array<Shoot> shoots;
    Array<MegaShoot> megaShoots;
    float power = 0;
    float maxPower = 20;

    Timer shootTimer;
    //delay
    float shootRate = 0.5f;

    Weapon() {
        shoots = new Array<Shoot>();
        megaShoots = new Array<MegaShoot>();
        //aqui le asignamos un delay para que no dispare demasiado rapido
        shootTimer = new Timer(shootRate);
    }

    void render(SpriteBatch batch) {
        for (Shoot shoot : shoots) {
            // aqui le dira a cada bala que se renderice
            shoot.render(batch);

        }
        for (MegaShoot megaShoot : megaShoots) {
            // aqui le dira a cada bala que se renderice
            megaShoot.render(batch);

        }
    }

    public void update(float delta, Assets assets) {
        shootTimer.update(delta);
        for (Shoot shoot : shoots) {
            shoot.update(delta, assets);
        }
        removeShoots();
    }

    public void updateMega(float delta, Assets assets) {
        shootTimer.update(delta);

        for (MegaShoot megaShoot : megaShoots) {
            megaShoot.update(delta, assets);
        }
        removeMegaShoots();
    }

    public void shoot(float position) {
        if (shootTimer.check()) {
            shoots.add(new Shoot(position));
        }
    }

    public void chargeMegaShoot(){
        if(power < maxPower) {
            power+=0.1;
        }
    }

    public void megaShoot(float position) {
        if (power > 5) {
            megaShoots.add(new MegaShoot(position, power));
            power = 0;
        }
    }

    public void removeShoots() {
        Array<Shoot> shootsToRemove = new Array<Shoot>();
        for (Shoot shoot : shoots) {
            if (shoot.state == Shoot.State.TO_REMOVE) {
                shootsToRemove.add(shoot);
            }
        }

        for (Shoot shoot : shootsToRemove) {
            shoots.removeValue(shoot, true);
        }
    }

    public void removeMegaShoots() {
        Array<MegaShoot> shootsToRemove = new Array<MegaShoot>();
        for (MegaShoot megaShoot : megaShoots) {
            if (megaShoot.state == MegaShoot.State.TO_REMOVE) {
                shootsToRemove.add(megaShoot);
            }
        }

        for (MegaShoot megaShoot : shootsToRemove) {
            megaShoots.removeValue(megaShoot, true);
        }
    }
}
