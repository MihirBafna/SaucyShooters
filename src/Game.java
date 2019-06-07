import java.applet.Applet;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
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
import java.util.HashMap;
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

import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Game extends JPanel implements ActionListener, KeyListener, MouseListener, MouseMotionListener {

    // instantiating all objects and other variables
    public static int screenwidth = 1000;
    public static int screenheight = 600;
    public static int displayX = 0;
    public static int displayY = 0;

    public static Player player;

    public static ArrayList<Weapon> weaponPool = new ArrayList<Weapon>();
    public static ArrayList<Ammo> ammoPool = new ArrayList<Ammo>();
    public static ArrayList<Shape> objects = new ArrayList<Shape>();
    public static ArrayList<Item> items = new ArrayList<Item>();

    Image background = null;
    Image ammoGUI = null;
    Image playerImage = null;

    // public static HashMap<Point, Item> items = new HashMap<Point, Item>();

    // weapons
    private Gun shotgun = new Gun(500, 250, 100, 20, 12, 12, "light", 3000, 1000, 4, .15, 10, 20, 100, "shotgun.png",
            30, 15);
    private Gun AR = new Gun(900, 700, 33, 20, 30, 30, "light", 2000, 333, 1, 0, 10, 20, 100, "ar.png", 30, 15);

    // ammos
    private Ammo light = new Ammo(20, 300, "light", 15, "lightammo.png", 10, 10);

    // public static void main(String[] args) {
    //     Game game = new Game();
    // }

    public void initializeWorld() {
        for (int i = 0; i < 10; i++) {
            Crate c = new Crate(i * 200, i * 200, "Crate_100x100.png", 100, 100, i);
            // c.setX(i * 200);
            // c.setY(i * 200);
            items.add(c);
        }
        try {
            playerImage = ImageIO.read(new File("img/" + "NEWCHARACTER4.png"));
        } catch (IOException e) {
            System.out.println("ERROR");
            e.printStackTrace();
        }
        player = new Player("NEWCHARACTER4.png", Game.screenwidth / 2, Game.screenheight / 2, 3.0, 15.0, Color.GREEN);
    }

    public Game() {
        // making JFrame
        JFrame f = new JFrame();
        f.setTitle("Saucy Shooters");
        f.setSize(Game.screenwidth, Game.screenheight);
        f.getContentPane();
        // ImageIcon backg = new ImageIcon("img/map sketch.png"); // setups icon image
        // JLabel background = new JLabel(backg);
        // background.setBounds(-1920/2, -1080/2, 1920, 1080); // set location and size
        // of icon
        f.getContentPane().add(this);
        f.pack();
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setLayout(null);
        f.addKeyListener(this);
        f.addMouseMotionListener(this);
        f.addMouseListener(this);

        initializeWorld();
        // f.add(player.getLabel());
        weaponPool.add(shotgun);
        weaponPool.add(AR);
        ammoPool.add(light);
        // objects.add(player.getCircle());

        // end creating objects
        t = new Timer(10, this);
        t.start();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);

        // image = ImageIO.read(new File("img/ammoGUIA.gif"));
        background = new ImageIcon("img/bigmap.png").getImage();

        // image = ImageIO.read(new File("img/ammoGUIA.gif"));
        ammoGUI = new ImageIcon("img/ammoGUIA4.gif").getImage();

    }

    Timer t;

    public void update() {
        player.move();
        for (Gun gun : player.getInventory().getGuns()) {
            if (gun != null) {
                gun.update();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        update();
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        // paints images/font/progress bars
        super.paint(g);
        int buffer = 100;
        Rectangle screen = new Rectangle(-Game.displayX - buffer, -Game.displayY - buffer,
                Game.screenwidth + (buffer * 2), Game.screenheight + (buffer * 2));
        g.translate(Game.displayX, Game.displayY);
        
        g.drawImage(background, -1920 / 2, -1080 / 2, null);
        for (Item i : items) {
            i.setRectangle();
            if (GameObject.collision(i.getRectangle(), screen)) {
                i.drawImage(g);
            }
        }


        for (Gun gun : player.getInventory().getGuns()) {
            if (gun != null) {
                gun.drawBullets(g, player.getColor());
            }
        }
        for(int i = 0; i<Client.playerPos.size();i++){
            double x =Client.playerPos.get(i).getX();
            double y = Client.playerPos.get(i).getY();
            if(GameObject.collision(screen, new Circle(x,y, 50))&&i!=Client.clientnumber){
                Item n = new Item(x -50 , y- 50, "NEWCHARACTER4.png", 100, 100);
                n.drawImage(g);
            }
        }
        g.setColor(Color.MAGENTA);
        g.setColor(Color.black);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 18));
        // score and instructions

        g.translate(-Game.displayX, -Game.displayY);
        player.drawImage(g);
        g.setColor(Color.BLACK);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 50));
        if (player.getEquippedWeapon() instanceof Gun) {
            g.drawString(Integer.toString(((Gun) player.getEquippedWeapon()).getMagazine()), screenwidth - 290 - 10,
                    screenheight - 62);
            g.drawString(Integer.toString(player.getEquippedAmmo()), screenwidth - 197 - 10, screenheight - 62);
        }

        g.drawImage(ammoGUI, screenwidth - 320 - 10, screenheight - 150 - 10, null);

        if(player.getEquippedWeapon()!=null){
            player.getEquippedWeapon().drawImage(g, screenwidth-80 -10, screenheight -82);
        }

        repaint();

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // player.drawImage(g);
        // g.setColor(Color.BLACK);
        // g.setFont(new Font("TimesRoman", Font.PLAIN, 50));
        // if (player.getEquippedWeapon() instanceof Gun) {
        //     g.drawString(Integer.toString(((Gun) player.getEquippedWeapon()).getMagazine()), screenwidth - 290 - 10,
        //             screenheight - 62);
        //     g.drawString(Integer.toString(player.getEquippedAmmo()), screenwidth - 197 - 10, screenheight - 62);
        // }

        // g.drawImage(ammoGUI, screenwidth - 320 - 10, screenheight - 150 - 10, null);

        // g.setFont(new Font("TimesRoman", Font.PLAIN, 18));
        // ArrayList<Gun> guns = player.getInventory().getGuns();
        // if (guns.get(0) != null) {
        // g.drawString(guns.get(0).getImgName() + " ... ", 40, 550);
        // }
        // if (guns.get(1) != null) {
        // g.drawString(guns.get(1).getImgName(), 160, 550);
        // }
        // if (player.getEquippedWeapon() != null) {
        // g.drawString(player.getEquippedWeapon().getImgName(), 100, 580);
        // }
    }

    @Override
    public Dimension getPreferredSize() {
        // dimension of window
        return new Dimension(Game.screenwidth, Game.screenheight);
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
            player.reload();
        }

        if (e.getKeyCode() == e.VK_F) {
            player.gather();
        }

        if (e.getKeyCode() == e.VK_1) {
            player.setEquippedWeapon(player.getInventory().getGuns().get(0));
        }

        if (e.getKeyCode() == e.VK_2) {
            player.setEquippedWeapon(player.getInventory().getGuns().get(1));
        }

        int cycle = 0;
        if (e.getKeyCode() == e.VK_3) {
            player.setEquippedWeapon(player.getInventory().getGrenades().get(cycle));
            cycle++;
            if (cycle >= player.getInventory().getGrenades().size()) {
                cycle = 0;
            }
        }

        if (e.getKeyCode() == e.VK_O) {
           player.openCrate();
        }

    }

    @Override
    public void mouseDragged(MouseEvent arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseMoved(MouseEvent arg0) {
        // TODO Auto-generated method stub
        player.setTheta(arg0.getX() - displayX, arg0.getY() - displayY);
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {
        // TODO Auto-generated method stub
        player.shoot(arg0.getX() - displayX, arg0.getY() - displayY);
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
        // TODO Auto-generated method stub
        java.awt.Toolkit toolkit = java.awt.Toolkit.getDefaultToolkit();
        Image image = toolkit.getImage("img/CROSSHAIR_BOLD.png");
        Cursor a = toolkit.createCustomCursor(image, new Point(this.getX(), this.getY()), "");
        this.setCursor(a);
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mousePressed(MouseEvent arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
        // TODO Auto-generated method stub
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

}
