package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Handler.GameStateManager;
import com.mygdx.game.Handler.IContactListener;

import com.mygdx.game.entitys.Ground;
import com.mygdx.game.entitys.Player;

import com.mygdx.game.main.StarterClass;

import java.util.Random;


public class Play extends GameState {

	private World world;
	private Box2DDebugRenderer b2dr;

	private OrthographicCamera b2dCam;
	private final static float PPM = 100;

	private int xPlace = 150;

	private IContactListener contactListner;

	private Array<Ground> ground;

	private Random rand;

	private Player player;

	private Sprite test;


	private Texture bg;
	public Play(GameStateManager gsm) {
		super(gsm);

		bg = new Texture(Gdx.files.internal("background.jpg"));

		rand = new Random();
		world = new World(new Vector2(0, -9.8f), true);
		ground = new Array<Ground>();
		for (int i = 0; i < 1; i++){
			ground.add(new Ground(world, xPlace, 150));
			//xPlace += 1000;
		}

		//walls.add(new Wall(world, 300,200,100,10));

		test = new Sprite(new Texture(Gdx.files.internal("ground1.png")));
		test.setPosition(500,500);

		contactListner = new IContactListener();

		world.setContactListener(contactListner);

		b2dr = new Box2DDebugRenderer();

		player = new Player(world);
		//Ground ground = new Ground(world,160,150);
		b2dCam = new OrthographicCamera();
		b2dCam.setToOrtho(false, StarterClass.V_WIDTH/PPM, StarterClass.V_HEIGHT/PPM);
	}
	
	public void handleInput(float dt) {
		if (player.getFuel() > 0) {
			if (Gdx.input.isKeyPressed(Input.Keys.W)) {
				player.getBody().applyForceToCenter(0, 40, true);
				player.editFuel();
			}
			if (Gdx.input.isKeyPressed(Input.Keys.A)) {
				//player.getBody().applyForceToCenter(-10, 0, true);
				//player.editFuel();
			}
			if (Gdx.input.isKeyPressed(Input.Keys.D)) {
				player.getBody().applyForceToCenter(10, 0, true);
				player.editFuel();
			}
		}
	}
	
	public void update(float dt) {

		handleInput(dt);

		world.step(dt,6,3);

		if ((ground.get(0).getBody().getPosition().x + 10)  < player.getBody().getPosition().x){
			world.destroyBody(ground.removeIndex(0).getBody());
		}

		if (ground.size < 5){
			xPlace += rand.nextInt(250)+800;
			ground.add(new Ground(world,xPlace , rand.nextInt(750)+ 125));
			//-xPlace += 1000;
		}

		if (contactListner.getResetFuel() == true){
			player.fuelReset();
		}

		if (player.getBody().getPosition().y < 0 ){
			gsm.setState(GameStateManager.PLAY);
		}

		System.out.println(player.getFuel());
	}
	
	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		b2dCam.position.set(player.getBody().getPosition().x + StarterClass.V_WIDTH / 4 / PPM, StarterClass.V_HEIGHT / 2 / PPM, 0);
		cam.position.set(player.getBody().getPosition().x * PPM + StarterClass.V_WIDTH / 4.f, StarterClass.V_HEIGHT / 2,0);
		b2dCam.update();
		cam.update();
		b2dr.render(world, b2dCam.combined);

		//cam.update();
		sb.setProjectionMatrix(cam.combined);

		sb.begin();
		test.draw(sb);
		sb.end();

	}
	
	public void dispose() {

	}


}
