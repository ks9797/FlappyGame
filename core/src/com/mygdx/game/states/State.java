package com.mygdx.game.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public abstract class State {

    protected OrthographicCamera camera;
    protected Vector3 mouse;
    protected GameStateManager gsm;

    /*конструктор класса*/
    public State(GameStateManager gsm){
        this.gsm = gsm;
        camera = new OrthographicCamera();
        mouse = new Vector3();
    }

    /*абстрактный метод для обработки пользовательского ввода*/
    protected abstract void handleInput();
    /*абстрактный метод для обновления изображения через определенные промежутки времени*/
    public abstract void update(float dt);
    /*абстрактный метод для отрисовки*/
    public abstract void render(SpriteBatch sb);
    /*абстрактный метод для освобождения ресурсов памяти*/
    public abstract void dispose();
}
