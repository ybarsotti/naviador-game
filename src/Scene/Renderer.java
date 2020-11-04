package Scene;

import com.jogamp.newt.event.WindowAdapter;
import com.jogamp.newt.event.WindowEvent;
import com.jogamp.newt.opengl.GLWindow;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.util.FPSAnimator;
import input.KeyBoard;

/**
 *
 * @author Yuri Barsotti
 */
public class Renderer {
    private static GLWindow window = null;
    public static int screenWidth = 620;
    public static int screenHeight = 480;

    //Cria a janela de rendeziração do JOGL
    public static void init(){
        Cena cena = new Cena();

        GLProfile.initSingleton();
        GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities caps = new GLCapabilities(profile);
        window = GLWindow.create(caps);
        window.setTitle("Naviador");
        window.setSize(screenWidth, screenHeight);
        window.setResizable(false);
        window.setFullscreen(true);
        window.addGLEventListener(cena);
        window.addKeyListener(new KeyBoard(cena));

        window.requestFocus();
        FPSAnimator animator = new FPSAnimator(window, 60);
        animator.start(); //inicia o loop de animação

        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowDestroyNotify(WindowEvent e) {
                animator.stop();
                System.exit(0);
            }
        });


        window.setVisible(true);
    }

    public static void main(String[] args) {
        init();
    }
}
