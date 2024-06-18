package Graphic;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    private StartPanel startPanel;
    private GamePanel gamePanel;
    private CardLayout cardLayout;

    public GameFrame() {
        setTitle("Duck Hunt");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        startPanel = new StartPanel(this);
        add(startPanel);

        setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));

    }

    public void startGame() {
        System.out.println("Inicando o jogo...");
        remove(startPanel);
        gamePanel = new GamePanel();
        add(gamePanel);

        revalidate();
        repaint();
    }
}
