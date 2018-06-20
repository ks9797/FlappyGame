package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Flappy;

public class GameOver extends State {

    private Texture backGround;
    private Texture gameOver;
    private BitmapFont font;
    private int score;
    private int bestScore;

    /*конструктор класса*/
    public GameOver(GameStateManager gsm, int score, int bestScore) {
        super(gsm);
        this.score = score;
        this.bestScore = bestScore;
        font = new BitmapFont();
        camera.setToOrtho(false, Flappy.WIDTH/2, Flappy.HEIGHT/2);
        backGround = new Texture("bg.png");
        gameOver = new Texture("gameover.png");
    }

    /*метод обработки нажатия на экран*/
    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm, bestScore));
        }
    }

    /*метод обновления*/
    @Override
    public void update(float dt) {
        handleInput();
    }

    /*метод отрисовки*/
    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(backGround,0,0);
        font.draw(sb,"SCORE: " + score, 85, 300);
        font.draw(sb,"BESTSCORE: " + bestScore, 70, 280);
        sb.draw(gameOver,camera.position.x - gameOver.getWidth() / 2, camera.position.y);
        sb.end();
    }

    /*метод освобождения*/
    @Override
    public void dispose() {
        backGround.dispose();
        gameOver.dispose();
        font.dispose();
    }
}
