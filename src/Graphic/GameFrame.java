package Graphic;

import javax.swing.*;

public class GameFrame extends JFrame {
    public GameFrame() {
        setTitle("Duck Hunt");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(new GamePanel());
    }
}
