package com.example.battle_graphics.base;

public class Weapon {

        protected String name;
        protected int damage;
        private double projectileSpeed;
        protected long cooldown;

        public Weapon(String name, int damage, double projectilespeed, Long cooldown){
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
}


