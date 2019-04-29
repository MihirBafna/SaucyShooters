public class Player{
    private double x;
    private double y;
    private double PXVel;
    private double PYVel;

    public Player(){
        this.x = (Main.screenwidth)/2;
        this.y = (Main.screenheight)/2;
    }
    
    public double getX(){
        return this.x;
    }

    public double getY(){
        return this.y;
    }

    public double getXVel() {
        return this.PXVel;
    }

    public double getYVel() {
        return this.PYVel;
    }
}