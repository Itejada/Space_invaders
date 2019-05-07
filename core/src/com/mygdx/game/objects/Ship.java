package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Assets;
import com.mygdx.game.Controls;
import com.mygdx.game.SoundsConfiguration;
import com.mygdx.game.screen.GameScreen;
import com.mygdx.game.screen.Hud;

public class Ship {

    enum State {
        IDLE, LEFT, RIGHT, SHOOT, MEGA_SHOOT;
    }

    State state;
    Vector2 position;
    private int lives;
    private int score= 0;
    float stateTime;
    float speed = 5;
    SoundsConfiguration soundsConfiguration;

    TextureRegion frame;

    Weapon weapon;
    Hud hud;

    Ship(int initialPosition, int lives, float WORLD_WIDTH, int WORLD_HEIGHT, SoundsConfiguration soundsConfiguration){
        position = new Vector2(initialPosition, 10);
        this.soundsConfiguration=soundsConfiguration;
        state = State.IDLE;
        stateTime = 0;
        this.lives=lives;
        weapon = new Weapon();
        hud=new Hud(lives,score,WORLD_WIDTH, WORLD_HEIGHT);
    }


    void setFrame(Assets assets){
        switch (state){
            case IDLE:
                frame = assets.naveidle.getKeyFrame(stateTime, true);
                break;
            case LEFT:
                frame = assets.naveleft.getKeyFrame(stateTime, true);
                break;
            case RIGHT:
                frame = assets.naveright.getKeyFrame(stateTime, true);
                break;
            case SHOOT:
                frame = assets.naveshoot.getKeyFrame(stateTime, true);
                break;
            case MEGA_SHOOT:
                frame = assets.naveshoot.getKeyFrame(stateTime, true);
                break;
            default:
                frame = assets.naveidle.getKeyFrame(stateTime, true);
                break;
        }
    }

    void render(SpriteBatch batch){
        batch.draw(frame, position.x, position.y);

        //actualiza el arma
        weapon.render(batch);
        hud.render(batch);
    }

    void processInput(Assets assets){
        switch (state){
            case IDLE:
                if(Controls.isLeftPressed()){
                    state = State.LEFT;
                    moveLeft();
                } else if(Controls.isRightPressed()){
                    state = State.RIGHT;
                    moveRight();
                } else if(Controls.isMegaShootPressed()){
                    state = State.MEGA_SHOOT;
                } else if(Controls.isShootPressed()){
                    state = State.SHOOT;
                    shoot(assets);
                }
                break;

            case LEFT:
                if(Controls.isLeftPressed()){
                    state = State.LEFT;
                    moveLeft();
                } else if(Controls.isRightPressed()){
                    state = State.IDLE;
                } else if(Controls.isMegaShootPressed()){
                    state = State.MEGA_SHOOT;
                } else if(Controls.isShootPressed()){
                    state = State.SHOOT;
                    shoot(assets);
                } else if(Controls.notKeyPressed()){
                    state = State.IDLE;
                }
                break;
            case RIGHT:
                if(Controls.isRightPressed()){
                    state = State.RIGHT;
                    moveRight();
                } else if(Controls.isLeftPressed()){
                    state = State.IDLE;
                } else if(Controls.isMegaShootPressed()){
                    state = State.MEGA_SHOOT;
                } else if(Controls.isShootPressed()){
                    state = State.SHOOT;
                    shoot(assets);
                } else if(Controls.notKeyPressed()){
                    state = State.IDLE;
                }
                break;
            case SHOOT:
                if(Controls.isLeftPressed()){
                    state = State.LEFT;
                    moveLeft();
                } else if(Controls.isRightPressed()){
                    state = State.RIGHT;
                    moveRight();
                } else if(Controls.isMegaShootPressed()){
                    state = State.MEGA_SHOOT;
                } else if(Controls.isShootPressed()){
                    state = State.SHOOT;
                    shoot(assets);
                } else if(Controls.notKeyPressed()){
                    state = State.IDLE;
                }
                break;
            case MEGA_SHOOT:
                if(Controls.isMegaShootPressed()){
                    state = State.MEGA_SHOOT;
                    chargeMegaShoot();
                } else if(!Controls.isMegaShootPressed()){
                    state = State.IDLE;
                    megaShoot(assets);
                }
                break;
        }
    }

    public void update(float delta, Assets assets) {
        stateTime += delta;
        soundsConfiguration.update();
        processInput(assets);

        setFrame(assets);

        //Actualiza la arma
        weapon.update(delta, assets);
        hud.update(delta,assets,lives,score);
        weapon.updateMega(delta, assets);
    }

    void idle(){
    }

    void moveLeft(){
        position.x -= speed;
    }

    void moveRight(){
        position.x += speed;
    }

    void shoot(Assets assets){
        weapon.shoot(position.x + (frame.getRegionWidth()/2));

        long id =assets.shootSound.play(soundsConfiguration.getVolumeShipShoot());
        //assets.shootSound.setVolume(id,0);
    }

    void chargeMegaShoot(){
        state = State.MEGA_SHOOT;
        weapon.chargeMegaShoot();
    }

    void megaShoot(Assets assets){
        weapon.megaShoot(position.x + (frame.getRegionWidth()/2));
        assets.shootSound.play();
    }

    public void damage() {
        setLives((getLives()>=0) ?  getLives()-1 :(-1));


    }

    public int getLives() {
        return lives;
    }
    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
