import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import javafx.scene.shape.Rectangle;

public class Item {

    private double x;
    private double y;
    private Point p;
    private String imgName;
    private double width;
    private double height;

    private ImageIcon img;
    private JLabel label;
    private boolean drawn;
    private Rectangle rectangle;

    public Item() {

    }

    public Item(String imgName, double width, double height) {
        this.imgName = imgName;
        this.width = width;
        this.height = height;
        drawn = false;
        rectangle = new Rectangle(-10000, -10000, width, height);
    }

    public Item(double x, double y, String imgName, double width, double height) {
        this.x = x;
        this.y = y;
        this.imgName = imgName;
        this.width = width;
        this.height = height;
        drawn = false;
        rectangle = new Rectangle(x, y, width, height);
    }

    public void drawImage(Graphics g) {
        Image image = null;
        try {
            image = ImageIO.read(new File("img/" + imgName));
        } catch (IOException e) {
            System.out.println("ERROR");
            e.printStackTrace();
        }
        g.drawImage(image, (int) x, (int) y, null);
    }

    //draw image in different location than image position
    public void drawImage(Graphics g,int a, int b) {
        Image image = null;
        try {
            image = ImageIO.read(new File("img/" + imgName));
        } catch (IOException e) {
            System.out.println("ERROR");
            e.printStackTrace();
        }
        g.drawImage(image, a, b, null);
    }

    // public void addImage() {
    // Main.game.add(label);
    // drawn = true;
    // }

    // public void removeImage() {
    // Main.game.remove(label);
    // drawn = false;
    // }

    // public void setBounds() {
    // label.setBounds((int) x, (int) y, (int) width, (int) height);
    // }

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

    public Point getP() {
        return this.p;
    }

    public void setP(double x, double y) {
        p = new Point((int) x, (int) y);
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

    public ImageIcon getImg() {
        return this.img;
    }

    public void setImg(ImageIcon img) {
        this.img = img;
    }

    public JLabel getLabel() {
        return this.label;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }

    public boolean isDrawn() {
        return this.drawn;
    }

    public void setDrawn(boolean drawn) {
        this.drawn = drawn;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle() {
        rectangle.setX(x);
        rectangle.setY(y);
        rectangle.setWidth(width);
        rectangle.setHeight(height);
    }

}