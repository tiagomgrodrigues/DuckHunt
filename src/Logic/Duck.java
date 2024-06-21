package Logic;

import Graphic.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Duck {
    private int x, y;
    private double moveX = 8;
    private double moveY = 8;
    // Limites
    static int LEFT_LIMIT = -200;
    static int RIGHT_LIMIT = 200;
    static int TOP_LIMIT = -200;
    // Estado
    private DuckState state = DuckState.MOVE;
    // Arry de Imagens
    private Image duckImage;
    // Para alteração das imagens
    private static final int ANIMATION_DELAY = 100;
    private int currentFrame = 0;
    private long lastFrameTime = System.currentTimeMillis();

    // Array de imagens
    private final ImageIcon[] duckImagesRight;
    private final ImageIcon[] duckImagesLeft;
    private final ImageIcon[] duckImagesFalling;

    // GamePanel
    private GamePanel gamePanel;


    public Duck(GamePanel gamePanel, int x, int y) {
        this.gamePanel = gamePanel;
        this.x = x;
        this.y = y;


        duckImagesRight = new ImageIcon[]{
                new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/images/duck/up_right/sprite_0.png"))),
                new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/images/duck/up_right/sprite_1.png"))),
                new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/images/duck/up_right/sprite_2.png")))
        };

        duckImagesLeft = new ImageIcon[]{
                new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/images/duck/up_left/sprite_0.png"))),
                new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/images/duck/up_left/sprite_1.png"))),
                new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/images/duck/up_left/sprite_2.png")))
        };

        duckImagesFalling = new ImageIcon[]{
                new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/images/duck/death/0.png"))),
                new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/images/duck/death/1.png"))),
                new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/images/duck/death/2.png"))),
                new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/images/duck/death/3.png")))
        };


        for (ImageIcon image : duckImagesRight) {
            image.setImage(image.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
        }

        for (ImageIcon image : duckImagesLeft) {
            image.setImage(image.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
        }

        for (ImageIcon image : duckImagesFalling) {
            image.setImage(image.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
        }


    }

    public ImageIcon getCurrentDuckImage() {
        if (state == DuckState.FALLING) {
            return duckImagesFalling[currentFrame % duckImagesFalling.length];
        } else {
            return moveX > 0 ? duckImagesRight[currentFrame % duckImagesRight.length] : duckImagesLeft[currentFrame % duckImagesLeft.length];
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public void move(int panelWidth, int panelHeight, double speed) {

        if (state == DuckState.MOVE) {
            x += (int) (moveX * speed);
            y += (int) (moveY * speed);

            if (x < LEFT_LIMIT || x > panelWidth - getCurrentDuckImage().getIconWidth() + RIGHT_LIMIT) {
                moveX = -moveX;
                if (x < LEFT_LIMIT) {
                    x = LEFT_LIMIT;
                } else if (x > panelWidth - getCurrentDuckImage().getIconWidth() + RIGHT_LIMIT) {
                    x = panelWidth - getCurrentDuckImage().getIconWidth() + RIGHT_LIMIT;
                }
            }

            if (y < TOP_LIMIT || y > panelHeight - getCurrentDuckImage().getIconHeight()) {
                moveY = -moveY;
                if (y < TOP_LIMIT) {
                    y = TOP_LIMIT;
                } else if (y > panelHeight - getCurrentDuckImage().getIconHeight()) {
                    y = panelHeight - getCurrentDuckImage().getIconHeight();
                }
            }

            long currentTime = System.currentTimeMillis();
            if (currentTime - lastFrameTime > ANIMATION_DELAY) {
                currentFrame = (currentFrame + 1) % duckImagesRight.length;
                lastFrameTime = currentTime;
            }
        }

        if (state == DuckState.FALLING) {
            moveY = 10;
            y += moveY;

            long currentTime = System.currentTimeMillis();
            if (currentTime - lastFrameTime > ANIMATION_DELAY) {
                currentFrame = (currentFrame + 1) % duckImagesFalling.length;
                lastFrameTime = currentTime;
            }
        }

        System.out.println("Posição do pato: (" + x + ", " + y + ")");

    }

    public void setState(DuckState state) {
        this.state = state;
    }

    public enum DuckState {
        MOVE, FALLING
    }
}


