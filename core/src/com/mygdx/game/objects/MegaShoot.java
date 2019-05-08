package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Assets;

public class MegaShoot {
    enum State {
        SHOOTING, TO_REMOVE
    }

    Vector2 position;

    float stateTime;
    State state;
    float speed = 1;
    private float potencia;

    TextureRegion frame;

    MegaShoot(float position, float potencia){
        //Aparicion de la bala
        this.position = new Vector2(position, 18);
        this.potencia=potencia;
        state = State.SHOOTING;
    }

    void render(SpriteBatch batch){
        batch.draw(frame, position.x-(potencia/2), position.y, potencia, potencia*2);
    }

    public void update(float delta, Assets assets) {
        stateTime += delta;

        position.y += speed;

        frame = assets.shoot.getKeyFrame(stateTime, true);
    }

    public void remove(){
        state = State.TO_REMOVE;
    }

    public float getPotencia() {
        return potencia;
    }

    public void setPotencia(float potencia) {
        this.potencia = potencia;
    }
}
