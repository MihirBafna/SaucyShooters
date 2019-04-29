public class Bullet {

    private double initX;
    private double initY;
    private double x;
    private double y;
    private double range;
    private double speedX;
    private double speedY;
    private double size;

    public Bullet(double initX, double initY, double range, double speedX, double speedY, double size) {
        this.initX = initX;
        this.initY = initY;
        x = initX;
        y = initY;
        this.range = range;
        this.speedX = speedX;
        this.speedY = speedY;
        this.size = size;
    }

    public void travel() {
        x += speedX;
        y += speedY;
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
