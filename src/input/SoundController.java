package input;

import java.io.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundController {
    Clip clip;

    public SoundController() {
    }

    private static void play(String path, Boolean loop) {
        try {
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(SoundController.class.getResource(path));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInput);

            if (loop)
                clip.loop(Clip.LOOP_CONTINUOUSLY);

            clip.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void shootSound() {
        play("sounds/shoot.wav", false);
    }

    public void playThemeSong() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource("sounds/themeMusic.wav"));
            this.clip = AudioSystem.getClip();
            this.clip.open(audioInputStream);
            this.clip.loop(Clip.LOOP_CONTINUOUSLY);
            this.clip.start();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void playIntro() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource("sounds/gameIntro.wav"));
            this.clip = AudioSystem.getClip();
            this.clip.open(audioInputStream);
            this.clip.loop(Clip.LOOP_CONTINUOUSLY);
            this.clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void stopThemeSong() {
        this.clip.close();
    }

    public static void destroyEnemySound() {
        play("sounds/enemyKill.wav", false);
    }

    public static void playerDeath() {
        play("sounds/playerDeath.wav", false);
    }

    public static void loseTheme() {
        play("sounds/loseSong.wav", false);
    }

    public static void stageClear() {
        play("sounds/stageClear.wav", false);
    }

    public static void endGame() {
        play("sounds/endGame.wav", false);
    }


}
