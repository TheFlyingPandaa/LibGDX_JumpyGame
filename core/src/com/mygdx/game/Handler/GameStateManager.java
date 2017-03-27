package com.mygdx.game.Handler;

import com.mygdx.game.main.StarterClass;
import com.mygdx.game.states.GameState;
import com.mygdx.game.states.Play;

import java.util.Stack;



public class GameStateManager {
	
	private StarterClass game;
	
	private Stack<GameState> gameStates;
	
	public static final int PLAY = 2;
	public static final int MENU = 4;
	public static final int HIGH = 8;

	private float timeScore = 1000000000;
	public GameStateManager(StarterClass game) {
		this.game = game;
		gameStates = new Stack<GameState>();
		pushState(PLAY);
	}
	
	public StarterClass game() { return game; }
	
	public void update(float dt) {
		gameStates.peek().update(dt);
	}
	
	public void render() {
		gameStates.peek().render();
	}
	
	private GameState getState(int state) {
		if(state == PLAY) return new Play(this);

		return null;
	}
	
	public void setState(int state) {
		popState();
		pushState(state);
	}
	
	public void pushState(int state) {
		gameStates.push(getState(state));
	}
	
	public void popState() {
		GameState g = gameStates.pop();
		g.dispose();
	}


	public void setTimer(float time){
		if (timeScore > time) {
			this.timeScore = time;
		}
	}

	public float getTimer(){
		return timeScore;
	}

}















