package com.example.battle_graphics.base;

public class weapon {

        protected String name;
        protected int damage;
        protected double projectileSpeed;
        protected long cooldown;
        public weapon(String name, int damage, double projectilespeed, Long cooldown){
            this.name=name;
            this.damage=damage;
            this.projectileSpeed=projectilespeed;
            this.cooldown=cooldown;
        }

        public String getName() {
            return name;
        }

        public int getDamage() {
            return damage;
        }

        public double getProjectileSpeed() {
            return projectileSpeed;
        }

        public long getCooldown() {
            return cooldown;
        }
        public void setName(String name) {
        this.name = name;
        }

        public void setDamage(int damage) {
        this.damage = damage;
         }
         public void setProjectileSpeed(double projectileSpeed) {
        this.projectileSpeed = projectileSpeed;}

       public void setCooldown(long cooldown) {
        this.cooldown = cooldown;
        }
        public Projectile createProjectile(Fighter owner) {
        return null;
    }
}


