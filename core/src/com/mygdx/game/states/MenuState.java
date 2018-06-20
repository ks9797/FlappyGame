package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Flappy;

public class MenuState extends State {

    private Texture backGround;
    private Texture playBtn;
    private int bScore;

    public MenuState(GameStateManager gsm, int bestScore) {
        super(gsm);
        this.bScore = bestScore;
        camera.setToOrtho(false, Flappy.WIDTH/2, Flappy.HEIGHT/2);
        backGround = new Texture("bg.png");
        playBtn = new Texture("playbtn.png");
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm, bScore));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(backGround,0,0);
        sb.draw(playBtn,camera.position.x - playBtn.getWidth() / 2, camera.position.y);
        sb.end();
    }

    @Override
    public void dispose() {
        backGround.dispose();
        playBtn.dispose();
    }
}
