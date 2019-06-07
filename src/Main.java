import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
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

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Main extends JPanel implements ActionListener, KeyListener, MouseMotionListener, MouseListener {
    // fields behind the actual game
    public static int screenwidth = 1000;
    public static int screenheight = 600;

    private static enum State {
        MENU, GAME, WON
    };

    private static State state = State.MENU;
    // public static JFrame game;
    private static JFrame menu = new JFrame();
    private static int whichSong;
    // objects of the actual game
    // public static Player player;
    // public static ArrayList<Weapon> weaponPool = new ArrayList<Weapon>();
    // private Gun shotgun = new Gun(500, 500, 100, 100, 30, 30, "light", 3000, 1000, 4, .15, 10, 10, 100, "shotgun.png",
    //         30, 15);
    // public static ArrayList<Ammo> ammoPool = new ArrayList<Ammo>();
    // Ammo light = new Ammo(15, 300, "light", 15, "lightammo.png", 10, 10);
    // public static ArrayList<Shape> objects = new ArrayList<Shape>();
    // public static ArrayList<Item> items = new ArrayList<Item>();
    // public static HashMap<Point, Item> items = new HashMap<Point, Item>();

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
                // SoundEffect.buttonclick.play();
                menu.setVisible(false);
                menu.dispose();
                state = State.GAME;
                Client client  = new Client();
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


    // override methods
    @Override
    public void actionPerformed(ActionEvent e) {

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

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

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