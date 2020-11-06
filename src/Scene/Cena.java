package Scene;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.awt.TextRenderer;
import game.Game;
import input.SoundController;

import java.awt.*;

/**
 * @author Yuri Barsotti
 */
public class Cena implements GLEventListener {
    private float xMin, xMax, yMin, yMax, zMin, zMax;
    public Game game;
    public int tela = 1;
    public SoundController soundController;

    GLU glu;

    @Override
    public void init(GLAutoDrawable drawable) {
        //dados iniciais da cena
        glu = new GLU();
        //Estabelece as coordenadas do SRU (Sistema de Referencia do Universo)
        xMin = yMin = zMin = -100;
        xMax = yMax = zMax = 100;

        this.game = new Game(drawable);
        this.soundController = new SoundController();
        this.soundController.playIntro();
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        //obtem o contexto Opengl
        GL2 gl = drawable.getGL().getGL2();
        //define a cor da janela (R, G, G, alpha)
        gl.glClearColor(0, 0, 0, 1);
        //limpa a janela com a cor especificada
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
        gl.glEnable(gl.GL_BLEND);
        gl.glBlendFunc(gl.GL_SRC_ALPHA, gl.GL_ONE_MINUS_SRC_ALPHA);
        gl.glLoadIdentity(); //lê a matriz identidade

        if (tela == 1) {
            showMenu();

        }
        // Jogar
        if (tela == 2) {
            this.game.play();
        }

        gl.glFlush();
    }

    public void showMenu() {
        TextRenderer textRenderer = new TextRenderer(new Font("Verdana", Font.BOLD, 38));
        textRenderer.beginRendering(900, 1000);
        textRenderer.setColor(Color.WHITE);
        textRenderer.setSmoothing(true);

        textRenderer.draw("Naviador", (int) (100), (int) (750));
        textRenderer.endRendering();

        TextRenderer textRenderer1 = new TextRenderer(new Font("Verdana", Font.BOLD, 22));
        textRenderer1.beginRendering(900, 1000);
        textRenderer1.setColor(Color.GRAY);
        textRenderer1.setSmoothing(true);
        textRenderer1.draw("Como jogar", (int) (300), (int) (500));
        textRenderer1.draw("Setas do teclado: Movimentar a nave ", (int) (300), (int) (450));
        textRenderer1.draw("Espaço: Atirar ", (int) (300), (int) (400));
        textRenderer1.endRendering();

        TextRenderer textRenderer2 = new TextRenderer(new Font("Verdana", Font.PLAIN, 22));
        textRenderer2.beginRendering(900, 1000);
        textRenderer2.setColor(Color.RED);
        textRenderer2.setSmoothing(true);
        textRenderer2.draw("Destrua as naves para evitar o avanço das tropas inimigas! ", (int) (100), (int) (700));
        textRenderer2.endRendering();

        TextRenderer textRenderer3 = new TextRenderer(new Font("Verdana", Font.BOLD, 22));
        textRenderer3.beginRendering(900, 1000);
        textRenderer3.setColor(Color.ORANGE);
        textRenderer3.setSmoothing(true);
        textRenderer3.draw("Aperte ENTER para começar", (int) (300), (int) (200));
        textRenderer3.endRendering();
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        //obtem o contexto grafico Opengl
        GL2 gl = drawable.getGL().getGL2();

        //evita a divisão por zero
        if (height == 0) height = 1;
        //calcula a proporção da janela (aspect ratio) da nova janela
        float aspect = (float) width / height;

        //seta o viewport para abranger a janela inteira
        gl.glViewport(0, 0, width, height);

        //ativa a matriz de projeção
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity(); //lê a matriz identidade

        //Projeção ortogonal
        //true:   aspect >= 1 configura a altura de -1 para 1 : com largura maior
        //false:  aspect < 1 configura a largura de -1 para 1 : com altura maior
        if (width >= height)
            gl.glOrtho(xMin * aspect, xMax * aspect, yMin, yMax, zMin, zMax);
        else
            gl.glOrtho(xMin, xMax, yMin / aspect, yMax / aspect, zMin, zMax);

        //ativa a matriz de modelagem
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity(); //lê a matriz identidade
        System.out.println("Reshape: " + width + ", " + height);
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
    }
}
