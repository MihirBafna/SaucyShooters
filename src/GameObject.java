
import javafx.scene.shape.Shape;

public class GameObject {

    public static boolean collision(Shape A, Shape B) {
        Shape AB = Shape.intersect(A, B);
        if (AB.getLayoutBounds().getWidth() > 0 && AB.getLayoutBounds().getHeight() > 0) {
            return true;
        }
        return false;
    }

}