package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Assets;
import com.mygdx.game.SoundsConfiguration;

public class World {
    Space space;
    Ship ship;
    AlienArmy alienArmy;
    int LIVES_PLAYER=3;
    SoundsConfiguration soundsConfiguration;

    int WORLD_WIDTH, WORLD_HEIGHT;

    public World(int WORLD_WIDTH, int WORLD_HEIGHT){
        this.WORLD_WIDTH = WORLD_WIDTH;
        this.WORLD_HEIGHT = WORLD_HEIGHT;
        this.soundsConfiguration=new SoundsConfiguration();
        space = new Space();
        ship = new Ship(WORLD_WIDTH/2,LIVES_PLAYER, WORLD_WIDTH, WORLD_HEIGHT,soundsConfiguration);
        alienArmy = new AlienArmy(WORLD_WIDTH, WORLD_HEIGHT,soundsConfiguration);
    }

    public void render(float delta, SpriteBatch batch, Assets assets){

        update(delta, assets);

        batch.begin();
        space.render(batch);
        ship.render(batch);
        alienArmy.render(batch);
        batch.end();
    }

    void update(float delta, Assets assets){
        space.update(delta, assets);
        ship.update(delta, assets);
        alienArmy.update(delta, assets);
        soundsConfiguration.update();


        checkCollisions(assets);
    }

    private void checkCollisions(Assets assets) {
        checkNaveInWorld();
        checkShootsInWorld();
        checkMegaShootsInWorld();
        checkShootsToAlien(assets);
        checkShootsToShip();
    }

    private void checkShootsToShip() {
        Rectangle shipRectangle = new Rectangle(ship.position.x, ship.position.y, ship.frame.getRegionWidth(), ship.frame.getRegionHeight());

        for(AlienShoot shoot: alienArmy.shoots){
            Rectangle shootRectangle = new Rectangle(shoot.position.x, shoot.position.y, shoot.frame.getRegionWidth(), shoot.frame.getRegionHeight());

            if (Intersector.overlaps(shootRectangle, shipRectangle)) {
                ship.damage();
                shoot.remove();
            }
        }
    }

    private void checkShootsToAlien(Assets assets) {
        for(Shoot shoot: ship.weapon.shoots){
            Rectangle shootRectangle = new Rectangle(shoot.position.x, shoot.position.y, shoot.frame.getRegionWidth(), shoot.frame.getRegionHeight());
            for(Alien alien: alienArmy.aliens){
                if(alien.isAlive()) {
                    Rectangle alienRectangle = new Rectangle(alien.position.x, alien.position.y, alien.frame.getRegionWidth(), alien.frame.getRegionHeight());

                    if (Intersector.overlaps(shootRectangle, alienRectangle)) {
                        alien.kill(ship);
                        shoot.remove();
                        assets.aliendieSound.play(soundsConfiguration.getVolumeAlienDie());
                    }
                }
            }
        }
        for(MegaShoot megaShoot: ship.weapon.megaShoots){
            Rectangle shootRectangle = new Rectangle(megaShoot.position.x, megaShoot.position.y, megaShoot.frame.getRegionWidth(), megaShoot.frame.getRegionHeight());
            for(Alien alien: alienArmy.aliens){
                if(alien.isAlive()) {
                    Rectangle alienRectangle = new Rectangle(alien.position.x, alien.position.y, alien.frame.getRegionWidth(), alien.frame.getRegionHeight());

                    if (Intersector.overlaps(shootRectangle, alienRectangle)) {
                        alien.kill(ship);
                        megaShoot.remove();
                        assets.aliendieSound.play(soundsConfiguration.getVolumeAlienDie());
                    }
                }
            }
        }
    }

    private void checkShootsInWorld() {
        for(Shoot shoot: ship.weapon.shoots){
            if(shoot.position.y > WORLD_HEIGHT){
                shoot.remove();
            }
        }
        //borra los disparos que se hayan salido de la pantalla
        for(MegaShoot megaShoot: ship.weapon.megaShoots){
            if(megaShoot.position.y > WORLD_HEIGHT){
                megaShoot.remove();
            }
        }

        for(AlienShoot shoot: alienArmy.shoots){
            if(shoot.position.y < 0){
                shoot.remove();
            }
        }
    }
    private void checkMegaShootsInWorld() {
        for(MegaShoot megaShoot: ship.weapon.megaShoots){
            if(megaShoot.position.y > WORLD_HEIGHT){
                megaShoot.remove();
            }
        }

//        for(AlienShoot shoot: alienArmy.shoots){
//            if(shoot.position.y < 0){
//                shoot.remove();
//            }
//        }
    }

    private void checkNaveInWorld() {
        if(ship.position.x > WORLD_WIDTH-32){
            ship.position.x = WORLD_WIDTH-32;
        } else if(ship.position.x < 0){
            ship.position.x = 0;
        }
    }

    public Ship getShip() {
        return ship;
    }
}
