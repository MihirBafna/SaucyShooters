import java.util.ArrayList;

public class Weapon {

    private double range;
    private double rangeFallOff;
    private double damage;
    private double damageFallOff;
    private double bulletSpeed;
    private double bulletSize;
    private ArrayList<Bullet> bullets = new ArrayList<Bullet>();

    public Weapon(double range, double rangeFallOff, double damage, double damageFallOff, double bulletSpeed, double bulletSize) {
        this.range = range;
        this.rangeFallOff = range;
        this.damage = damage;
        this.damageFallOff = damageFallOff;
        this.bulletSpeed = bulletSpeed;
        this.bulletSize = bulletSize;
    }

    public void shoot(double x, double y, Player player) {
        double xDiff = x - player.getX();
        double yDiff = y - player.getY();
        // double xDiffAbs = Math.abs(xDiff);
        // double yDiffAbs = Math.abs(yDiff);
        double angle = Math.atan2(yDiff,xDiff);
        double bulletSpeedX = bulletSpeed * Math.cos(angle);
        double bulletSpeedY = bulletSpeed * Math.sin(angle);

        Bullet b = new Bullet(player.getX(), player.getY(), range, bulletSpeedX, bulletSpeedY, bulletSize);
        bullets.add(b);
    }

    public void rangeDrop() {
        for(int i=0;i<bullets.size();i++){
            if(bullets.get(i).expire()){
                bullets.remove(i);
                i--;
            }
        }

    }

}
