package com.mygdx.game;

public class SoundsConfiguration {

    float volumeAlienShoot = 0f;
    float volumeAlienDie = 0f;
    float volumeShipShoot = 0f;


    public SoundsConfiguration() {
    }


    public void update() {
        controlVolume();
    }

    private void controlVolume() {

        if (Controls.isMinusPressed()) {

            this.volumeAlienShoot = (volumeAlienShoot < 0) ? 0 : volumeAlienShoot - 0.002f;
            this.volumeAlienDie = (volumeAlienDie < 0) ? 0 : volumeAlienDie - 0.002f;
            this.volumeShipShoot = (volumeShipShoot < 0) ? 0 : volumeShipShoot - 0.002f;

        } else if (Controls.isPluslePressed()) {
            this.volumeAlienShoot = (volumeAlienShoot > 1) ? 1 : volumeAlienShoot + 0.002f;
            this.volumeAlienDie = (volumeAlienDie > 1) ? 1 : volumeAlienDie + 0.002f;

            this.volumeShipShoot = (volumeShipShoot > 1) ? 1 : volumeShipShoot + 0.002f;
        }


        System.out.println(volumeAlienShoot + "aa");


    }


    public float getVolumeAlienShoot() {
        return volumeAlienShoot;
    }

    public void setVolumeAlienShoot(float volumeAlienShoot) {
        this.volumeAlienShoot = volumeAlienShoot;
    }

    public float getVolumeShipShoot() {
        return volumeShipShoot;
    }

    public void setVolumeShipShoot(float volumeShipShoot) {
        this.volumeShipShoot = volumeShipShoot;
    }

    public float getVolumeAlienDie() {
        return volumeAlienDie;
    }

    public void setVolumeAlienDie(float volumeAlienDie) {
        this.volumeAlienDie = volumeAlienDie;
    }
}