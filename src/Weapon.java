

public abstract class Weapon extends Item{

    private double dropRate;
    private boolean equipped;

    public Weapon(double dropRate) {
        super();
        this.dropRate = dropRate;
        equipped = false;
    }

    public Weapon(double dropRate, String imgName, double width, double height) {
        super(imgName, width, height);
        this.dropRate = dropRate;
        equipped = false;
    }

    public Weapon(double dropRate, double x, double y, String imgName, double width, double height) {
        super(x, y, imgName, width, height);
        this.dropRate = dropRate;
        equipped = false;
    }

    public abstract void shoot(double mouseX, double mouseY, double playerX, double playerY);
    public abstract void reload(Ammo ammo);

    public double getDropRate() {
        return this.dropRate;
    }

    public void setDropRate(double dropRate) {
        this.dropRate = dropRate;
    }

    public boolean isEquipped() {
        return this.equipped;
    }

    public void setEquipped(boolean equipped) {
        this.equipped = equipped;
    }

}