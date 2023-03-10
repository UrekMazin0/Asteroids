package com.vizor.game.collision;

import com.vizor.game.enemy.AsteroidSpawner;
import com.vizor.game.player.Militor;
import com.vizor.game.player.Projectile;
import com.vizor.game.player.Ship;

import java.util.ArrayList;

public class CollisionManager {
    public Ship ship;
    AsteroidSpawner asteroids;

    public CollisionManager(Ship sh, AsteroidSpawner spawner){
        ship      = sh;
        asteroids = spawner;
    }

    public boolean updateCollision(float dt){

        for (int i = 0; i < asteroids.Length(); i++){
            if(asteroids.asteroidContainer[i] == null)
                continue;

            if(ship.projectile != null)
                if(ship.projectile.collisionShape.overlaps(asteroids.asteroidContainer[i].collisionShape)) {
                    asteroids.destroyAsteroid(i);
                    ship.projectile = null;
                    return false;
                }

            if(ship.collisionShape.overlaps(asteroids.asteroidContainer[i].collisionShape)) {
                asteroids.destroyAsteroid(i);
                return ship.GetHit();
            }
        }
        return false;
    }
}
