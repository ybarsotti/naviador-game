package game;

import Entities.EntityA;
import Entities.EntityB;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.util.awt.TextRenderer;

import java.awt.*;
import java.util.*;

public class Game {
    public Controller controller;
    public LinkedList<EntityA> entityAS;
    public LinkedList<EntityB> entityBS;

    private int currentLevel = 1;

    private int enemyCount = 150;

    boolean jogando;


    public Game(GLAutoDrawable drawable) {
        this.controller = new Controller(drawable, this);

        entityAS = controller.getEntityA();
        entityBS = controller.getEntityB();
    }

    public void play() {
        this.renderText();
        this.renderObjects();
        this.controller.createEnemies();
    }

    public void renderText() {
        TextRenderer textRenderer = new TextRenderer(new Font("Verdana", Font.BOLD, 12));
        textRenderer.beginRendering(900, 1000);
        textRenderer.setColor(Color.WHITE);
        textRenderer.setSmoothing(true);

        textRenderer.draw("Vidas: " + this.controller.ship.getVidas(), (int) (18), (int) (50));
        textRenderer.draw("Pontos: " + String.format("%07d", this.controller.ship.getPontos()), (int) (18), (int) (18));
        textRenderer.draw("Inimigos restantes: " + getEnemyCount(), (int) (150), (int) (18));
        textRenderer.endRendering();
    }

    public void renderObjects() {
        this.controller.render();
    }

    public void startGame() {
        this.jogando = true;
    }

    public void stopGame() {
        this.jogando = false;
    }

    public int getCurrentLevel() {
        return this.currentLevel;
    }

    public void setNextLevel() {
        this.currentLevel++;
    }

    public int getEnemyCount() {
        return enemyCount;
    }

    public void decEnemyCount() {
        enemyCount--;
    }

}
