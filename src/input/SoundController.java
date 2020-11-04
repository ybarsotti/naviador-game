package input;

import java.io.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundController {
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

    public static void themeSong() {
        play("shared/assets/sounds/themeMusic.wav", true);
    }

    public static void destroyEnemySound() {
        play("shared/assets/sounds/enemyKill.wav", false);
    }
}
