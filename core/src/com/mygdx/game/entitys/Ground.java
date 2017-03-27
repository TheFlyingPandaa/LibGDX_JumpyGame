package com.mygdx.game.entitys;

import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Handler.B2DVar;

/**
 * Created by Root on 2017-03-25.
 */
public class Ground {


    private Body body;

    private final static float PPM = 100;
    public Ground(World world, int x , int y) {

        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        bdef.position.set(x / PPM, y / PPM);
        bdef.type = BodyDef.BodyType.StaticBody;
        body = world.createBody(bdef);

        shape.setAsBox(100f / PPM, 20 / PPM);
        fdef.shape = shape;
        fdef.filter.categoryBits = B2DVar.BIT_GROUND;
        fdef.filter.maskBits = B2DVar.BIT_PLAYER;
        //fdef.restitution = 2;
        body.createFixture(fdef).setUserData("ground");

    }

    public Body getBody() {
        return body;
    }
}
