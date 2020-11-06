package game;

import Entities.EntityA;
import Entities.EntityB;
import Scene.Ambient;
import com.jogamp.opengl.GLAutoDrawable;
import gameObjects.Asteroid;
import gameObjects.NormalEnemy;
import gameObjects.Ship;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Controller {
    private LinkedList<EntityA> eA = new LinkedList<EntityA>();
    private LinkedList<EntityB> eB = new LinkedList<EntityB>();

    public Game game;
    public Ship ship;

    GLAutoDrawable drawable;

    EntityA entityA;
    EntityB entityB;

    public Controller(GLAutoDrawable drawable, Game game){
        this.drawable = drawable;
        this.game = game;
        this.ship = new Ship(-92, 0, 5, drawable, game);

        eA.add(this.ship);
    }


    public void createAmbient() {

    }

    public void createEnemies() {
        int spawn = ThreadLocalRandom.current().nextInt(0, 80);

        boolean willSpawn = spawn > 77;

        int position = ThreadLocalRandom.current().nextInt(-70, 95);

        if (willSpawn && game.getEnemyCount() > 0) {
            NormalEnemy enemy = new NormalEnemy(200, position, drawable, game);

            if (game.getEnemyCount() < 125)
                enemy.addVelocity(0.15f);

            if (game.getEnemyCount() < 100)
                enemy.addVelocity(0.20f);

            if (game.getEnemyCount() < 50)
                enemy.addVelocity(0.22f);

            if (game.getEnemyCount() < 25)
                enemy.addVelocity(0.30f);

            eB.add(enemy);
        }

        if (game.getCurrentLevel() > 1) {
            int spawnAsteroid = ThreadLocalRandom.current().nextInt(0, 80);
            boolean willSpawnAsteroid = spawnAsteroid > 78;
            float positionAsteroidY;

            float positionAsteroidX = ThreadLocalRandom.current().nextInt(0, 200);
            if (positionAsteroidX < 120 )
                positionAsteroidY = ThreadLocalRandom.current().nextInt(95, 120);
            else
                positionAsteroidY = ThreadLocalRandom.current().nextInt(-90, 120);

            if (willSpawnAsteroid) {
                Asteroid asteroid = new Asteroid(positionAsteroidX, positionAsteroidY, drawable, game);

                eB.add(asteroid);
            }
        }

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

    public void removeEnemies() {
        eB.clear();
    }
}
