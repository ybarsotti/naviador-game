package input;

import java.io.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundController {
    Clip clip;
    public SoundController() {}

    private static void play(String path, Boolean loop) {
        File musicPath = new File(path);
        try {
            if (musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);

                if (loop)
                    clip.loop(Clip.LOOP_CONTINUOUSLY);

                clip.start();
            } else {
                System.out.println("NAO FOI POSSIVEL ENCONTRAR O SOM");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void shootSound() {
        play("shared/assets/sounds/shoot.wav", false);
    }

    public void playThemeSong() {
        File musicPath = new File("shared/assets/sounds/themeMusic.wav");
        try {
            if (musicPath.exists()) {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(musicPath);
                this.clip = AudioSystem.getClip();
                this.clip.open(audioInputStream);
                this.clip.loop(Clip.LOOP_CONTINUOUSLY);
                this.clip.start();
            } else {
                System.out.println("NAO FOI POSSIVEL ENCONTRAR O SOM");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void stopThemeSong() {
        this.clip.close();
    }

    public static void destroyEnemySound() {
        play("shared/assets/sounds/enemyKill.wav", false);
    }

    public static void playerDeath() { play("shared/assets/sounds/playerDeath.wav", false); }

    public static void loseTheme() { play("shared/assets/sounds/loseSong.wav", false); }

    public static void stageClear() {
        play("shared/assets/sounds/stageClear.wav", false);
    }
}
