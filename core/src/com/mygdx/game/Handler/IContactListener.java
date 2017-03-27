package com.mygdx.game.Handler;

import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Root on 2017-03-23.
 */
public class IContactListener implements ContactListener {

    private boolean resetFuel = false;

    public IContactListener(){

    }

    @Override
    public void beginContact(Contact con) {
        Fixture f1 = con.getFixtureA();
        Fixture f2 = con.getFixtureB();

        if (f1.getUserData() != null && f1.getUserData().equals("player")){
            resetFuel = true;
        }
        if (f2.getUserData() != null && f2.getUserData().equals("player")){
            resetFuel = true;
        }

    }

    @Override
    public void endContact(Contact con) {
        Fixture f1 = con.getFixtureA();
        Fixture f2 = con.getFixtureB();

        if (f1.getUserData() != null && f1.getUserData().equals("player")){
            resetFuel = false;
        }
        if (f2.getUserData() != null && f2.getUserData().equals("player")){
            resetFuel = false;
        }
    }

    @Override
    public void preSolve(Contact con, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact con, ContactImpulse impulse) {

    }

    public boolean getResetFuel() {
        return resetFuel;
    }
}
