
public class Grenade extends Weapon {

    private double radius1;
    private double radius2;
    private double r1Damage;
    private double r2Damage;
    private double explosionDelay;
    private double range;
    private double throwSpeed;
    private double dropRate;
    private boolean throwable;

    public Grenade(double x, double y, Grenade grenade){
        super(grenade.dropRate);
        this.radius1 = grenade.radius1;
        this.radius2 = grenade.radius2;
        this.r1Damage = grenade.r1Damage;
        this.r2Damage = grenade.r2Damage;
        this.explosionDelay = grenade.explosionDelay;
        this.range = grenade.range;
        this.throwSpeed = grenade.throwSpeed;

        throwable = true;
    }

    public Grenade(double radius1, double radius2, double r1Damage, double r2Damage, double explosionDelay, double range, double throwSpeed, double dropRate) {
        super(dropRate);
        this.radius1 = radius1;
        this.radius2 = radius2;
        this.r1Damage = r1Damage;
        this.r2Damage = r2Damage;
        this.explosionDelay = explosionDelay;
        this.range = range;
        this.throwSpeed = throwSpeed;

        throwable = true;
    }

    // work in progress
    // create circle after delay
    // if player is in circle, takes damage
    public void shoot(double mouseX, double mouseY, double playerX, double playerY) {
    //     if (throwable) {
    //         throwable = false;
    //         if (!reloading) {
    //             double xDiff = mouseX - playerX;
    //             double yDiff = mouseY - playerY;
    //             Double theta = Math.atan2(yDiff, xDiff);
    //             double grenadeSpeedX = grenadeSpeed * Math.cos(theta);
    //             double grenadeSpeedY = grenadeSpeed * Math.sin(theta);
    //             Bullet b = new Bullet(playerX, playerY, range, rangeFallOff, damage, damageFallOff, grenadeSpeedX,
    //                     grenadeSpeedY, bulletSize);
    //         }
    //     }
    //     final ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
    //     executor.schedule(new Runnable() {
    //         public void run() {
    //             if (equipped) {
    //                 if (!reloading) {
    //                     throwable = true;
    //                 }
    //             }
    //         }
    //     }, (long) fireSpeed, TimeUnit.MILLISECONDS);
    // }

    }

    public void reload(Ammo ammo) {
        return;
    }

}