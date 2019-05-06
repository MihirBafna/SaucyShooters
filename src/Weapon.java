import java.util.ArrayList;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Weapon {

    private double x;
    private double y;
    private double range;
    private double rangeFallOff;
    private double damage;
    private double damageFallOff;
    private int magazine;
    private int magazineSize;
    private int ammo;
    public double reloadSpeed;
    private double fireSpeed;
    private int bulletsPerShot;
    private double bulletSpread;
    private double bulletSpeed;
    private double bulletSize;
    private ArrayList<Bullet> bullets = new ArrayList<Bullet>();

    private boolean shootable;
    private boolean reloading;
    private double shootTime1;
    private double shootTime2;
    private double reloadTime1;
    private double reloadTime2;

    public Weapon(double x, double y, double range, double rangeFallOff, double damage, double damageFallOff,
            int magazine, int magazineSize, int ammo, double reloadSpeed, double fireSpeed, int bulletsPerShot,
            double bulletSpread, double bulletSpeed, double bulletSize) {
        this.x = x;
        this.y = y;
        this.range = range;
        this.rangeFallOff = range;
        this.damage = damage;
        this.damageFallOff = damageFallOff;
        this.magazine = magazine;
        this.magazineSize = magazineSize;
        this.ammo = ammo;
        this.reloadSpeed = reloadSpeed;
        this.fireSpeed = fireSpeed;
        this.bulletsPerShot = bulletsPerShot;
        this.bulletSpread = bulletSpread;
        this.bulletSpeed = bulletSpeed;
        this.bulletSize = bulletSize;

        shootable = true;
        reloading = false;
        shootTime1 = 0;
        shootTime2 = System.currentTimeMillis();
        reloadTime1 = 0;
        reloadTime2 = System.currentTimeMillis();
    }

    public void shoot(double x, double y, Player player) {
        if (shootable) {
            shootable = false;
            if (!reloading) {
                double xDiff = x - player.getX();
                double yDiff = y - player.getY();
                ArrayList<Double> angles = new ArrayList<Double>();
                Double mouseAngle = Math.atan2(yDiff, xDiff);
                if (bulletsPerShot % 2 == 0) {
                    for (int i = 0; i < bulletsPerShot / 2; i++) {
                        angles.add(mouseAngle + bulletSpread / 2 + (i * bulletSpread));
                        angles.add(mouseAngle - bulletSpread / 2 - (i * bulletSpread));
                    }
                } else {
                    angles.add(mouseAngle);
                    for (int i = 1; i <= (bulletsPerShot - 1) / 2; i++) {
                        angles.add(mouseAngle + (i * bulletSpread));
                        angles.add(mouseAngle - (i * bulletSpread));
                    }
                }
                for (Double theta : angles) {
                    double bulletSpeedX = bulletSpeed * Math.cos(theta);
                    double bulletSpeedY = bulletSpeed * Math.sin(theta);
                    if (magazine > 0) {
                        Bullet b = new Bullet(player.getX(), player.getY(), range, rangeFallOff, damage, damageFallOff, bulletSpeedX, bulletSpeedY,
                                bulletSize);
                        bullets.add(b);
                        magazine--;
                    }
                }
            }
            final ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
            executor.schedule(new Runnable() {
                public void run() {
                    if (!reloading) {
                        shootable = true;
                    }
                }
            }, (long) fireSpeed, TimeUnit.MILLISECONDS);
        }
    }

    public void reload() {
        if (magazine != magazineSize) {
            if (!reloading) {
                shootable = false;
                reloading = true;
                final ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(2);
                executor.schedule(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = magazine; i < magazineSize; i++) {
                            if (ammo > 0) {
                                magazine++;
                                ammo--;
                            } else {
                                break;
                            }
                        }
                        shootable = true;
                        reloading = false;
                    }
                }, (long) reloadSpeed, TimeUnit.MILLISECONDS);
            }
        }
    }

    public void rangeDelete() {
        for (int i = 0; i < bullets.size(); i++) {
            if (bullets.get(i).expire()) {
                bullets.remove(i);
                i--;
            }
        }
    }

    public double getX() {
        return this.x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return this.y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getRange() {
        return this.range;
    }

    public void setRange(double range) {
        this.range = range;
    }

    public double getRangeFallOff() {
        return this.rangeFallOff;
    }

    public void setRangeFallOff(double rangeFallOff) {
        this.rangeFallOff = rangeFallOff;
    }

    public double getDamage() {
        return this.damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public double getDamageFallOff() {
        return this.damageFallOff;
    }

    public void setDamageFallOff(double damageFallOff) {
        this.damageFallOff = damageFallOff;
    }

    public int getMagazine() {
        return this.magazine;
    }

    public void setMagazine(int magazine) {
        this.magazine = magazine;
    }

    public int getMagazineSize() {
        return this.magazineSize;
    }

    public void setMagazineSize(int magazineSize) {
        this.magazineSize = magazineSize;
    }

    public int getAmmo() {
        return this.ammo;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    public double getReloadSpeed() {
        return this.reloadSpeed;
    }

    public void setReloadSpeed(double reloadSpeed) {
        this.reloadSpeed = reloadSpeed;
    }

    public double getFireSpeed() {
        return this.fireSpeed;
    }

    public void setFireSpeed(double fireSpeed) {
        this.fireSpeed = fireSpeed;
    }

    public int getbulletsPerShot() {
        return this.bulletsPerShot;
    }

    public void setbulletsPerShot(int bulletsPerShot) {
        this.bulletsPerShot = bulletsPerShot;
    }

    public double getBulletSpread() {
        return this.bulletSpread;
    }

    public void setBulletSpread(double bulletSpread) {
        this.bulletSpread = bulletSpread;
    }

    public double getBulletSpeed() {
        return this.bulletSpeed;
    }

    public void setBulletSpeed(double bulletSpeed) {
        this.bulletSpeed = bulletSpeed;
    }

    public double getBulletSize() {
        return this.bulletSize;
    }

    public void setBulletSize(double bulletSize) {
        this.bulletSize = bulletSize;
    }

    public ArrayList<Bullet> getBullets() {
        return this.bullets;
    }

    public void setBullets(ArrayList<Bullet> bullets) {
        this.bullets = bullets;
    }

    public boolean isShootable() {
        return this.shootable;
    }

    public void setShootable(boolean shootable) {
        this.shootable = shootable;
    }

    public boolean isReloading() {
        return this.reloading;
    }

    public void setReloading(boolean reloading) {
        this.reloading = reloading;
    }

    public double getShootTime1() {
        return this.shootTime1;
    }

    public void setShootTime1(double shootTime1) {
        this.shootTime1 = shootTime1;
    }

    public double getShootTime2() {
        return this.shootTime2;
    }

    public void setShootTime2(double shootTime2) {
        this.shootTime2 = shootTime2;
    }

    public double getReloadTime1() {
        return this.reloadTime1;
    }

    public void setReloadTime1(double reloadTime1) {
        this.reloadTime1 = reloadTime1;
    }

    public double getReloadTime2() {
        return this.reloadTime2;
    }

    public void setReloadTime2(double reloadTime2) {
        this.reloadTime2 = reloadTime2;
    }

}