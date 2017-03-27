package com.mygdx.game.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Handler.GameStateManager;
import com.mygdx.game.main.StarterClass;


public abstract class GameState {
	
	protected GameStateManager gsm;
	protected StarterClass game;


	protected SpriteBatch sb;
	protected OrthographicCamera cam;
	
	protected GameState(GameStateManager gsm) {
		this.gsm = gsm;
		game = gsm.game();
		sb = game.getSpriteBatch();
		cam = game.getCamera();
	}
	
	public abstract void handleInput(float dt);
	public abstract void update(float dt);
	public abstract void render();
	public abstract void dispose();
	
}









