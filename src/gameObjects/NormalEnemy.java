package gameObjects;

import Entities.EntityB;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import input.SoundController;

import java.awt.*;

public class NormalEnemy extends GameObject implements EntityB {
    GLAutoDrawable drawable;
    SoundController soundController;
    boolean colisao;

    public NormalEnemy(float x, float y, GLAutoDrawable drawable) {
        super(x, y);
        this.drawable = drawable;
        this.soundController = new SoundController();
        this.colisao = false;
    }

    public void render() {
        final GL2 gl = this.drawable.getGL().getGL2();

        gl.glPushMatrix();
        gl.glTranslatef(this.x, this.y, 0);

        gl.glColor4f(0.4f, 0, 0.7f, 0.7f);

        // Corpo
        gl.glBegin(GL2.GL_POLYGON);
            gl.glVertex2f(0, 0);
            gl.glVertex2f(-10, -3);
            gl.glVertex2f(-12, -4);
            gl.glVertex2f(-10, -4);
            gl.glVertex2f(2, -4);
        gl.glEnd();

        // Hitbox
        gl.glColor4f(1, 1, 1, 0);
        gl.glBegin(GL2.GL_QUADS);
            gl.glVertex2f(0, 0);
            gl.glVertex2f(-12, 0);
            gl.glVertex2f(-12, -4);
            gl.glVertex2f(0, -4);
        gl.glEnd();

        gl.glPopMatrix();
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) this.x - 12, (int) this.y + 6, 10, 5);
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    public void destroyEnemy() {
        this.soundController.destroyEnemySound();
    }

}
