import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.scene.shape.Rectangle;

public class Item {

    private double x;
    private double y;
    private String imgName;
    private double width;
    private double height;

    public Item() {

    }

    public Item(String imgName, double width, double height) {
        this.imgName = imgName;
        this.width = width;
        this.height = height;
    }

    public Item(double x, double y, String imgName, double width, double height) {
        this.x = x;
        this.y = y;
        this.imgName = imgName;
        this.width = width;
        this.height = height;
    }

    public void drawImage(Graphics g) {
        Image image = null;
        try {
            image = ImageIO.read(new File("img/"+imgName));
        } catch (IOException e) {
            System.out.println("ERROR");
            e.printStackTrace();
        }
        g.drawImage(image, (int) x, (int) y, null);
    }

    public Rectangle getRectangle() {
        return new Rectangle(x, y, width, height);
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

    public String getImgName() {
        return this.imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public double getWidth() {
        return this.width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return this.height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

}