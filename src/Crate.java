import java.util.ArrayList;
import javafx.scene.shape.Shape;

public class Crate extends Item {

	private boolean spawnable;
	private ArrayList<Weapon> weaponPool;
	private ArrayList<Ammo> ammoPool;

	/**
	 * @param imgName
	 * @param width
	 * @param height
	 * @param index
	 * generates crate with random x,y
	 */
	public Crate(String imgName, double width, double height, int index) {
		super(imgName, width, height);
		this.ammoPool = Game.ammoPool;
		this.weaponPool = Game.weaponPool;
		super.index = index;
		spawnable = false;
		// change to map width height
		while (!spawnable) {
			setX(Math.random() * 1921);
			setY(Math.random() * 1081);
			setRectangle();
			if (legalSpawn()) {
				spawnable = true;
			}
		}
		setRectangle();
		Game.objects.add(getRectangle());
	}


	/**
	 * @param x
	 * @param y
	 * @param imgName
	 * @param width
	 * @param height
	 * @param index
	 * generates crate with specified x,y
	 */
	public Crate(double x, double y, String imgName, double width, double height, int index) {
		super(x, y, imgName, width, height);
		this.ammoPool = Game.ammoPool;
		this.weaponPool = Game.weaponPool;
		super.index = index;
		spawnable = false;
		// change to map width height
		// while (!spawnable) {
		// 	setX(Math.random() * 1921);
		// 	setY(Math.random() * 1081);
		// 	if (legalSpawn()) {
		// 		spawnable = true;
		// 	}
		// }
		setRectangle();
		Game.objects.add(getRectangle());
	}

	/**
	 * @return total droprate of weapons
	 */
	public double totalWeaponFrequency() {
		double totalWeaponFrequency = 0;
		for (Weapon w : weaponPool) {
			totalWeaponFrequency += w.getDropRate();
		}
		return totalWeaponFrequency;
	}

	/**
	 * @return total droprate of ammo
	 */
	public double totalAmmoFrequency() {
		double totalAmmoFrequency = 0;
		for (Ammo a : ammoPool) {
			totalAmmoFrequency += a.getDropRate();
		}
		return totalAmmoFrequency;
	}

	/**
	 * @return random weapon in weaponpool
	 */
	public Weapon generateWeapon() {
		double random = Math.random() * totalWeaponFrequency();
		double lowerBound = 0;
		double upperBound = 0;
		for (Weapon w : weaponPool) {
			upperBound = lowerBound + w.getDropRate();
			if (lowerBound <= random && random <= upperBound) {
				if (w instanceof Gun) {
					Gun temp = (Gun) w;
					Gun drop = new Gun(getX(), getY(), temp);
					return drop;
				}
			}
			lowerBound = upperBound;
		}
		return null;
	}

	/**
	 * @return random ammo in ammopool
	 */
	public Ammo generateAmmo() {
		double random = Math.random() * totalAmmoFrequency();
		double lowerBound = 0;
		double upperBound = 0;
		for (Ammo a : ammoPool) {
			upperBound = lowerBound + a.getDropRate();
			if (lowerBound <= random && random <= upperBound) {
				Ammo drop = new Ammo(getX(), getY()+30, a);
				drop.setAmount(drop.getPileAmount());
				return drop;
			}
			lowerBound = upperBound;
		}
		return null;
	}

	/**
	 * @return true if crate can spawn without interference
	 */
	public boolean legalSpawn() {
		for (int i = 0; i < Game.objects.size(); i++) {
			if (GameObject.collision(getRectangle(), Game.objects.get(i))) {
				return false;
			}
		}
		return true;
	}

	public boolean isSpawnable() {
		return this.spawnable;
	}

	public void setSpawnable(boolean spawnable) {
		this.spawnable = spawnable;
	}

	public ArrayList<Weapon> getWeaponPool() {
		return this.weaponPool;
	}

	public void setWeaponPool(ArrayList<Weapon> weaponPool) {
		this.weaponPool = weaponPool;
	}

}
