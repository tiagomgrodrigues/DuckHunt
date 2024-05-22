package Logic;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Duck {
    private int x, y;
    private Image duckImage;


    public Duck(int x, int y) {
        this.x = x;
        this.y = y;
        this.duckImage = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/images/duck/up/sprite_0.png"))).getImage();

        if (this.duckImage == null) {
            System.out.println("Erro ao carregar a imagem Duck!");
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getDuckImage() {
        return duckImage;
    }
}


