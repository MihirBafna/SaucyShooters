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

	public ArrayList<Shape> objects = new ArrayList<Shape>();

	public ArrayList<Item> items = new ArrayList<Item>();

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

	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
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
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}



}
