
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javafx.scene.shape.Circle;

public class Player implements Serializable{
	private static final long serialVersionUID = -2993316011764722030L;
    private double x;
    private double y;
    private double speed;
    private double hp;
    private Color color;
    private boolean dead;
    private double velX;
    private double velY;
    private int size = 100;
    private double theta = 0;
    private String imgName = "NEWCHARACTER4.png";
    private int score;
    private transient Circle circle;
    private Inventory inventory;
    private Weapon equippedWeapon;



    public Player(){
        this.x = Game.screenwidth / 2;
        this.y = Game.screenwidth / 2;
        this.speed = 3.0;
        this.hp = 15.0;
        this.color = Color.BLACK;
    }

    /**
     * @param imgName
     * @param x
     * @param y
     * @param speed
     * @param hp
     * @param color
     */
    public Player(String imgName, double x, double y, double speed, double hp, Color color) {
        this.imgName = imgName;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.color = color;
        score = 0;
        circle = new Circle(x, y, size / 2);
        inventory = new Inventory();
        equippedWeapon = null;
    }

    /**
     * @param mouseX
     * @param mouseY shoots equipped gun based on position of mouse and player
     */
    public void shoot(double mouseX, double mouseY) {
        if (equippedWeapon != null) {
            equippedWeapon.shoot(mouseX, mouseY, x, y);
        }
    }

    /**
     * reloads equipped gun
     */
    public void reload() {
        if (equippedWeapon instanceof Gun) {
            for (Ammo ammo : inventory.getAmmos()) {
                if (ammo != null) {
                    equippedWeapon.reload(ammo);
                }
            }
        }
    }

    /**
     * moves player changes displayX,Y
     */
    public void move() {
        Game.displayX -= velX;
        Game.displayY -= velY;
        x += velX;
        y += velY;
        circle.setCenterX(x);
        circle.setCenterY(y);
    }

    /**
     * @param g draws player in center of screen
     */
    public void drawImage(Graphics g) {
        int drawX = (int) Game.screenwidth / 2;
        int drawY = (int) Game.screenheight / 2;
        Image image = null;
        try {
            image = ImageIO.read(new File("img/" + imgName));
        } catch (IOException e) {
            System.out.println("ERROR");
            e.printStackTrace();
        }
        // g.drawImage(image, (int) Game.screenwidth / 2 - size / 2, (int)
        // Game.screenheight / 2 - size / 2, null);
        // double rotationRequired = Math.toRadians (theta);
        // double rotateX = size / 2;
        // double rotateY = size / 2;
        // AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired,
        // rotateX, rotateY);
        // AffineTransformOp op = new AffineTransformOp(tx,
        // AffineTransformOp.TYPE_BILINEAR);
        Graphics2D g2d = (Graphics2D) g;
        // g2d.drawImage(op.filter(image, null), (int) Game.screenwidth / 2 - size / 2,
        // (int) Game.screenheight / 2 - size / 2, null);
        AffineTransform backup = g2d.getTransform();
        AffineTransform a = AffineTransform.getRotateInstance(Math.PI / 2 + theta + .22, drawX, drawY);
        g2d.setTransform(a);
        g2d.drawImage(image, drawX - (size / 2), drawY - (size / 2), null);
        g2d.setTransform(backup);
    }

    public void drawImage(Graphics g, int x, int y) {
        int drawX = x;
        int drawY = y;
        Image image = null;
        try {
            image = ImageIO.read(new File("img/" + imgName));
        } catch (IOException e) {
            System.out.println("ERROR");
            e.printStackTrace();
        }
        g.drawImage(image, drawX - (size / 2), drawY - (size / 2), null);
    }

    // public void rotate() {
    // double rotationRequired = Math.toRadians (theta);
    // double rotateX = size / 2;
    // double rotateY = size / 2;
    // AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired,
    // rotateX, rotateY);
    // AffineTransformOp op = new AffineTransformOp(tx,
    // AffineTransformOp.TYPE_BILINEAR);

    // // Drawing the rotated image at the required drawing locations
    // g2d.drawImage(op.filter(image, null), drawLocationX, drawLocationY, null);

    // }

    /**
     * picks up an item off the ground
     */
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

    /**
     * opens up crates
     */
    public void openCrate() {
        for (Item c : Game.items) {
            if (c instanceof Crate) {
                if (GameObject.collision(circle, c.getRectangle())) {
                    score++;
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

    /**
     * @return amount of ammo that equipped gun can use
     */
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

    public boolean getDead() {
        return this.dead;
    }

    public void setDead(boolean x) {
        this.dead = x;
        System.out.println(Client.clientnumber+" " +Game.players.get(Client.clientnumber).getDead());
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

    public double getTheta() {
        return theta;
    }

    public void setTheta(double mouseX, double mouseY) {
        double xDiff = mouseX - x - size / 2;
        double yDiff = mouseY - y - size / 2;
        theta = Math.atan2(yDiff, xDiff);
    }

    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }

    public void setEquippedWeapon(Weapon nextEquipped) {
        if (equippedWeapon != null) {
            equippedWeapon.setEquipped(false);
        }
        if (nextEquipped != null) {
            nextEquipped.setEquipped(true);
        }
        equippedWeapon = nextEquipped;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

}