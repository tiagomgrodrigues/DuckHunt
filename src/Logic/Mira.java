package Logic;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Mira {

    private Point position;
    private int size = 10; // Tamanho da mira (diâmetro do círculo)
    private Color color = Color.RED; // Cor da mira0

    private final ImageIcon miraImage;

    public Mira() {
        this.position = new Point(0, 0);

        miraImage = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/images/mira/mira.png")));
        Image scaledImage = miraImage.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        miraImage.setImage(scaledImage);

    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public void draw(Graphics g) {
        g.drawImage(miraImage.getImage(), position.x - miraImage.getIconWidth() / 2, position.y - miraImage.getIconHeight() / 2, null);
    }
}