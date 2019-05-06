
import java.awt.Color;
import javafx.scene.shape.Circle;

public class Player{
    private double x;
    private double y;
    private double PXVel;
    private double PYVel;
    private Circle hitBox;
    private Color playerColor;

    public Player(double x, double y, Color color){
        this.x = x;
        this.y = y;
        playerColor = color;
        hitBox = new Circle(x, y, 20);
    }
    
    public boolean collision(Circle A, Circle B) { // this method determines whether or not two objects are colliding
        double distance = Math.sqrt(Math.pow(A.getCenterX() - B.getCenterX(), 2) // this calculates the distance between two centers                                                                           
            + Math.pow(A.getCenterY() - B.getCenterY(), 2));
        double sumOfRadii = A.getRadius() + B.getRadius();
        return (distance <= sumOfRadii);
    }

    public double getX(){
        return this.x;
    }

    public double getY(){
        return this.y;
    }

    public double getXVel() {
        return this.PXVel;
    }

    public double getYVel() {
        return this.PYVel;
    }
}