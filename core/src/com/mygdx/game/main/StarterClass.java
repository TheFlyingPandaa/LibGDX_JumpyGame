package com.mygdx.game.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Handler.GameStateManager;

public class StarterClass extends ApplicationAdapter {

	public static final int V_WIDTH = 1500; //1500 //1776
	public static final int V_HEIGHT = 1000;//1000 //1080

	public static final float STEP = 1 / 60f;
	private float accum;

	private SpriteBatch sb;
	private OrthographicCamera cam;

	private GameStateManager gsm;
	
	//@Override
	public void create () {
		sb = new SpriteBatch();
		cam = new OrthographicCamera();
		//cam.setToOrtho(false, V_WIDTH, V_HEIGHT);

		gsm = new GameStateManager(this);
	}

	//@Override
	public void render () {
		accum += Gdx.graphics.getDeltaTime();
		while(accum >= STEP) {
			accum -= STEP;
			gsm.update(STEP);
			gsm.render();
		}
	}
	
	@Override
	public void dispose () {

	}

	public SpriteBatch getSpriteBatch() {
		return sb; }
	public OrthographicCamera getCamera() {
		return cam; }
}
