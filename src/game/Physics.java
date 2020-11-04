package game;

import Entities.EntityA;
import Entities.EntityB;
import gameObjects.GameObject;

import java.util.LinkedList;

public class Physics {

    public static boolean collision(EntityA entityA, LinkedList<EntityB> entityBS) {
        for (int i = 0; i < entityBS.size(); i++) {
            if (entityA.getBounds().intersects(entityBS.get(i).getBounds())){
                return true;
            }
        }
        return false;
    }
}
