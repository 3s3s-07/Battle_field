public class fighter {
    protected String name;
    protected int health;
    protected double x ,y;
    protected double speed;
    protected weapon currentweapon;
    protected boolean facingright;
    protected long lastshoot;
    protected rectangle sprite;
    public fighter(){}
    public fighter(String name,int health,double x,double y,double speed,weapon currentweapon, boolean facingright,long lastshoot){
        this.name=name;
        this.health=health;
        this.x=x;
        this.y=y;
        this.speed=speed;
        this.currentweapon=currentweapon;
        this.lastshoot=lastshoot;
        this.facingright=facingright;

    }

    public double getSpeed() {
        return speed;
    }

    public String getName() {
        return name;}

    public int getHealth() {
        return health;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public weapon getCurrentweapon() {
        return currentweapon;
    }

    public long getLastshoot() {
        return lastshoot;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFacingright(boolean facingright) {
        this.facingright = facingright;
    }

    public void setCurrentweapon(weapon currentweapon) {
        this.currentweapon = currentweapon;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setLastshoot(long lastshoot) {
        this.lastshoot = lastshoot;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}
