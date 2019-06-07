
public abstract class Weapon extends Item {

    private double dropRate;
    private boolean equipped;

    public Weapon() {
        super();
    }

    /**
     * @param dropRate
     */
    public Weapon(double dropRate) {
        super();
        this.dropRate = dropRate;
        equipped = false;
    }

    /**
     * @param dropRate
     * @param imgName
     * @param width
     * @param height
     */
    public Weapon(double dropRate, String imgName, double width, double height) {
        super(imgName, width, height);
        this.dropRate = dropRate;
        equipped = false;
    }

    /**
     * @param dropRate
     * @param x
     * @param y
     * @param imgName
     * @param width
     * @param height
     */
    public Weapon(double dropRate, double x, double y, String imgName, double width, double height) {
        super(x, y, imgName, width, height);
        this.dropRate = dropRate;
        equipped = false;
    }

    /**
     * @param mouseX
     * @param mouseY
     * @param playerX
     * @param playerY
     */
    public abstract void shoot(double mouseX, double mouseY, double playerX, double playerY);

    /**
     * @param ammo
     */
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