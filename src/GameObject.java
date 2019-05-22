
import javafx.scene.shape.Shape;

public class GameObject {

    public static boolean collision(Shape A, Shape B) {
        Shape AB = Shape.intersect(A, B);
        if (AB.getLayoutBounds().getWidth() > 0 && AB.getLayoutBounds().getHeight() > 0) {
            return true;
        }
        return false;
    }

    // test methods
    public static void main(String[] arg) {
        // Shape A = new Rectangle(0,0,50,50);
        // Shape B = new Rectangle(100,100,50,50);

        // if(collision(A, B)){
        //     System.out.println("collides");
        // } else {
        //     System.out.println("no collision");
        // }
    }

}