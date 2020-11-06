package gameObjects;

import Entities.EntityB;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import game.Game;
import input.SoundController;

import java.awt.*;

public class Asteroid extends GameObject implements EntityB {
    GLAutoDrawable drawable;
    Game game;
    float velocidade = 0.5f;

    boolean moveUp = false;

    public Asteroid(float x, float y, GLAutoDrawable drawable, Game game) {
        super(x, y);
        this.drawable = drawable;
        this.game = game;
        if (y < 0)
            this.moveUp = true;
    }

    public void render() {
        final GL2 gl = this.drawable.getGL().getGL2();

        gl.glPushMatrix();
        gl.glTranslatef(this.x, this.y, 0);

        gl.glColor4f(0.7f, 0.5f, 0.4f, 1);

        gl.glBegin(GL2.GL_POLYGON);
            gl.glVertex2f(0, 0);
            gl.glVertex2f(6, 2);
            gl.glVertex2f(9, -2);
            gl.glVertex2f(11, -7);
            gl.glVertex2f(8, -9);
            gl.glVertex2f(5, -11);
            gl.glVertex2f(0, -8);
            gl.glVertex2f(-0, -2);
            gl.glVertex2f(-2, -1);
        gl.glEnd();

        gl.glPopMatrix();

        if (game.jogando)
            moveForward();
    }

    public void moveForward() {
        this.x -= velocidade;
        if (moveUp)
            this.y += velocidade;
        else
            this.y -= velocidade;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) this.x - 22, (int) this.y, 10, 10);
    }

    @Override
    public void destroy() {
        this.game.controller.removeEntity(this);
        SoundController.destroyEnemySound();
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

}
