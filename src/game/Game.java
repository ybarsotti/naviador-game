package game;

import Entities.EntityA;
import Entities.EntityB;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.util.awt.TextRenderer;
import input.SoundController;

import java.awt.*;
import java.util.*;

public class Game {
    public Controller controller;
    public LinkedList<EntityA> entityAS;
    public LinkedList<EntityB> entityBS;
    public SoundController soundController;
    private Timer timer = new Timer();
    private int currentLevel = 1;
    private int enemyCount = 2;
    private final int DEFAULT_ENEMY_COUNT = 2;
    private boolean delayEndGame = false;
    public boolean venceu = false;
    public boolean jogando = true;
    public boolean delay = false;
    private boolean credits = false;
    private float creditsY = -10;


    public Game(GLAutoDrawable drawable) {
        this.soundController = new SoundController();
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
            if (currentLevel == 1) {
                renderCenterScreen("Fase concluída !");
                nextStage();
            }
            else if (!credits) {
                renderCenterScreen("Salvou a todos!");
                endGame();
            } else {
                credits();
            }

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

    private synchronized void endGame() {
        if (!delayEndGame) {
            this.timer.cancel();
            this.timer = new Timer();
            TimerTask action = new TimerTask() {
                @Override
                public void run() {
                    SoundController.endGame();
                    credits = true;
                }
            };

            this.timer.schedule(action, 7 * 1000);
            delayEndGame = true;
        }

    }

    private void credits() {
        TextRenderer textRenderer = new TextRenderer(new Font("Verdana", Font.BOLD, 38));

        textRenderer.beginRendering(900, 1000);
        textRenderer.setColor(Color.WHITE);
        textRenderer.setSmoothing(true);

        textRenderer.draw("Game Creator: Yuri Barsotti", (int) (150), (int) (creditsY));
        textRenderer.draw("Game Programmer: Yuri Barsotti", (int) (150), (int) (creditsY - 80));
        textRenderer.draw("Game Design: Yuri Barsotti", (int) (150), (int) (creditsY - 80 * 2));
        textRenderer.draw("Game Director: Yuri Barsotti", (int) (150), (int) (creditsY - 80 * 3));
        textRenderer.draw("Game Support: Dhiego Rodrigues", (int) (150), (int) (creditsY - 80 * 4));
        textRenderer.draw("Game Tester: Guilherme Guerra", (int) (150), (int) (creditsY - 80 * 5));
        textRenderer.draw("Agradecimentos: Marcelo Lopes", (int) (150), (int) (creditsY - 80 * 7));
        textRenderer.draw("Obrigado por jogar !", (int) (150), (int) (creditsY - 80 * 10));
        textRenderer.draw(":)", (int) (150), (int) (creditsY - 80 * 11));
        textRenderer.endRendering();

        creditsY += 1;
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
