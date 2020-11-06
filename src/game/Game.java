package game;

import Entities.EntityA;
import Entities.EntityB;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.util.awt.TextRenderer;
import input.SoundController;

import java.awt.*;
import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Game {
    public Controller controller;
    public LinkedList<EntityA> entityAS;
    public LinkedList<EntityB> entityBS;
    SoundController soundController;
    private Timer timer = new Timer();
    private int currentLevel = 1;
    private int enemyCount = 10;
    private final int DEFAULT_ENEMY_COUNT = 150;

    public boolean venceu = false;
    public boolean jogando = true;
    public boolean delay = false;


    public Game(GLAutoDrawable drawable) {
        this.soundController = new SoundController();
        this.soundController.playThemeSong();
        this.controller = new Controller(drawable, this);

        entityAS = controller.getEntityA();
        entityBS = controller.getEntityB();
    }

    public void play() {
        renderInformation();
        renderObjects();

        if (jogando) {
            controller.createEnemies();
        }
    }

    public void renderInformation() {
        TextRenderer textRenderer = new TextRenderer(new Font("Verdana", Font.BOLD, 12));
        textRenderer.beginRendering(900, 1000);
        textRenderer.setColor(Color.WHITE);
        textRenderer.setSmoothing(true);

        textRenderer.draw("Nível: " + currentLevel, (int) (18), (int) (50));
        textRenderer.draw("Vidas: " + controller.ship.getVidas(), (int) (150), (int) (50));
        textRenderer.draw("Pontos: " + String.format("%07d", controller.ship.getPontos()), (int) (18), (int) (18));
        textRenderer.draw("Inimigos restantes: " + getEnemyCount(), (int) (150), (int) (18));
        textRenderer.endRendering();
    }

    public void renderCenterScreen(String texto) {
        TextRenderer textRenderer = new TextRenderer(new Font("Verdana", Font.BOLD, 38));

        textRenderer.beginRendering(900, 1000);
        textRenderer.setColor(Color.WHITE);
        textRenderer.setSmoothing(true);

        textRenderer.draw(texto, (int) (315), (int) (500));

        textRenderer.endRendering();
    }

    public void renderObjects() {
        controller.render();

        // Verifica a vida do jogador
        if (controller.ship.getVidas() < 0) {
            soundController.stopThemeSong();
            controller.ship.setVidas(0);
            controller.ship.destroy();
            SoundController.playerDeath();
            stopGame();
        }

        // Verifica a quantidade de inimigos

        if (noEnemies() && !venceu) {
            levelCleared();
        }

        if (venceu) {
            renderCenterScreen("Fase concluída !");
            nextStage();
        }

        // Se jogo estiver parado
        if (!controller.ship.vivo) {
            renderCenterScreen("Fim de jogo");
        }
    }

    private synchronized void nextStage() {
        if (!delay) {
            this.timer.cancel();
            this.timer = new Timer();
            TimerTask action = new TimerTask() {
                @Override
                public void run() {
                    startNextLevel();
                }
            };

            this.timer.schedule(action, 7 * 1000);
            delay = true;
        }

    }

    public void levelCleared() {
        venceu = true;
        enemyCount = 1;
        soundController.stopThemeSong();
        SoundController.stageClear();
        stopGame();
    }

    public void startNextLevel() {
        soundController.playThemeSong();
        venceu = false;
        currentLevel++;
        controller.ship.reset();
        controller.removeEnemies();
        enemyCount = DEFAULT_ENEMY_COUNT;
        startGame();
    }

    public void startGame() {
        jogando = true;
    }

    public void stopGame() {
        jogando = false;
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

    public boolean noEnemies() {
        return enemyCount < 1;
    }

}
