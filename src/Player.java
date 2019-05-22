
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import javafx.scene.shape.Circle;

public class Player{
    private double x;
    private double y;
    private double PXVel;
    private double PYVel;
    private Circle hitBox;
    private Color playerColor; 
    private JLabel label;
    private Weapon weapon;
    private int size =100;
    

    public Player(ImageIcon img, double x, double y, Color color, double speed){
        this.label = new JLabel(img);
        this.x = x;
        this.y = y;
        this.PXVel = speed;
        this.PYVel = speed;
        weapon = null;
        playerColor = color;
        hitBox = new Circle(x, y, 20);
        label.setBounds((int) x, (int) y, size, size);
    }
    
    public boolean collision(Circle A, Circle B) { // this method determines whether or not two objects are colliding
        double distance = Math.sqrt(Math.pow(A.getCenterX() - B.getCenterX(), 2) // this calculates the distance between two centers                                                                           
            + Math.pow(A.getCenterY() - B.getCenterY(), 2));
        double sumOfRadii = A.getRadius() + B.getRadius();
        return (distance <= sumOfRadii);
    }

    public JLabel getLabel(){
        return this.label;
    }

    public double getPX(){
        return this.x;
    }

    public double getPY(){
        return this.y;
    }

    public double getPXVel() {
        return this.PXVel;
    }

    public double getPYVel() {
        return this.PYVel;
    }

    public Weapon getWeapon(){
        return this.weapon;
    }

    public void setWeapon(Weapon w){
        this.weapon = w;
    }

    public void setx(int x) {
        this.x = x;
        label.setBounds((int)this.x, (int)this.y, size, size);
    }

    public void sety(int y) {
        this.y = y;
        label.setBounds((int) this.x, (int) this.y, size, size);

    }

}