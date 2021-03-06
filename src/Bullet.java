import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

// import javafx.*;
import javafx.scene.shape.Circle;

public class Bullet implements Serializable{

    private static final long serialVersionUID = -2349428033052770258L;
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
    private transient Circle circle;

    public Bullet(){
        this.initX = Game.player.getX();
        this.initY = Game.player.getY();
        x = initX;
        y = initY;
        this.range = 600;
        this.velX = 8;
        this.velY = 8;
        this.size = 15;
        circle = new Circle(x, y, size / 2);
    }
    /**
     * @param initX
     * @param initY
     * @param range
     * @param rangeFallOff
     * @param damage
     * @param damageFallOff
     * @param velX
     * @param velY
     * @param size
     */
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
        circle = new Circle(x, y, size / 2);
    }

    /**
     * moves the bullet and updates the hitbox
     */
    public void move() {
        x += velX;
        y += velY;
        circle.setCenterX(x);
        circle.setCenterY(y);
        damageUpdate();
    }

    /**
     * @param g
     * @param c
     * draws bullet of color c at x,y
     */
    public void draw(Graphics g, Color c) {
        g.setColor(c);
        // g.fillOval((int) (x - size / 2), (int) (y - size / 2), (int) size, (int)
        // size);
        Image image = null;
        try {
            image = ImageIO.read(new File("img/paintball.png"));
        } catch (IOException e) {
            System.out.println("ERROR");
            e.printStackTrace();
        }
        g.drawImage(image, (int) (x - size / 2), (int) (y - size / 2), null);
    }

    /**
     * decreases amount of damage based on how far bullet travels
     */
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

    /**
     * deletes bullet if hits an object or reaches max range
     */
    public boolean expire() {
        // pythagorean theorem to check hypotenuse
        double squareAB = (double) (Math.pow(x - initX, 2) + Math.pow(y - initY, 2));
        if (Math.sqrt(squareAB) >= range) {
            return true;
        }
        for (int i = 0; i < Game.objects.size(); i++) {
            if (GameObject.collision(circle, Game.objects.get(i))) {
                return true;
            }
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

    public Circle getCircle() {
        return this.circle;
    }

    public void setC(Circle circle) {
        this.circle = circle;
    }

}