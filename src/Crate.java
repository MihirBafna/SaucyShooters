import java.util.ArrayList;
import javafx.scene.shape.Shape;

public class Crate extends Item {

	private boolean spawnable;
	private ArrayList<Weapon> weaponPool;
	private ArrayList<Ammo> ammoPool;
	private ArrayList<Shape> objects;

	public Crate(String imgName, double width, double height) {
		super(imgName, width, height);
		this.ammoPool = Game.ammoPool;
		this.weaponPool = Game.weaponPool;
		this.objects = Game.objects;
		spawnable = false;
		// change to map width height
		while (!spawnable) {
			setX(Math.random() * 1921);
			setY(Math.random() * 1081);
			if (legalSpawn()) {
				spawnable = true;
			}
		}
		objects.add(getRectangle());
	}

	public double totalWeaponFrequency() {
		double totalWeaponFrequency = 0;
		for (Weapon w : weaponPool) {
			totalWeaponFrequency += w.getDropRate();
		}
		return totalWeaponFrequency;
	}

	public double totalAmmoFrequency() {
		double totalAmmoFrequency = 0;
		for (Ammo a : ammoPool) {
			totalAmmoFrequency += a.getDropRate();
		}
		return totalAmmoFrequency;
	}

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
				if (w instanceof Grenade) {
					Grenade temp = (Grenade) w;
					Grenade drop = new Grenade(getX(), getY(), temp);
					// return drop;
				}
			}
			lowerBound = upperBound;
		}
		return null;
	}

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

	public boolean legalSpawn() {
		for (int i = 0; i < objects.size(); i++) {
			if (GameObject.collision(getRectangle(), objects.get(i))) {
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

	public ArrayList<Shape> getObjects() {
		return this.objects;
	}

	public void setObjects(ArrayList<Shape> objects) {
		this.objects = objects;
	}

}
