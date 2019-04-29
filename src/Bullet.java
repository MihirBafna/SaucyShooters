public class Bullet {

    private double initX;
    private double initY;
    private double x;
    private double y;
    private double range;
    private double BXVel;
    private double BYVel;
    private double size;

    public Bullet(double initX, double initY, double range, double BXVel, double BYVel, double size) {
        this.initX = initX;
        this.initY = initY;
        x = initX;
        y = initY;
        this.range = range;
        this.BXVel = BXVel;
        this.BYVel =BYVel;
        this.size = size;
    }

    public void travel() {
        x += BXVel;
        y += BYVel;
    }

    public boolean expire() {
        // pythagorean theorem to check hypotenuse
        double squareAB = (Math.pow(x - initX, 2) + Math.pow(y - initY, 2));
        if (Math.sqrt(squareAB) >= range) {
            return true;
        }
        return false;
    }

}
