package com.mygdx.game.entitys;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Handler.B2DVar;

import java.util.Map;
import java.util.Vector;

/**
 * Created by Root on 2017-03-22.
 */
public class Player {

    private Body body;

    private float fuel;
    private final static float PPM = 100;
    public Player(World world){

        fuel = 100;

        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        bdef.position.set(160/PPM,250/PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bdef);

        shape.setAsBox(40f/PPM,40/PPM);
        fdef.shape = shape;
        fdef.filter.categoryBits = B2DVar.BIT_PLAYER;
        fdef.filter.maskBits = B2DVar.BIT_GROUND;
        //fdef.restitution = 2;
        body.createFixture(fdef).setUserData("player");



    }

    public Body getBody(){return body;}

    public float getFuel() {
        return fuel;
    }
    public void editFuel() {
        this.fuel -= 2.5;
    }
    public void fuelReset(){
        this.fuel = 100;
    }
}
