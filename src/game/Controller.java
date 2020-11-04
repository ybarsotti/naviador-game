package game;

import Entities.EntityA;
import Entities.EntityB;
import Scene.Ambient;
import com.jogamp.opengl.GLAutoDrawable;
import gameObjects.NormalEnemy;
import gameObjects.Ship;

import java.util.LinkedList;
import java.util.Random;

public class Controller {
    private LinkedList<EntityA> eA = new LinkedList<EntityA>();
    private LinkedList<EntityB> eB = new LinkedList<EntityB>();

    public Game game;
    public Ship ship;
    public NormalEnemy normalEnemy;
    public Ambient ambient;

    GLAutoDrawable drawable;

    EntityA entityA;
    EntityB entityB;

    Random random = new Random();

    public Controller(GLAutoDrawable drawable, Game game){
        this.drawable = drawable;
        this.game = game;
        createPlayer();
        createNormalEnemy();
        eA.add(this.ship);
        eB.add(this.normalEnemy);
    }

    public void createPlayer() {
        this.ship = new Ship(-92, 0, 3, drawable, game);
    }

    public void createNormalEnemy() {
        this.normalEnemy = new NormalEnemy(0, 0, drawable, game);
    }

    public void createAmbient() {

    }

    public void render() {
        // Ent A
        for (int i = 0; i < eA.size(); i++) {
            entityA = eA.get(i);
            entityA.render();
        }

        // ent B
        for (int i = 0; i < eB.size(); i++) {
            entityB = eB.get(i);
            entityB.render();
        }
    }

    public void addEntity(EntityA entityA) {
        eA.add(entityA);
    }

    public void removeEntity(EntityA entityA) {
        eA.remove(entityA);
    }

    public void addEntity(EntityB entityB) {
        eB.add(entityB);
    }

    public void removeEntity(EntityB entityB) {
        eB.remove(entityB);
    }

    public LinkedList<EntityA> getEntityA() {
        return eA;
    }

    public LinkedList<EntityB> getEntityB() {
        return eB;
    }
}
