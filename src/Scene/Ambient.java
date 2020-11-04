package Scene;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import gameObjects.GameObject;

public class Ambient extends GameObject {
    GLAutoDrawable drawable;

    public Ambient(float x, float y, GLAutoDrawable drawable){
        super(x, y);
        this.drawable = drawable;
    }

    public void render() {
        final GL2 gl = drawable.getGL().getGL2();

        gl.glPushMatrix();
            gl.glColor4f(1, 1, 1, 1);
            gl.glBegin(GL2.GL_LINES);
                gl.glVertex3f(x, y, -1);
            gl.glEnd();
        gl.glPopMatrix();
    }
}
