package com.mygdx.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class GameStateManager {
    private Stack<State> states; //стек состояний

    /*конструктор класса*/
    public GameStateManager(){
        states = new Stack<State>();
    }

    /*метод помещения элемента в вершину стека*/
    public void push(State state){
        states.push(state);
    }

    /*метод удаления первого элемента из стека*/
    public void pop(){
        states.pop().dispose();
    }

    /*метод удаления первого элемента из стека и помещения нового на первое место*/
    public void set(State state){
        states.pop().dispose();
        states.push(state);
    }

    /*метод обновления, при котором возвращается первый элемент стека*/
    public void update(float dt){
        states.peek().update(dt);
    }

    /*метод отрисовки верхнего элемента стека*/
    public void render(SpriteBatch sb){
        states.peek().render(sb);
    }
}
