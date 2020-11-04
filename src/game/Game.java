package game;

import Entities.EntityA;
import Entities.EntityB;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.util.awt.TextRenderer;
import gameObjects.NormalEnemy;
import gameObjects.Ship;
import input.SoundController;

import java.awt.*;
import java.util.*;

public class Game {
    public Controller controller;
    public LinkedList<EntityA> entityAS;
    public LinkedList<EntityB> entityBS;

    boolean jogando;

    ArrayList<NormalEnemy> normalEnemies = new ArrayList<>();

    public Game(GLAutoDrawable drawable) {
        this.controller = new Controller(drawable, this);

        entityAS = controller.getEntityA();
        entityBS = controller.getEntityB();
    }

    public void play() {
        this.renderText();
        this.renderObjects();
    }

    public void renderText() {
        TextRenderer textRenderer = new TextRenderer(new Font("Verdana", Font.BOLD, 12));
        textRenderer.beginRendering(900, 1000);
        textRenderer.setColor(Color.WHITE);
        textRenderer.setSmoothing(true);

        textRenderer.draw("Colisão: ", (int) (18), (int) (50));
        textRenderer.draw("Pontos: " + String.format("%07d", 5), (int) (18), (int) (18));
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

}
