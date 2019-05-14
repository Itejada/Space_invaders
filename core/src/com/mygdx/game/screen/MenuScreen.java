package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Assets;
import com.mygdx.game.SpaceInvaders;
import com.mygdx.game.objects.Space;

public class MenuScreen extends SpaceInvadersScreen {

    Space space;
    Stage stage;
    public SpriteBatch spriteBatch;

    public OrthographicCamera camera;
    public Viewport viewport;
    private Image botonPlay,botonSound,botonExit;
    Table table;

    private BitmapFont font= new BitmapFont();



    public MenuScreen(SpaceInvaders spaceInvaders) {
        super(spaceInvaders);
    }

    @Override
    public void show() {

        space = new Space();
        spriteBatch = new SpriteBatch();

        camera = new OrthographicCamera();
        camera.position.set(SCENE_WIDTH / 2, SCENE_HEIGHT / 2, 0);
        viewport = new FitViewport(SCENE_WIDTH, SCENE_HEIGHT, camera);
        viewport.apply();

        stage= new Stage(viewport);

        table=new Table();

        botonPlay =new Image(new Texture("menu_0.png"));
        botonSound =new Image(new Texture("menu_1.png"));
        botonExit =new Image(new Texture("menu_2.png"));


        //detectar clicks
        Gdx.input.setInputProcessor(stage);
        botonPlay.setPosition((SCENE_WIDTH / 2)-24,(SCENE_HEIGHT/2)+28);
        botonSound.setPosition((SCENE_WIDTH / 2)-24,SCENE_HEIGHT/2);
        botonExit.setPosition((SCENE_WIDTH / 2)-24,(SCENE_HEIGHT/2)-28);

        stage.addActor(botonPlay);
        stage.addActor(botonSound);
        stage.addActor(botonExit);




        botonPlay.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                setScreen(new GameScreen(game));
            }
        });
        botonSound.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                setScreen(new SoundScreen(game));
            }
        });
        botonExit.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                Gdx.app.exit();
            }
        });


        stage.addActor(table);
    }


    @Override
    public void render(float delta) {
        spriteBatch.setProjectionMatrix(camera.combined);
        update(delta,assets);

        spriteBatch.begin();
        space.render(spriteBatch);
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
