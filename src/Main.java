import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import javafx.scene.shape.Shape;

public class Main extends JPanel implements ActionListener, KeyListener, MouseMotionListener, MouseListener {
    // fields behind the actual game
    public static int screenwidth = 1000;
    public static int screenheight = 600;

    private static enum State {
        MENU, GAME, WON
    };

    private static State state = State.MENU;
    private static JFrame game;
    private static JFrame menu = new JFrame();
    private static int whichSong;
    public Timer timer;
    // objects of the actual game
    public static Player player;
    public static ArrayList<Weapon> weaponPool = new ArrayList<Weapon>();
    private Gun shotgun = new Gun(500, 500, 100, 100, 30, 30, "light", 3000, 1000, 4, .15, 10, 10, 100, "shotgun.png",
            30, 15);
    public static ArrayList<Ammo> ammoPool = new ArrayList<Ammo>();
    Ammo light = new Ammo(15, 300, "light", 15, "lightammo.png", 10, 10);
    public static ArrayList<Shape> objects = new ArrayList<Shape>();
    // public static ArrayList<Item> items = new ArrayList<Item>();
    public static HashMap<Point, Item> items = new HashMap<Point, Item>();

    @SuppressWarnings("unused")
    public static void main(String[] args) {
        if (state == State.MENU) {
            Main main = new Main();
        }

    }

