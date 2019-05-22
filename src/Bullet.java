
public class Bullet {

	private double initX;
	private double initY;
	private double x;
	private double y;
	private double range;
	private double rangeFallOff;
	private double damage;
	private double damageFallOff;
	private double velX;
	private double velY;
	private double size;

	public Bullet(double initX, double initY, double range, double rangeFallOff, double damage, double damageFallOff, double velX, double velY, double size) {
		this.initX = initX;
		this.initY = initY;
		x = initX;
		y = initY;
		this.range = range;
		this.velX = velX;
		this.velY = velY;
		this.size = size;
	}

	public void travel() {
		x += velX;
		y += velY;
	}

	public boolean expire() {
		// pythagorean theorem to check hypotenuse
		double squareAB = (double) (Math.pow(x - initX, 2) + Math.pow(y - initY, 2));
		if (Math.sqrt(squareAB) >= range) {
			return true;
		}
		return false;
	}

}