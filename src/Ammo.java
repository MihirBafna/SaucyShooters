public class Ammo extends Item{

    private int amount;
    private int pileAmount;
    private int stackSize;
    private String type;
    private double dropRate;

    /**
    * @param x
    * @param y
    * @param ammo
    * creats a clone of the ammo at point x,y
    */
    public Ammo(double x, double y, Ammo ammo) {
        super(x, y, ammo.getImgName(), ammo.getWidth(), ammo.getHeight());
        this.amount = ammo.amount;
        this.pileAmount = ammo.pileAmount;
        this.stackSize = ammo.stackSize;
        this.type = ammo.type;
        this.dropRate = ammo.dropRate;
    }
    
    /**
     * @param pileAmount
     * @param stackSize
     * @param type
     * @param dropRate
     * @param imgName
     * @param width
     * @param height
     */
    public Ammo(int pileAmount, int stackSize, String type, double dropRate, String imgName, double width, double height) {
        super(imgName, width, height);
        this.pileAmount = pileAmount;
        this.stackSize = stackSize;
        this.type = type;
        this.dropRate = dropRate;
    }

    /**
     * @param num
     * increases amount of ammo by num
     */
    public void add(int num){
        amount += num;
    }

    /**
     * @param num
     * decreases amount of ammo by num
     */
    public void subtract(int num){
        amount -= num;
    }

    public int getAmount() {
        return this.amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPileAmount() {
        return this.pileAmount;
    }

    public void setPileAmount(int pileAmount) {
        this.pileAmount = pileAmount;
    }

    public int getStackSize() {
        return this.stackSize;
    }

    public void setStackSize(int stackSize) {
        this.stackSize = stackSize;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getDropRate() {
        return this.dropRate;
    }

    public void setDropRate(double dropRate) {
        this.dropRate = dropRate;
    }

}