    public Main() {
        // added JLables
        JLabel background = new JLabel(new ImageIcon("img/saucyshooterbackground.png"));
        JLabel title = new JLabel(new ImageIcon("img/saucyshooters.png"));
        JLabel music = new JLabel(new ImageIcon("img/saucymusic.png"));
        JLabel controls = new JLabel(new ImageIcon("img/controls.png"));
        JLabel settings = new JLabel(new ImageIcon("img/settings.png"));
        JButton playbutton = new JButton();
        JButton controlsbutton = new JButton();
        JButton settingsbutton = new JButton();
        JButton forkbutton = new JButton();
        JButton backbutton = new JButton();
        JButton music1 = new JButton("synthy beat");
        JButton music2 = new JButton("icy synth");
        JButton music3 = new JButton("chimes beat");
        backbutton.setVisible(false);
        controls.setVisible(false);
        settings.setVisible(false);
        music.setVisible(false);
        music1.setVisible(false);
        music2.setVisible(false);
        music3.setVisible(false);
        backbutton.setIcon(new ImageIcon("img/backbutton1.png"));
        forkbutton.setIcon(new ImageIcon("img/githublogo.png"));
        playbutton.setIcon(new ImageIcon("img/play1.png"));
        controlsbutton.setIcon(new ImageIcon("img/controls1.png"));
        settingsbutton.setIcon(new ImageIcon("img/settings1.png"));
        title.setBounds(screenwidth / 2 - 250, 10, 520, 250);
        background.setBounds(0, 0, screenwidth, screenheight);
        playbutton.setBounds(screenwidth / 2 - 100, 260, 200, 105);
        controlsbutton.setBounds(screenwidth / 2 - 180, 360, 350, 100);
        settingsbutton.setBounds(screenwidth / 2 - 180, 450, 350, 100);
        forkbutton.setBounds(950, 10, 40, 40);
        controls.setBounds(0, 0, screenwidth, screenheight);
        settings.setBounds(0, 0, screenwidth, screenheight);
        backbutton.setBounds(10, 10, 60, 60);
        music.setBounds(screenwidth / 2 - 200, 20, 400, 230);
        music1.setBounds(screenwidth / 2 - 80, 250, 160, 50);
        music2.setBounds(screenwidth / 2 - 80, 320, 160, 50);
        music3.setBounds(screenwidth / 2 - 80, 390, 160, 50);
        playbutton.setOpaque(false);
        playbutton.setContentAreaFilled(false);
        playbutton.setBorderPainted(false);
        backbutton.setOpaque(false);
        backbutton.setContentAreaFilled(false);
        backbutton.setBorderPainted(false);
        forkbutton.setContentAreaFilled(false);
        forkbutton.setOpaque(false);
        forkbutton.setBorderPainted(false);
        settingsbutton.setContentAreaFilled(false);
        settingsbutton.setOpaque(false);
        settingsbutton.setBorderPainted(false);
        controlsbutton.setContentAreaFilled(false);
        controlsbutton.setOpaque(false);
        controlsbutton.setBorderPainted(false);
        menu.add(music);
        menu.add(music1);
        menu.add(music2);
        menu.add(music3);
        menu.add(backbutton);
        menu.add(controls);
        menu.add(settings);
        menu.add(forkbutton);
        menu.add(controlsbutton);
        menu.add(settingsbutton);
        menu.add(playbutton);
        menu.add(title);
        menu.add(background);
        menu.pack();
        menu.setSize(screenwidth, screenheight);
        menu.setTitle("Saucy Shooters");
        menu.setResizable(false);
        menu.setLocationByPlatform(true);
        menu.setLayout(null);
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setVisible(true);
        // added actionlisteners to these Jbuttons for button pressing
        forkbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SoundEffect.buttonclick.play();
                openLink("https://github.com/MihirBafna/SaucyShooters");
            }
        });
        playbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SoundEffect.buttonclick.play();
                menu.setVisible(false);
                menu.dispose();
                state = State.GAME;
                startGame();
            }
        });
        controlsbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SoundEffect.buttonclick.play();
                playbutton.setVisible(false);
                forkbutton.setVisible(false);
                settingsbutton.setVisible(false);
                controls.setVisible(true);
                backbutton.setVisible(true);

            }
        });
        backbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SoundEffect.buttonclick.play();
                controls.setVisible(false);
                backbutton.setVisible(false);
                settings.setVisible(false);
                music1.setVisible(false);
                music2.setVisible(false);
                music3.setVisible(false);
                music.setVisible(false);
                playbutton.setVisible(true);
                forkbutton.setVisible(true);
                controlsbutton.setVisible(true);
                settingsbutton.setVisible(true);
            }
        });
        settingsbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SoundEffect.buttonclick.play();
                controls.setVisible(false);
                backbutton.setVisible(true);
                playbutton.setVisible(false);
                forkbutton.setVisible(false);
                settingsbutton.setVisible(false);
                controlsbutton.setVisible(false);
                music.setVisible(true);
                settings.setVisible(true);
                music1.setVisible(true);
                music2.setVisible(true);
                music3.setVisible(true);
            }
        });
        music1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SoundEffect.buttonclick.play();
                whichSong = 1;
            }
        });
        music2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SoundEffect.buttonclick.play();
                whichSong = 2;
            }
        });
        music3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SoundEffect.buttonclick.play();
                whichSong = 3;
            }
        });
    }

    public static void openLink(String url) {
        try {
            Desktop.getDesktop().browse(new URL(url).toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initializeWorld() {
        for (int i = 0; i < 3; i++) {
            Crate c = new Crate("img/Crate_100x100.png", 100, 100);
            c.setX(i * 200);
            c.setY(i * 200);
            items.put(new Point((int) c.getX(), (int) c.getY()), c);
        }
    }

    public void startGame() {
        game = new JFrame();
        JLabel background = new JLabel(new ImageIcon("img/saucyshooterbackground.png"));
        player = new Player(new ImageIcon("img/player1.png"), 100.0, 100.0, 100.0, 15.0, Color.CYAN);
        initializeWorld();
        game.add(player.getLabel());
        game.add(background);
        game.setTitle("Saucy Shooters");
        game.pack();
        game.setSize(screenwidth, screenheight);
        game.getContentPane();
        game.getContentPane().add(this);
        game.setResizable(false);
        game.setLocationByPlatform(true);
        game.setLayout(null);
        game.addKeyListener(this);
        game.addMouseListener(this);
        game.addMouseMotionListener(this);

        weaponPool.add(shotgun);
        ammoPool.add(light);
        objects.add(player.getCircle());
        // for (int i = 0; i < 2; i++) {
        // Crate c = new Crate("crate.png", 100, 100);
        // items.add(c);
        // }

        timer = new Timer(1000 / 60, this);
        timer.start();
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setVisible(true);
    }

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

    // override methods
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
        for (Point p : items.keySet()) {
            items.get(p).drawImage(g);
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
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        player.shoot(e.getX(), e.getY());

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
            player.gather();
        }

        if (e.getKeyCode() == e.VK_1) {
            player.setEquippedWeapon(player.getInventory().getGuns().get(0));
        }

        if (e.getKeyCode() == e.VK_O) {
            player.openCrate();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == e.VK_A || e.getKeyCode() == e.VK_D) {
            player.setVelX(0);
        }
        if (e.getKeyCode() == e.VK_W || e.getKeyCode() == e.VK_S) {
            player.setVelY(0);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}