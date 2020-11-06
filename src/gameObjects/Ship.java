package gameObjects;

import Entities.EntityA;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import game.Game;
import game.Physics;

import java.awt.*;

public class Ship extends GameObject implements EntityA {
    private int vidas;
    private int pontos = 0;
    GLAutoDrawable drawable;
    float[] chama = {0.8f, 0.4f, 0.2f};
    boolean chamaVermelha = true;
    boolean movimentando;
    public boolean vivo = true;
    private Game game;

    public Ship(float x, float y, int vidas, GLAutoDrawable drawable, Game game) {
        super(x, y);
        this.drawable = drawable;
        this.game = game;
        this.vidas = vidas;
        this.movimentando = false;

    }

    public void shoot() {
        if (game.jogando)
            game.controller.addEntity(new Bullet(this.x, this.y, drawable, game));
    }

    public void up() {
        if (this.y < 100 && game.jogando && vivo) {
            this.y += 5;
            this.movimentando = true;
        }
    }

    public void down() {
        if (this.y > -70 && game.jogando && vivo) {
            this.y -= 5;
            this.movimentando = true;
        }
    }

    public void right() {
        if (this.x < 100 && game.jogando && vivo) {
            this.x += 5;
            this.movimentando = true;
        }
    }

    public void left() {
        if (this.x > -170 && game.jogando && vivo) {
            this.x -= 5;
            this.movimentando = true;
        }
    }

    public void render() {
        final GL2 gl = this.drawable.getGL().getGL2();

        gl.glPushMatrix();
        gl.glTranslatef(this.x, this.y, 0);

        gl.glColor4f(0, 0.7f, 0.7f, 1);

        // Corpo
        gl.glBegin(GL2.GL_POLYGON);
        gl.glVertex2f(0, 0);
        gl.glVertex2f(5, 0);
        gl.glVertex2f(5, -8);
        gl.glVertex2f(15, -8);

        gl.glVertex2f(18, -10);

        gl.glVertex2f(15, -12);
        gl.glVertex2f(0, -12);
        gl.glVertex2f(0, 0);
        gl.glEnd();

        // Cabine
        gl.glColor4f(0.7f, 0, 0.2f, 1);
        gl.glBegin(GL2.GL_POLYGON);
        gl.glVertex2f(5, 0);
        gl.glVertex2f(10, -3);
        gl.glVertex2f(15, -8);
        gl.glVertex2f(5, -8);
        gl.glEnd();

        // Asa
        gl.glColor4f(0.4f, 0.4f, 0.4f, 1);
        gl.glBegin(GL2.GL_POLYGON);
        gl.glVertex2f(1, -10);
        gl.glVertex2f(15, -10);
        gl.glVertex2f(-2, -17);
        gl.glVertex2f(-5, -17);
        gl.glEnd();

        // Curva asa
        gl.glColor4f(0.7f, 0.7f, 0.7f, 1);
        gl.glBegin(GL2.GL_POLYGON);
        gl.glVertex2f(-5, -17);
        gl.glVertex2f(-2, -17);
        gl.glVertex2f(-4, -15);
        gl.glVertex2f(-2, -15);
        gl.glEnd();

        // Propulsor
        gl.glColor4f(0.9f, 0.9f, 0.9f, 1);
        gl.glBegin(GL2.GL_POLYGON);
        gl.glVertex2f(0, -8);
        gl.glVertex2f(-4, 0);
        gl.glVertex2f(-4, -12);
        gl.glEnd();

        // Fogo
        if (movimentando) {
            gl.glColor4f(this.chama[0], this.chama[1], this.chama[2], 1);
            gl.glBegin(GL2.GL_POLYGON);
            gl.glVertex2f(-4, -2);
            gl.glVertex2f(-8, -4);
            gl.glVertex2f(-10, -9);
            gl.glVertex2f(-4, -10);
            gl.glEnd();
        }

        if (this.chamaVermelha) {
            this.chamaVermelha = false;
            this.chama[0] = 0.6f;
            this.chama[1] = 0.3f;
            this.chama[2] = 0.15f;
        } else {
            this.chamaVermelha = true;
            this.chama[0] = 1f;
            this.chama[1] = 0.3f;
            this.chama[2] = 0.5f;
        }

        // Canhão
        gl.glColor4f(0.5f, 0.5f, 0.5f, 1);
        gl.glBegin(GL2.GL_POLYGON);
        gl.glVertex2f(15, -10);
        gl.glVertex2f(20, -10);
        gl.glVertex2f(20, -11);
        gl.glVertex2f(22, -11);
        gl.glVertex2f(22, -12);
        gl.glVertex2f(20, -12);
        gl.glVertex2f(20, -13);
        gl.glVertex2f(15, -13);
        gl.glEnd();

        gl.glPopMatrix();

        this.movimentando = false;

        if (Physics.collision(this, game.entityBS)) {
            perderVida();
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x - 30, (int) y - 10, 25, 25);
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    public int getVidas() {
        return vidas;
    }

    public void perderVida() {
        this.vidas--;
    }

    public int getPontos() {
        return pontos;
    }

    public void ganharPontos() {
        this.pontos += 25;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
    }

    public void destroy() {
        this.game.controller.removeEntity(this);
        vivo = false;
    }

    public void reset() {
        x = -92;
        y = 0;
    }
}
