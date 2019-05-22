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

    public void addAmmo(Ammo ammo){

    }

    public void addGrenade(Grenade grenade){

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