package Graphic;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

import Logic.Duck;

public class GamePanel extends JPanel {
    private Duck duck;

    // Imagens de fundo do jogo
    private final Image backgroundSky;
    private final Image background;

    public GamePanel() {
        duck = new Duck(100, 100);

        backgroundSky = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/images/cenario/cenariofrente.png"))).getImage();
        background = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/images/cenario/cenariotras.png"))).getImage();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (backgroundSky != null) {
            g.drawImage(backgroundSky, 0, 0, getWidth(), getHeight(), this);
        }

        if (background != null) {
            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        }

        // Desenha o pato
        if (duck.getDuckImage() != null) {
            g.drawImage(duck.getDuckImage(), duck.getX(), duck.getY(), this);
        } else {
            System.err.println("Erro ao carregar a imagem do pato");
        }
    }
}