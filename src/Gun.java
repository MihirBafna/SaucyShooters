
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javafx.scene.shape.Circle;

public class Gun extends Weapon {

    private double range;
    private double rangeFallOff;
    private double damage;
    private double damageFallOff;
    private int magazine;
    private int magazineSize;
    private String ammoType;
    public double reloadSpeed;
    private double fireSpeed;
    private int bulletsPerShot;
    private double bulletSpread;
    private double bulletSpeed;
    private double bulletSize;

    public ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    private boolean shootable;
    private boolean reloading;

    /**
     * @param x
     * @param y
     * @param gun
     * clones gun and gives pos x,y
     */
    public Gun(double x, double y, Gun gun) {
        super(gun.getDropRate(), x, y, gun.getImgName(), gun.getWidth(), gun.getHeight());
        this.range = gun.range;
        this.rangeFallOff = gun.range;
        this.damage = gun.damage;
        this.damageFallOff = gun.damageFallOff;
        this.magazine = gun.magazine;
        this.magazineSize = gun.magazineSize;
        this.ammoType = gun.ammoType;
        this.reloadSpeed = gun.reloadSpeed;
        this.fireSpeed = gun.fireSpeed;
        this.bulletsPerShot = gun.bulletsPerShot;
        this.bulletSpread = gun.bulletSpread;
        this.bulletSpeed = gun.bulletSpeed;
        this.bulletSize = gun.bulletSize;

        shootable = true;
        reloading = false;
    }

    /**
     * @param range
     * @param rangeFallOff
     * @param damage
     * @param damageFallOff
     * @param magazine
     * @param magazineSize
     * @param ammoType
     * @param reloadSpeed
     * @param fireSpeed
     * @param bulletsPerShot
     * @param bulletSpread
     * @param bulletSpeed
     * @param bulletSize
     * @param dropRate
     * @param imgName
     * @param width
     * @param height
     */
    public Gun(double range, double rangeFallOff, double damage, double damageFallOff, int magazine, int magazineSize,
            String ammoType, double reloadSpeed, double fireSpeed, int bulletsPerShot, double bulletSpread,
            double bulletSpeed, double bulletSize, double dropRate, String imgName, double width, double height) {
        super(dropRate, imgName, width, height);
        this.range = range;
        this.rangeFallOff = range;
        this.damage = damage;
        this.damageFallOff = damageFallOff;
        this.magazine = magazine;
        this.magazineSize = magazineSize;
        this.ammoType = ammoType;
        this.reloadSpeed = reloadSpeed;
        this.fireSpeed = fireSpeed;
        this.bulletsPerShot = bulletsPerShot;
        this.bulletSpread = bulletSpread;
        this.bulletSpeed = bulletSpeed;
        this.bulletSize = bulletSize;

        shootable = true;
        reloading = false;
    }

    /**
     * @param mouseX
     * @param mouseY
     * @param playerX
     * @param playerY
     * creates bullets based on mouse position and player position
     */
    public void shoot(double mouseX, double mouseY, double playerX, double playerY) {
        if (shootable) {
            shootable = false;
            if (!reloading) {
                double xDiff = mouseX - playerX;
                double yDiff = mouseY - playerY;
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
                        Bullet b = new Bullet(playerX, playerY, range, rangeFallOff, damage, damageFallOff,
                                bulletSpeedX, bulletSpeedY, bulletSize);
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

    /**
     * @param ammo
     * reloads gun after certain delay
     */
    public void reload(Ammo ammo) {
        if (ammo.getType().equals(ammoType)) {
            if (magazine < magazineSize) {
                if (!reloading) {
                    shootable = false;
                    reloading = true;
                    final ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(2);
                    executor.schedule(new Runnable() {
                        @Override
                        public void run() {
                            if (isEquipped()) {
                                for (int i = magazine; i < magazineSize; i++) {
                                    if (ammo.getAmount() > 0) {
                                        magazine++;
                                        ammo.subtract(1);
                                    } else {
                                        break;
                                    }
                                }
                            }
                            shootable = true;
                            reloading = false;
                        }
                    }, (long) reloadSpeed, TimeUnit.MILLISECONDS);
                }
            }
        }
    }

    /**
     * removes bullets from arraylist if they expire
     */
    public void deleteBullets() {
        for (int i = 0; i < bullets.size(); i++) {
            if (bullets.get(i).expire()) {
                bullets.remove(i);
                i--;
            }
        }
    }

    /**
     * moves all bullets in arraylist
     */
    public void moveBullets() {
        for (Bullet b : bullets) {
            b.move();
        }
    }

    /**
     * @param g
     * @param c
     * draws all bullets in arraylist
     */
    public void drawBullets(Graphics g, Color c) {
        for (Bullet b : bullets) {
            b.draw(g, c);
        }
    }

    /**
     * updates bullets in arraylist
     */
    public void update() {
        deleteBullets();
        moveBullets();
        // for (Bullet b : bullets) {
        //     for(int i = 0; i<Client.playerPos.size();i++){
        //         double x =Client.playerPos.get(i).getX();
        //         double y = Client.playerPos.get(i).getY();
        //         if(GameObject.collision(b.getCircle(), new Circle(x,y, 50))&&i!=Client.clientnumber){
        //             Client.deadPlayers.add(i);
        //         }
        //     }
        // }
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

    public String getAmmoType() {
        return this.ammoType;
    }

    public void setAmmoType(String ammoType) {
        this.ammoType = ammoType;
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

}