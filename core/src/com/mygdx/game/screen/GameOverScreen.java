package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Assets;
import com.mygdx.game.SpaceInvaders;
import com.mygdx.game.objects.Space;
import com.mygdx.game.objects.World;

import java.awt.*;

public class GameOverScreen extends SpaceInvadersScreen {

    Stage stage;
    public SpriteBatch spriteBatch;
    Space space;
    public OrthographicCamera camera;
    public Viewport viewport;
    private Image boton;
    int puntuacion;
    boolean win;


    private BitmapFont font = new BitmapFont();


    public GameOverScreen(SpaceInvaders spaceInvaders, int puntuacion, boolean win) {
        super(spaceInvaders);
        this.puntuacion = puntuacion;
        this.win = win;
    }

    @Override
    public void show() {

        space = new Space();
        camera = new OrthographicCamera();
        camera.position.set(SCENE_WIDTH / 2, SCENE_HEIGHT / 2, 0);
        viewport = new FitViewport(SCENE_WIDTH, SCENE_HEIGHT, camera);
        viewport.apply();
        spriteBatch = new SpriteBatch();

        stage = new Stage(viewport);

        //table=new Table();

        boton = new Image(new Texture("heartR.png"));
        //detectar clicks
        Gdx.input.setInputProcessor(stage);
        boton.setPosition((SCENE_WIDTH / 2) - 24, (SCENE_HEIGHT / 2) - 48);

        stage.addActor(boton);


        boton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                setScreen(new MenuScreen(game));
            }
        });

    }


    @Override
    public void render(float delta) {
        spriteBatch.setProjectionMatrix(camera.combined);
        update(delta,assets);

        spriteBatch.begin();
        if (win) {
            space.render(spriteBatch);
            font.draw(spriteBatch, "YOU WIN", (SCENE_WIDTH / 2)+27, SCENE_HEIGHT-(SCENE_HEIGHT / 4), 0, SCENE_WIDTH / 2, false);
        } else {
            font.draw(spriteBatch, "GAME OVER", SCENE_WIDTH / 1.62f, SCENE_HEIGHT / 1.75f, 0, SCENE_WIDTH / 2, false);
        }
        font.draw(spriteBatch, puntuacion + "", (SCENE_WIDTH / 2) + 25, (SCENE_HEIGHT / 1.75f) - 70, 0, SCENE_WIDTH / 2, false);
        spriteBatch.end();
        stage.draw();
    }

    void update(float delta, Assets assets) {

        space.update(delta,assets);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);

        viewport.update(width, height);
    }

}
