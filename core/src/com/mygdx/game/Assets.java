package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets extends AssetManager {
    public TextureAtlas atlas;
    public Animation<TextureRegion> space, alien,bossAlien, aliendie,bossAliendie, hearts, livesBoss, naveidle, naveleft, naveright, naveshoot, shoot ,alienshoot;
    public Sound shootSound = Gdx.audio.newSound(Gdx.files.internal("shootsound.wav"));
    public Sound shipDamageSound = Gdx.audio.newSound(Gdx.files.internal("damageShip.wav"));


    public Sound alienSound = Gdx.audio.newSound(Gdx.files.internal("aliensound.wav"));
    public Sound aliendieSound = Gdx.audio.newSound(Gdx.files.internal("aliendie.wav"));

    public void load(){
        load("invaders.atlas", TextureAtlas.class);
    }

    @Override
    public synchronized boolean update() {
        boolean update = super.update();

        if(update){
            atlas = get("invaders.atlas", TextureAtlas.class);

            loadAnimations();
        }
        return update;
    }

    void loadAnimations(){
        space = new Animation<TextureRegion>(1f, atlas.findRegions("space"));

        alien = new Animation<TextureRegion>(0.4f, atlas.findRegions("alien"));
        bossAlien = new Animation<TextureRegion>(0.09f, atlas.findRegions("bossAlien"));
        aliendie = new Animation<TextureRegion>(0.05f, atlas.findRegions("aliendie"));
        bossAliendie = new Animation<TextureRegion>(0.05f, atlas.findRegions("aliendie"));

        hearts = new Animation<TextureRegion>(0.75f, atlas.findRegions("heart"));
        livesBoss = new Animation<TextureRegion>(0.55f, atlas.findRegions("livesBoss"));


        naveidle = new Animation<TextureRegion>(0.1f, atlas.findRegions("naveidle"));
        naveleft = new Animation<TextureRegion>(0.1f, atlas.findRegions("naveleft"));
        naveright = new Animation<TextureRegion>(0.1f, atlas.findRegions("naveright"));
        naveshoot = new Animation<TextureRegion>(0.2f, atlas.findRegions("naveshoot"));
        shoot = new Animation<TextureRegion>(0.05f, atlas.findRegions("shoot"));
        alienshoot = new Animation<TextureRegion>(0.1f, atlas.findRegions("alienshoot"));
    }
}
