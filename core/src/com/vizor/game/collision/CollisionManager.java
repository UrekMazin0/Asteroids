package com.vizor.game.collision;

import com.vizor.game.enemy.AsteroidSpawner;
import com.vizor.game.player.Ship;

public class CollisionManager {
    Ship ship;
    AsteroidSpawner asteroids;
    public CollisionManager(Ship sh, AsteroidSpawner spawner){
        ship      = sh;
        asteroids = spawner;
    }

    public boolean updateCollision(){
        for (int i = 0; i < asteroids.Length(); i++){
            if(asteroids.asteroidContainer[i] == null)
                continue;

            if(ship.collisionShape.overlaps(asteroids.asteroidContainer[i].collisionShape)) {
                asteroids.destroyAsteroid(i);
                return ship.GetHit();
            }
        }
        return false;
    }
}
