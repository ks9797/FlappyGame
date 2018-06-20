package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Tube {
    public static final int TUBE_WIDTH = 52;
    public  static int FLUCTUATION = 130;//диапазон отклонений труб по высоте
    public  static int TUBE_GAP = 100; //высота расстояния между трубами
    public  static int LOWEST_OPENING = 120;//нижняя граница просвета

    private Texture topTube, bottomTube;
    private Vector2 posTopTube, posBottomTube;
    private Random rand;
    private Rectangle boundsTop, boundsBot;

    /*геттер для диапазона отклонений труб по высоте*/
    public static int getFLUCTUATION() {
        return FLUCTUATION;
    }

    /*геттер для верхней трубы*/
    public Texture getTopTube() {
        return topTube;
    }

    /*геттер для нижней трубы*/
    public Texture getBottomTube() {
        return bottomTube;
    }

    /*геттер для позиции верхней трубы*/
    public Vector2 getPosTopTube() {
        return posTopTube;
    }

    /*геттер для позиции нижней трубы*/
    public Vector2 getPosBottomTube() {
        return posBottomTube;
    }

    /*конструктор класса*/
    public Tube(float x){
        topTube = new Texture("toptube.png");
        bottomTube = new Texture("bottomtube.png");

        rand = new Random();

        posTopTube = new Vector2(x, rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        posBottomTube = new Vector2(x, posTopTube.y - TUBE_GAP - bottomTube.getHeight());

        boundsTop =  new Rectangle(posTopTube.x, posTopTube.y, topTube.getWidth(), topTube.getHeight());
        boundsBot = new Rectangle(posBottomTube.x, posBottomTube.y, bottomTube.getWidth(), bottomTube.getHeight());
    }

    /* метод изменения позиции труб*/
    public void reposition(float x){
        posTopTube.set(x, rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        posBottomTube.set(x, posTopTube.y - TUBE_GAP - bottomTube.getHeight());
        boundsTop.setPosition(posTopTube.x, posTopTube.y);
        boundsBot.setPosition(posBottomTube.x, posBottomTube.y);
    }

    /*метод определения столкновения птицы с трубой*/
    public boolean collides(Rectangle player){
        return player.overlaps(boundsTop) || player.overlaps(boundsBot);
    }

    /*метод освобождения*/
    public void dispose() {
        topTube.dispose();
        bottomTube.dispose();
    }
}
