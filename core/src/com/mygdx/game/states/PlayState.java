package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Flappy;
import com.mygdx.game.sprites.Bird;
import com.mygdx.game.sprites.Tube;

public class PlayState extends State {

    public static int TUBE_SPACING = 125; //расстояние между трубами
    public static int TUBE_COUNT = 4;//количество пар труб
    private static final int GROUND_Y_OFFSET = -30;//смещение земли по y
    private int score = 0;
    private int bScore;
    private BitmapFont font;

    private Bird bird;
    private Texture backGround;
    private Texture ground;
    private Vector2 groundPos1, groundPos2;

    private Array <Tube> tubes;

    /*конструктор класса*/
    public PlayState(GameStateManager gsm, int bestScore) {
        super(gsm);
        bird = new Bird(50, 300);
        font = new BitmapFont();
        this.bScore = bestScore;
        camera.setToOrtho(false, Flappy.WIDTH / 2,Flappy.HEIGHT / 2);
        backGround = new Texture("bg.png");
        ground = new Texture("ground.png");
        groundPos1 = new Vector2(camera.position.x - camera.viewportWidth / 2, GROUND_Y_OFFSET);
        groundPos2 = new Vector2((camera.position.x - camera.viewportWidth / 2) + ground.getWidth(), GROUND_Y_OFFSET);

        tubes = new Array<Tube>();

        for (int i = 0; i < TUBE_COUNT; i++){
            tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));
        }
    }

    /*геттер для счетчика*/
    public int getScore() {
        return score;
    }

    /*метод для обработки нажатия пользователем на экран*/
    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched())
            bird.jump();
    }

    /*метод для обновления элементов игры(птичка, зебля, трубы), подсчет очков*/
    @Override
    public void update(float dt) {
        handleInput();
        updateGround();
        bird.update(dt);
        camera.position.x = bird.getPosition().x + 80;

        score = Math.round((int)bird.getPosition().x / 177);

        for (int i = 0; i < tubes.size; i++) {
            Tube tube = tubes.get(i);
            if (camera.position.x - (camera.viewportWidth / 2) > tube.getPosTopTube().x + tube.getTopTube().getWidth()) {
                tube.reposition(tube.getPosTopTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
            }
            if (tube.collides(bird.getBounds())) {
                if (bScore < score)
                    bScore = score;
                gsm.set(new GameOver(gsm, score, bScore));
            }
        }
        camera.update();
    }

    /*отрисовка элементов игры*/
    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(backGround, camera.position.x - (camera.viewportWidth / 2), 0);
        sb.draw(bird.getBird(), bird.getPosition().x, bird.getPosition().y);
        for (Tube tube : tubes) {
            sb.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            sb.draw(tube.getBottomTube(), tube.getPosBottomTube().x, tube.getPosBottomTube().y);
        }
        sb.draw(ground, groundPos1.x, groundPos1.y);
        sb.draw(ground, groundPos2.x, groundPos2.y);
        font.draw(sb, "SCORE: " + score, bird.getPosition().x + 50, 375);
        sb.end();
    }

    /*метод обновления земли*/
    private void updateGround(){
        if (camera.position.x - (camera.viewportWidth / 2) > groundPos1.x + ground.getWidth())
            groundPos1.add(ground.getWidth() * 2, 0);
        if (camera.position.x - (camera.viewportWidth / 2) > groundPos2.x + ground.getWidth())
            groundPos2.add(ground.getWidth() * 2, 0);
    }

    /*метод для освобождения памяти от ресурсов*/
    @Override
    public void dispose() {
        backGround.dispose();
        bird.dispose();
        ground.dispose();
        for (Tube tube : tubes)
            tube.dispose();
        font.dispose();
    }
}
