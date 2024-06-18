package Logic;

import javax.swing.*;

public class Hunt {
    private int vidas;
    private int pontos;
    private int level;

    public Hunt() {
        this.vidas = 3;
        this.pontos = 0;
        this.level = 1;

    }

    public int getVidas() {
        return vidas;
    }

    public int getPontos() {
        return pontos;
    }

    public int getLevel() {
        return level;
    }

    public void adicionarPontos(int pontos) {
        this.pontos += pontos;
    }

    public void perderVida() {
        if (this.vidas > 0) {
            this.vidas--;
        }
    }

    public void aumentarNivel() {
        level++;
    }
}