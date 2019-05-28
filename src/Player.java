
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
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

    private String imgName;

    public Player(String imgName, double x, double y, double speed, double hp, Color color) {
        this.imgName = imgName;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.color = color;
        circle = new Circle(x, y, size / 2);
        inventory = new Inventory(2, 3, 2);
        equippedWeapon = null;
    }

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
        Game.displayX -= velX;
        Game.displayY -= velY;
        x += velX;
        y += velY;
        circle.setCenterX(x);
        circle.setCenterY(y);
    }

    public void drawImage(Graphics g) {
        Image image = null;
        try {
            image = ImageIO.read(new File("img/" + imgName));
        } catch (IOException e) {
            System.out.println("ERROR");
            e.printStackTrace();
        }
        g.drawImage(image, (int) Game.screenwidth / 2 - size / 2, (int) Game.screenheight / 2 - size / 2, null);
    }

    public void gather() {
        for (Item i : Game.items) {
            if (GameObject.collision(circle, i.getRectangle())) {
                if (i instanceof Gun) {
                    inventory.addGun((Gun) i);
                    return;
                }
                if (i instanceof Grenade) {
                    inventory.addGrenade((Grenade) i);
                    return;
                }
                if (i instanceof Ammo) {
                    inventory.addAmmo((Ammo) i);
                    return;
                }
            }
        }
    }

    public void openCrate() {
        for (Item c : Game.items) {
            if (c instanceof Crate) {
                if (GameObject.collision(circle, c.getRectangle())) {
                    Game.objects.remove(c.getRectangle());
                    Game.items.remove(c);
                    Weapon w = ((Crate) c).generateWeapon();
                    Game.items.add(w);
                    Ammo a = ((Crate) c).generateAmmo();
                    Game.items.add(a);
                    break;
                }
            }
        }
    }

    public int getEquippedAmmo() {
        int amount = 0;
        for (Ammo a : inventory.getAmmos()) {
            if (a != null) {
                if (a.getType().equals(((Gun) equippedWeapon).getAmmoType())) {
                    amount += a.getAmount();
                }
            }
        }
        return amount;
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
        return equippedWeapon;
    }

    public void setEquippedWeapon(Weapon nextEquipped) {
        if (equippedWeapon != null) {
            equippedWeapon.setEquipped(false);
        }
        nextEquipped.setEquipped(true);
        equippedWeapon = nextEquipped;
    }

}