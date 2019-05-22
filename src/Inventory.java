import java.util.ArrayList;

public class Inventory {

    
    private ArrayList<Gun> guns;
    private ArrayList<Ammo> ammos;
    private ArrayList<Grenade> grenades;
    
    private int gunSlots;
    private int ammoSlots;
    private int grenadeSlots;

    public Inventory(int gunSlots, int ammoSlots, int grenadeSlots) {
        guns = new ArrayList<Gun>();
        ammos = new ArrayList<Ammo>();
        grenades = new ArrayList<Grenade>();

        this.gunSlots = gunSlots;
        this.ammoSlots = ammoSlots;
        this.grenadeSlots = grenadeSlots;

        nullify();
    }

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

    public void addGun(Gun gun) {
        ArrayList<Gun> guns = Main.player.getInventory().getGuns();
        for (int g = 0; g < guns.size(); g++) {
            if (guns.get(g) == null) {
                guns.set(g, gun);
                guns.get(g).setEquipped(true);
                Main.items.remove(gun);
                return;
            }
        }
        for (int g = 0; g < guns.size(); g++) {
            if (guns.get(g).isEquipped()) {
                guns.get(g).setEquipped(false);
                Main.items.add(guns.get(g));
                guns.set(g, gun);
                guns.get(g).setEquipped(true);
                Main.items.remove(gun);
                return;
            }
        }
    }

    public void addGrenade(Grenade grenade) {
        ArrayList<Grenade> grenades = Main.player.getInventory().getGrenades();
        for (int g = 0; g < grenades.size(); g++) {
            if (grenades.get(g) == null) {
                grenades.set(g, grenade);
                grenades.get(g).setEquipped(false);
                Main.items.remove(grenade);
                return;
            }
        }
        for (int g = 0; g < grenades.size(); g++) {
            if (grenades.get(g).isEquipped()) {
                grenades.get(g).setEquipped(false);
                Main.items.add(grenades.get(g));
                grenades.set(g, grenade);
                grenades.get(g).setEquipped(true);
                Main.items.remove(grenade);
            }
        }
    }

    public void addAmmo(Ammo ammo) {
        ArrayList<Ammo> ammos = Main.player.getInventory().getAmmos();
        for (int a = 0; a < ammos.size(); a++) {
            if (ammos.get(a) != null) {
                if (ammos.get(a).getType().equals(ammo.getType())) {
                    for (int i = 0; i < ammo.getAmount(); i++) {
                        if (ammos.get(a).getAmount() < ammos.get(a).getStackSize()) {
                            if (ammo.getAmount() > 0) {
                                ammos.get(a).add(1);
                                ammo.subtract(1);
                            }
                        }
                        if (ammo.getAmount() == 0) {
                            Main.items.remove(ammo);
                            return;
                        }
                    }
                }

            }
        }
        for (int a = 0; a < ammos.size(); a++) {
            if (ammos.get(a) == null) {
                ammos.set(a, ammo);
                ammos.get(a).setAmount(ammo.getPileAmount());
                Main.items.remove(ammo);
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