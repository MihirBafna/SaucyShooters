import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main implements ActionListener, KeyListener, MouseMotionListener {
    public static int screenwidth = 1000;
    public static int screenheight = 600;
    private static State state = State.MENU;
    private static enum State { MENU, GAME, WON};
    private JFrame screen;
    private JFrame menu;
    private int whichSong;


    @SuppressWarnings("unused")
    public static void main(String[] args) {
        if(state == State.MENU){
            Main main = new Main();
        }
    }

    public Main(){
        // added JLables
        JLabel background = new JLabel(new ImageIcon("images/nightbackground.png")); // image from
                                                                                     // https://www.vectorstock.com/royalty-free-vector/cartoon-game-background-vector-7926680
        JLabel title = new JLabel(new ImageIcon("images/saucysoccerlogo.png"));
        JLabel music = new JLabel(new ImageIcon("images/saucymusic.png"));
        JLabel controls = new JLabel(new ImageIcon("images/controls.png"));
        JLabel settings = new JLabel(new ImageIcon("images/settings.png"));
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
        backbutton.setIcon(new ImageIcon("images/backbutton1.png"));
        forkbutton.setIcon(new ImageIcon("images/githublogo.png"));
        playbutton.setIcon(new ImageIcon("images/playbutton.png"));
        controlsbutton.setIcon(new ImageIcon("images/controlsbutton.png"));
        settingsbutton.setIcon(new ImageIcon("images/settingsbutton.png"));
        title.setBounds(screenwidth / 2 - 200, 20, 400, 200);
        background.setBounds(0, 0, screenwidth, screenheight);
        playbutton.setBounds(screenwidth / 2 - 80, 240, 160, 80);
        controlsbutton.setBounds(screenwidth / 2 - 80, 340, 160, 80);
        settingsbutton.setBounds(screenwidth / 2 - 80, 440, 160, 80);
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
        menu.setTitle("Saucy Soccer");
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
                openLink("https://github.com/MihirBafna/SaucySoccer");
            }
        });
        playbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SoundEffect.buttonclick.play();
                menu.setVisible(false);
                menu.dispose();
                state = State.GAME;
                //startGame();
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

    public void openLink(String url) {
        try {
            Desktop.getDesktop().browse(new URL(url).toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // override methods
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
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}