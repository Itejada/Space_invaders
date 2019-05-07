package com.mygdx.game;

public class Timer {
    float time;
    float max;

    public Timer(float max){
        //aqui se define el limite que queramos
        this.max = max;
    }

    public void update(float delta){
        //TODO preguntar ha gerard como funciona esta suma, se suma de 60 en 60?

        //el delta son 60 frames por segundo
        time += delta;
    }

    public boolean check(){
        //aqui seteamos el tiempo ha 0 cuando supere o iguale a nuestro maximo
        if(time >= max){
            time = 0;
            return true;
        }
        return false;
    }

    //actualiza nuestro maximo
    public void set(float max){
        this.max = max;
    }
}
