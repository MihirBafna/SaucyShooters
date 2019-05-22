import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Area;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import javafx.scene.shape.Shape;

public class Driver extends JPanel implements ActionListener, KeyListener, MouseListener, MouseMotionListener {

	// instantiating all objects and other variables
	String bg = "background.png";
	JLabel background;
	int screen_width = 1920;
	int screen_height = 1080;
	int score = 0;

	Player player = new Player(null, 200, 100, 5, 100, Color.GREEN);

	ArrayList<Weapon> weaponPool = new ArrayList<Weapon>();
	Gun shotgun = new Gun(500, 500, 100, 100, 30, 30, "light", 3000, 1000, 4, .15, 10, 10, 100, "shotgun.png", 30, 15);

	ArrayList<Ammo> ammoPool = new ArrayList<Ammo>();
	Ammo light = new Ammo(15, 300, "light", 15, "lightammo.png", 10, 10);

	ArrayList<Shape> objects = new ArrayList<Shape>();

	ArrayList<Item> items = new ArrayList<Item>();

	public static void main(String[] args) {
		Driver d = new Driver();
	}

	public Driver() {
		// making JFrame
		JFrame f = new JFrame();
		f.setTitle("Ratatouille");
		f.setSize(screen_width, screen_height);
		f.getContentPane();
		// String src = new File("").getAbsolutePath() + "/src/"; // path to image
		// // setup
		// ImageIcon backg = new ImageIcon(src + bg); // setups icon image
		// background = new JLabel(backg);
		// background.setBounds(0, 0, 600, 600); // set location and size of icon
		// // f.add(background);
		f.getContentPane().add(this);
		f.pack();
		f.setResizable(false);
		f.setLocationRelativeTo(null);
		f.setLayout(null);
		f.addKeyListener(this);
		f.addMouseMotionListener(this);
		f.addMouseListener(this);

		weaponPool.add(shotgun);
		ammoPool.add(light);
		objects.add(player.getCircle());
		for (int i = 0; i < 2; i++) {
			Crate c = new Crate(weaponPool, ammoPool, objects, "crate.png", 100, 100);
			items.add(c);
		}

		// end creating objects
		t = new Timer(10, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}

	Timer t;

	public void update() {
		player.move();
		for (Gun gun : player.getInventory().getGuns()) {
			if (gun != null) {
				gun.update();
			}
		}

		// w.rangeDelete();
		// for (Bullet b : w.getBullets()) {
		// b.move();
		// }
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		update();
		repaint();

	}

	@Override
	public void paint(Graphics g) {
		// paints images/font/progress bars
		super.paint(g);
		player.draw(g);
		for (Item i : items) {
			i.drawImage(g);
		}

		for (Gun gun : player.getInventory().getGuns()) {
			if (gun != null) {
				gun.drawBullets(g, player.getColor());
			}
		}

		player.draw(g);
		// g.fillRect(0, 0, 50, 50);
		g.setColor(Color.black);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 18));
		// score and instructions
		g.drawString("Read the console for instructions", 400, 790);
		if (player.getInventory().getAmmos().get(0) != null) {
			g.drawString(Integer.toString(player.getInventory().getAmmos().get(0).getAmount()), 400, 500);
		}
		if (player.getEquippedWeapon() != null) {
			if (player.getEquippedWeapon() instanceof Gun) {
				int magazine = ((Gun) player.getEquippedWeapon()).getMagazine();
				int magazineSize = ((Gun) player.getEquippedWeapon()).getMagazineSize();
				g.drawString(Integer.toString(magazine) + " / " + Integer.toString(magazineSize), 800, 800);
			}
		}
		g.setFont(new Font("TimesRoman", Font.PLAIN, 30));

