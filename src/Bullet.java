import java.awt.Color;
import java.awt.Graphics;
// import javafx.*;
import javafx.scene.shape.Circle;

public class Bullet {

    private double initX;
    private double initY;
    private double x;
    private double y;
    private double range;
    private double rangeFallOff;
    private double damage;
    private double damageFallOff;
    private double velX;
    private double velY;
    private double size;
    private Circle c;

    public Bullet(double initX, double initY, double range, double rangeFallOff, double damage, double damageFallOff,
            double velX, double velY, double size) {
        this.initX = initX;
        this.initY = initY;
        x = initX;
        y = initY;
        this.range = range;
        this.velX = velX;
        this.velY = velY;
        this.size = size;
        c = new Circle(x,y,size);
    }

    public Circle hitbox() {
        return c;
    }

    public void travel() {
        x += velX;
        y += velY;
        c.setCenterX(x);
        c.setCenterY(y);
    }

    public void draw(Graphics g, Color c) {
        g.setColor(c);
        g.fillOval((int) (x - size), (int) (y - size), (int) size, (int) size);
    }

    public void damageUpdate() {
        double squareAB = (double) (Math.pow(x - initX, 2) + Math.pow(y - initY, 2));
        double hypotenuse = Math.sqrt(squareAB);

        double rangeDiff = range - rangeFallOff;
        double damageDiff = damage = damageFallOff;
        double dmgToRange = damageDiff / rangeDiff;

        if (hypotenuse >= rangeFallOff) {
            if (damage >= damageFallOff) {
                damage -= dmgToRange;
            }
        }
    }

    public boolean expire() {
        // pythagorean theorem to check hypotenuse
        double squareAB = (double) (Math.pow(x - initX, 2) + Math.pow(y - initY, 2));
        if (Math.sqrt(squareAB) >= range) {
            return true;
        }
        return false;
    }

    public double getInitX() {
        return this.initX;
    }

    public void setInitX(double initX) {
        this.initX = initX;
    }

    public double getInitY() {
        return this.initY;
    }

    public void setInitY(double initY) {
        this.initY = initY;
    }

    public double getX() {
        return this.x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return this.y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getRange() {
        return this.range;
    }

    public void setRange(double range) {
        this.range = range;
    }

    public double getRangeFallOff() {
        return this.rangeFallOff;
    }

    public void setRangeFallOff(double rangeFallOff) {
        this.rangeFallOff = rangeFallOff;
    }

    public double getDamage() {
        return this.damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public double getDamageFallOff() {
        return this.damageFallOff;
    }

    public void setDamageFallOff(double damageFallOff) {
        this.damageFallOff = damageFallOff;
    }

    public double getVelX() {
        return this.velX;
    }

    public void setVelX(double velX) {
        this.velX = velX;
    }

    public double getVelY() {
        return this.velY;
    }

    public void setVelY(double velY) {
        this.velY = velY;
    }

    public double getSize() {
        return this.size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public Circle getC() {
        return this.c;
    }

    public void setC(Circle c) {
        this.c = c;
    }

}