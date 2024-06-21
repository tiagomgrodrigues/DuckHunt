package Graphic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
// Classes do jogo
import Logic.Duck;
import Logic.GameManager;
import Logic.Hunt;
import Logic.Mira;

public class GamePanel extends JPanel {
    private Hunt hunt;
    private Timer timer;
    public JLabel scoreLabel;
    public Mira mira;

    // Imagens de fundo
    private final Image backgroundSky;
    private final Image background;

    // Random
    private Random random = new Random();

    // GameManager
    private GameManager gameManager;

    public GamePanel() {
        // Criação do Hunt
        hunt = new Hunt();
        // Criação da Mira
        mira = new Mira();

        // Score
        scoreLabel = new JLabel("Score: " + hunt.getPontos());
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 24));
        scoreLabel.setForeground(Color.WHITE);
        add(scoreLabel);
        // Backgrounds
        background = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/images/cenario/cenariofrente.png"))).getImage();
        backgroundSky = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/images/cenario/cenariotras.png"))).getImage();

        gameManager = new GameManager(this, hunt);
        gameManager.registerMouseListener();

        // Sinconismo para os Frames
        timer = new Timer(30, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                for (Duck duck : gameManager.getDucks()) {
                    duck.move(getWidth(), getHeight(), 1);
                }

                repaint();
            }
        });
        timer.start();


        // Movimento da Mira
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                if (!SwingUtilities.isLeftMouseButton(e)) {
                    mira.setPosition(e.getPoint());
                    repaint();
                }
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e))
                    mira.setPosition(e.getPoint());
                repaint();
            }

        });

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Desenhar o Score
        scoreLabel.setBounds(getWidth() - scoreLabel.getPreferredSize().width - 10,
                getHeight() - scoreLabel.getPreferredSize().height - 10,
                scoreLabel.getPreferredSize().width,
                scoreLabel.getPreferredSize().height);

        // Desenha o background
        if (backgroundSky != null) {
            g.drawImage(backgroundSky, 0, 0, getWidth(), getHeight(), this);
        }

        for (Duck duck : gameManager.getDucks()) {
            g.drawImage(duck.getCurrentDuckImage().getImage(), duck.getX(), duck.getY(), this);
        }


        // Desenha o background
        if (background != null) {
            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        }

        mira.draw(g);

    }
}