		repaint();

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// paints stationary images like background and inventory
		// Image img = null;
		// try {
		// img = ImageIO.read(new File("background.png"));
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// Image inventory = null;
		// try {
		// inventory = ImageIO.read(new File("inventory.png"));
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// g.drawImage(inventory, 480, 0, null);
		// g.drawImage(img, 0, 0, null);

	}

	@Override
	public Dimension getPreferredSize() {
		// dimension of window
		return new Dimension(screen_width, screen_height);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// movement
		// changes velocity based on key pressed
		if (e.getKeyCode() == e.VK_A) {
			player.setVelX(-player.getSpeed());
		} else if (e.getKeyCode() == e.VK_D) {
			player.setVelX(player.getSpeed());
		}
		if (e.getKeyCode() == e.VK_W) {
			player.setVelY(-player.getSpeed());
		} else if (e.getKeyCode() == e.VK_S) {
			player.setVelY(player.getSpeed());
		}

		if (e.getKeyCode() == e.VK_R) {
			System.out.println();
			System.out.println("RELOADING");
			player.reload();
		}

		if (e.getKeyCode() == e.VK_F) {
			gather();
		}

		if (e.getKeyCode() == e.VK_1) {
			player.setEquippedWeapon(player.getInventory().getGuns().get(0));
		}

		if (e.getKeyCode() == e.VK_O) {
			openCrate();
		}

	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		player.shoot(arg0.getX(), arg0.getY());
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// more movement
		// changes velocity based on key pressed
		// animation change/idle position
		if (arg0.getKeyCode() == arg0.VK_A || arg0.getKeyCode() == arg0.VK_D) {
			player.setVelX(0);
		}
		if (arg0.getKeyCode() == arg0.VK_W || arg0.getKeyCode() == arg0.VK_S) {
			player.setVelY(0);
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void openCrate() {
		for (Item c : items) {
			if (c instanceof Crate) {
				if (GameObject.collision(player.getCircle(), c.getRectangle())) {
					items.add(((Crate) c).generateWeapon());
					items.add(((Crate) c).generateAmmo());
					objects.remove(c.getRectangle());
					items.remove(c);
					break;
				}
			}
		}
	}

	public void gather() {
		for (Item i : items) {
			if (GameObject.collision(player.getCircle(), i.getRectangle())) {
				if (i instanceof Gun) {
					addGun((Gun) i);
					return;
				}
				if (i instanceof Grenade) {
					addGrenade((Grenade) i);
					return;
				}
				if (i instanceof Ammo) {
					addAmmo((Ammo) i);
					return;
				}
			}
		}
	}

	public void addGun(Gun gun) {
		ArrayList<Gun> guns = player.getInventory().getGuns();
		for (int g = 0; g < guns.size(); g++) {
			if (guns.get(g) == null) {
				guns.set(g, gun);
				guns.get(g).setEquipped(true);
				items.remove(gun);
				return;
			}
		}
		for (int g = 0; g < guns.size(); g++) {
			if (guns.get(g).isEquipped()) {
				guns.get(g).setEquipped(false);
				items.add(guns.get(g));
				guns.set(g, gun);
				guns.get(g).setEquipped(true);
				items.remove(gun);
				return;
			}
		}
	}

	public void addGrenade(Grenade grenade) {
		ArrayList<Grenade> grenades = player.getInventory().getGrenades();
		for (int g = 0; g < grenades.size(); g++) {
			if (grenades.get(g) == null) {
				grenades.set(g, grenade);
				grenades.get(g).setEquipped(false);
				items.remove(grenade);
				return;
			}
		}
		for (int g = 0; g < grenades.size(); g++) {
			if (grenades.get(g).isEquipped()) {
				grenades.get(g).setEquipped(false);
				items.add(grenades.get(g));
				grenades.set(g, grenade);
				grenades.get(g).setEquipped(true);
				items.remove(grenade);
			}
		}
	}

	public void addAmmo(Ammo ammo) {
		ArrayList<Ammo> ammos = player.getInventory().getAmmos();
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
							items.remove(ammo);
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
				items.remove(ammo);
			}
		}
	}

}
