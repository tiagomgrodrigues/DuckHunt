package Logic;

import Graphic.GamePanel;
import Logic.Duck;


import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Graphic.GamePanel;

import javax.swing.*;

public class GameManager {

    private final List<Duck> ducks = new ArrayList<>();
    private final Random random = new Random();
    private final Hunt hunt;
    private final GamePanel gamePanel;

    public GameManager(GamePanel gamePanel, Hunt hunt) {
        this.hunt = hunt;
        this.gamePanel = gamePanel;
        createDucks(hunt.getLevel());
        registerMouseListener();
    }

    public List<Duck> getDucks() {
        return ducks;
    }

    public void update() {
        for (Duck duck : ducks) {
            duck.move(gamePanel.getWidth(), gamePanel.getHeight(), 1);
            if (duck.getY() > gamePanel.getHeight()) {
                removeDuck(duck);
            }
        }
    }

    public void createDucks(int numDucks) {
        for (int i = 0; i < numDucks; i++) {
            // Para tela com tamanho variavel
            //int randomX = random.nextInt(gamePanel.getWidth() - 60);
            int randomX = random.nextInt(1220);
            int randomY = -100; // Posição Y inicial fora da tela (acima)
            Duck newDuck = new Duck(gamePanel, randomX, randomY);
            ducks.add(newDuck);
            System.out.println("Pato a ser adicionado!");
        }
    }

    public void checkCollisionsAndRemoveDucks() {
        for (Duck duck : ducks) {
            if (checkCollision(duck)) {
                System.out.println("Pato atingido!");
                hunt.adicionarPontos(1);
                gamePanel.scoreLabel.setText("Score: " + hunt.getPontos());
                duck.setState(Duck.DuckState.FALLING);
            }
            break;
        }
    }

    public boolean checkCollision(Duck duck) {
        Rectangle duckBounds = new Rectangle(duck.getX(), duck.getY(),
                duck.getCurrentDuckImage().getIconWidth(),
                duck.getCurrentDuckImage().getIconHeight());

        return duckBounds.contains(gamePanel.mira.getPosition());
    }

    public void removeDuck(Duck duck) {
        ducks.remove(duck);
        gamePanel.repaint();
    }

    public void registerMouseListener() {
        gamePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    checkCollisionsAndRemoveDucks();
                }
            }
        });
    }

}
