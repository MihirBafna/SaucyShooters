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

    public Item() {

    }

    public Item(String imgName, double width, double height) {
        this.imgName = imgName;
        this.width = width;
        this.height = height;
        img = new ImageIcon("img/"+imgName);
        label = new JLabel(img);
        label.setBounds((int) x, (int) y, (int) width, (int) height);
    }

    public Item(double x, double y, String imgName, double width, double height) {
        this.x = x;
        this.y = y;
        this.p = new Point((int)x,(int)y);
        this.imgName = imgName;
        this.width = width;
        this.height = height;
        img = new ImageIcon("img/"+imgName);
        label = new JLabel(img);
        label.setBounds((int) x, (int) y, (int) width, (int) height);
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

    public void addImage() {
        Main.game.add(label);
    }

    public void setBounds() {
        label.setBounds((int) x, (int) y, (int) width, (int) height);
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

    public Point getP(){
        return this.p;
    }

    public void setP(double x, double y){
        p = new Point((int)x,(int)y);
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