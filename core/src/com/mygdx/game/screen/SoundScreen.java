package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Assets;
import com.mygdx.game.SpaceInvaders;
import com.mygdx.game.objects.Space;
import com.mygdx.game.objects.World;

public class SoundScreen extends SpaceInvadersScreen {

    public SpriteBatch spriteBatch;
    Space space;
    public OrthographicCamera camera;
    public Viewport viewport;
    Stage stage;
    private Image botonPlus,botonMinus, botonBack;


    private BitmapFont font = new BitmapFont();


    World world;

    public SoundScreen(SpaceInvaders spaceInvaders) {
        super(spaceInvaders);
    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
        camera.position.set(SCENE_WIDTH / 2, SCENE_HEIGHT / 2, 0);
        viewport = new FitViewport(SCENE_WIDTH, SCENE_HEIGHT, camera);
        viewport.apply();
        space = new Space();

        spriteBatch = new SpriteBatch();
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);

        botonPlus = new Image(new Texture("livesBoss_0.png"));
        botonPlus.setPosition((SCENE_WIDTH / 2) - 24, (SCENE_HEIGHT / 2) - 48);
        botonMinus = new Image(new Texture("livesBoss_0.png"));
        botonMinus.setPosition(SCENE_WIDTH / 2, (SCENE_HEIGHT / 2) - 48);
        botonMinus = new Image(new Texture("livesBoss_0.png"));
        botonMinus.setPosition(SCENE_WIDTH / 2, (SCENE_HEIGHT / 2) - 48);
        botonBack =new Image(new Texture("menu_1.png"));
        botonBack.setPosition((SCENE_WIDTH / 2)-24,SCENE_HEIGHT/2);

        botonBack.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                setScreen(new MenuScreen(game));
            }
        });


        stage.addActor(botonBack);
        stage.addActor(botonPlus);
        stage.addActor(botonMinus);


    }

    @Override
    public void render(float delta) {
        spriteBatch.setProjectionMatrix(camera.combined);
        update(delta, assets);
        spriteBatch.begin();
        space.render(spriteBatch);
        spriteBatch.end();
        stage.draw();


    }

    void update(float delta, Assets assets) {

        space.update(delta, assets);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);

        viewport.update(width, height);
    }


}
