package Graphic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import Logic.Duck;
import Logic.Hunt;
import Logic.Mira;

public class GamePanel extends JPanel {
    private Hunt hunt;
    private Timer timer;
    private Mira mira;
    private JLabel scoreLabel;
    // Lista de Ducks
    private List<Duck> ducks;

    // Imagens de fundo
    private final Image backgroundSky;
    private final Image background;

    // Random
    private Random random = new Random();

    public GamePanel() {
        // Criação do Hunt
        hunt = new Hunt();
        // Criação da Mira
        mira = new Mira();
        // Lista de Ducks
        ducks = new ArrayList<>();
        addDuck();


        // Score
        scoreLabel = new JLabel("Score: " + hunt.getPontos());
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 24));
        scoreLabel.setForeground(Color.WHITE);
        add(scoreLabel);

        // Backgrounds
        background = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/images/cenario/cenariofrente.png"))).getImage();
        backgroundSky = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/images/cenario/cenariotras.png"))).getImage();

        timer = new Timer(30, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Duck duck : ducks) {
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

        // Tiro
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("Mouse pressionado");
                System.out.println(ducks.size());
                for (Duck duck : ducks) {
                    if (checkCollision(duck)) {
                        System.out.println("Pato atingido!");
                        hunt.adicionarPontos(1);
                        scoreLabel.setText("Score: " + hunt.getPontos());
                        duck.setState(Duck.DuckState.FALLING);
                        break;
                    }
                }
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

        if (ducks.isEmpty() && getWidth() > 0) {
            addDuck();
        }

        // Desenha todos os patos na lista
        for (Duck duck : ducks) {
            g.drawImage(duck.getCurrentDuckImage().getImage(), duck.getX(), duck.getY(), this);
        }


        // Desenha o background
        if (background != null) {
            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        }

        mira.draw(g);

    }

    private boolean checkCollision(Duck duck) {
        Rectangle duckBounds = new Rectangle(
                duck.getX(),
                duck.getY(),
                duck.getCurrentDuckImage().getIconWidth(),
                duck.getCurrentDuckImage().getIconHeight());

        return duckBounds.contains(mira.getPosition());
    }

    private void addDuck() {
        int numPatos = hunt.getLevel();
        for (int i = 0; i < numPatos; i++) {
            int panelWidth = getWidth();
            if (panelWidth > 0) { // Verifica se a largura do painel é positiva
                int randomX = random.nextInt(panelWidth - 60);
                int randomY = -100;
                Duck newDuck = new Duck(this, randomX, randomY);
                ducks.add(newDuck);
            } else {
                System.err.println("Largura do painel inválida ao criar pato."); // Mensagem de erro
            }
        }
    }

    public void removeDuck(Duck duck) {
        ducks.remove(duck);
        repaint();
    }
}