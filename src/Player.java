
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javafx.scene.shape.Circle;

public class Player {
    private double x;
    private double y;
    private double speed;
    private double hp;
    private Color color;

    private double velX;
    private double velY;
    private int size = 50;
    private Circle circle;
    private Inventory inventory;
    private Weapon equippedWeapon;

    public Player(double x, double y, double speed, double hp, Color color) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.color = color;
        circle = new Circle(x, y, size);
        inventory = new Inventory(2, 3, 2);
        equippedWeapon = null;
    }

    // public boolean collision(Circle A, Circle B) { // this method determines
    // whether or not two objects are colliding
    // double distance = Math.sqrt(Math.pow(A.getCenterX() - B.getCenterX(), 2) //
    // this calculates the distance between
    // // two centers
    // + Math.pow(A.getCenterY() - B.getCenterY(), 2));
    // double sumOfRadii = A.getRadius() + B.getRadius();
    // return (distance <= sumOfRadii);
    // }

    public void shoot(double mouseX, double mouseY) {
        if (equippedWeapon != null) {
            equippedWeapon.shoot(mouseX, mouseY, x, y);
        }
    }

    public void reload() {
        if (equippedWeapon instanceof Gun) {
            for (Ammo ammo : inventory.getAmmos()) {
                if (ammo != null) {
                    equippedWeapon.reload(ammo);
                }
            }
        }
    }

    public void move() {
        x += velX;
        y += velY;
        circle.setCenterX(x);
        circle.setCenterY(y);
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval((int) (x - size / 2), (int) (y - size / 2), (int) size, (int) size);
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

    public double getSpeed() {
        return this.speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getHp() {
        return this.hp;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color color) {
        this.color = color;
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

    public Circle getCircle() {
        return this.circle;
    }

    public void setCircle(Circle circle) {
        this.circle = circle;
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Weapon getEquippedWeapon() {
        return this.equippedWeapon;
    }

    public void setEquippedWeapon(Weapon equippedWeapon) {
        this.equippedWeapon = equippedWeapon;
    }

}