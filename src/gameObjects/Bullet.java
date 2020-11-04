package gameObjects;

import Entities.EntityA;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import game.Game;
import game.Physics;
import input.SoundController;

import java.awt.*;

public class Bullet extends GameObject implements EntityA {
    GLAutoDrawable drawable;

    private Game game;

    public Bullet(float x, float y, GLAutoDrawable drawable, Game game) {
        super(x, y);
        this.drawable = drawable;
        this.game = game;
        SoundController.shootSound();
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 5, 1);
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    public void render() {
        final GL2 gl = this.drawable.getGL().getGL2();

        gl.glPushMatrix();
        gl.glTranslatef(this.x + 22, this.y - 11.5f, 0);

        gl.glColor4f(1, 1, 1, 1);

        gl.glLineWidth(2);
        gl.glBegin(GL2.GL_LINES);
        gl.glVertex2f(0, 0);
        gl.glVertex2f(5, 0);
        gl.glEnd();

        gl.glPopMatrix();
        this.x += 4;

        if(Physics.collision(this, game.entityBS)) {
            System.out.println("Colisao detectada");
        }

    }
}
