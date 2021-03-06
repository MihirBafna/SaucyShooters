import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;

public class Inventory implements Serializable {

    
    private static final long serialVersionUID = -228417892005900847L;
    private ArrayList<Gun> guns;
    private ArrayList<Ammo> ammos;
    private ArrayList<Grenade> grenades;
    
    private int gunSlots;
    private int ammoSlots;
    private int grenadeSlots;

    /**
     * constructor
     * creates inventory with slots for each player
     */
    public Inventory() {
        guns = new ArrayList<Gun>();
        ammos = new ArrayList<Ammo>();
        grenades = new ArrayList<Grenade>();

        this.gunSlots = 2;
        this.ammoSlots = 3;
        this.grenadeSlots = 2;

        nullify();
    }

    /**
     * makes every array element null
     */
    public void nullify() {
        for(int g = 0; g < gunSlots; g++){
            guns.add(null);
        }
        for(int a = 0; a < ammoSlots; a++){
            ammos.add(null);
        }
        for(int g = 0; g < grenadeSlots; g++){
            grenades.add(null);
        }
    }

    /**
     * @param gun
     * adds gun to arraylist
     * removes a gun aswell if list is full
     */
    public void addGun(Gun gun) {
        ArrayList<Gun> guns = Game.player.getInventory().getGuns();
        for (int g = 0; g < guns.size(); g++) {
            if (guns.get(g) == null) {
                guns.set(g, gun);
                guns.get(g).setEquipped(false);
                guns.get(g).setRectangle();
                Game.items.remove(gun);
                return;
            }
        }
        for (int g = 0; g < guns.size(); g++) {
            if (guns.get(g).isEquipped()) {
                Game.player.setEquippedWeapon(null);
                guns.get(g).setX(gun.getX());
                guns.get(g).setY(gun.getY());
                guns.get(g).setRectangle();
                guns.get(g).setEquipped(false);
                Game.items.remove(gun);
                Game.items.add(guns.get(g));
                guns.set(g, gun);
                guns.get(g).setRectangle();
                guns.get(g).setEquipped(true);
                Game.player.setEquippedWeapon(null);
                Game.player.setEquippedWeapon(guns.get(g));
                return;
            }
        }
    }

    /**
     * @param grenade
     * adds grenade to arraylist
     * removes a grenade as well if list is full
     */
    public void addGrenade(Grenade grenade) {
        ArrayList<Grenade> grenades = Game.player.getInventory().getGrenades();
        for (int g = 0; g < grenades.size(); g++) {
            if (grenades.get(g) == null) {
                grenades.set(g, grenade);
                grenades.get(g).setEquipped(false);
                Game.items.remove(grenade);
                return;
            }
        }
        for (int g = 0; g < grenades.size(); g++) {
            if (grenades.get(g).isEquipped()) {
                Game.player.setEquippedWeapon(null);
                Game.items.remove(grenade);
                grenades.get(g).setX(grenade.getX());
                grenades.get(g).setY(grenade.getY());
                grenades.get(g).setEquipped(false);
                Game.items.add(grenades.get(g));
                grenades.set(g, grenade);
                grenades.get(g).setEquipped(true);
                Game.player.setEquippedWeapon(null);
                Game.player.setEquippedWeapon(grenades.get(g));
            }
        }
    }

    /**
     * @param ammo
     * adds ammo to arraylist
     * removes an ammo as well if list is full
     */
    public void addAmmo(Ammo ammo) {
        ArrayList<Ammo> ammos = Game.player.getInventory().getAmmos();
        for (int a = 0; a < ammos.size(); a++) {
            if (ammos.get(a) != null) {
                if (ammos.get(a).getType().equals(ammo.getType())) {
                    int count = ammo.getAmount();
                    for (int i = 0; i < count; i++) {
                        if (ammos.get(a).getAmount() < ammos.get(a).getStackSize()) {
                            if (ammo.getAmount() > 0) {
                                ammos.get(a).add(1);
                                ammo.subtract(1);
                            }
                        }
                        if (ammo.getAmount() == 0) {
                            Game.items.remove(ammo);
                            return;
                        }
                    }
                }
            }
        }
        for (int a = 0; a < ammos.size(); a++) {
            if (ammos.get(a) == null) {
                ammos.set(a, ammo);
                Game.items.remove(ammo);
                return;
            }
        }
    }

    public ArrayList<Gun> getGuns() {
        return this.guns;
    }

    public void setGuns(ArrayList<Gun> guns) {
        this.guns = guns;
    }

    public ArrayList<Ammo> getAmmos() {
        return this.ammos;
    }

    public void setAmmos(ArrayList<Ammo> ammos) {
        this.ammos = ammos;
    }

    public ArrayList<Grenade> getGrenades() {
        return this.grenades;
    }

    public void setGrenades(ArrayList<Grenade> grenades) {
        this.grenades = grenades;
    }

    public int getGunSlots() {
        return this.gunSlots;
    }

    public void setGunSlots(int gunSlots) {
        this.gunSlots = gunSlots;
    }

    public int getAmmoSlots() {
        return this.ammoSlots;
    }

    public void setAmmoSlots(int ammoSlots) {
        this.ammoSlots = ammoSlots;
    }

    public int getGrenadeCap() {
        return this.grenadeSlots;
    }

    public void setGrenadeSlots(int grenadeSlots) {
        this.grenadeSlots = grenadeSlots;
    }

}