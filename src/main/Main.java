package main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Main {
    public static void main(String[] args) {


        JFrame window = new JFrame();
        GamePanel gamepanel = new GamePanel();

        ImageIcon icon = new ImageIcon("res/tiles/server-icon.png");

        window.setIconImage(icon.getImage());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("BirbVill");
        window.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));

        window.setLocationRelativeTo(null);
        window.setVisible(true);

       window.add(gamepanel);

       window.pack();

       gamepanel.gameSetUp();
       gamepanel.startGameThread();



        System.out.println("Hello world!");
    }
}