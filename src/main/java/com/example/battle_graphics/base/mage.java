package com.example.battle_graphics.base;

public class mage extends fighter {
    public mage(String name,int health,double speed){
        super(name,health,speed);
        name="mage";
       int health=90;
        double speed = 2.5;
        currentweapon=new magicwand();
    }
}
