package Testes;

import Logic.Hunt;

public class HuntTeste {
    public static void main(String[] args) {
        // Teste de inicialização
        Hunt hunt = new Hunt();
        System.out.println("Teste de inicialização:");
        System.out.println("Vidas iniciais: " + hunt.getVidas());
        System.out.println("Pontos iniciais: " + hunt.getPontos());

        // Teste de adicionar pontos
        hunt.adicionarPontos(25);
        System.out.println("\nTeste de adicionar pontos:");
        System.out.println("Pontos após adicionar 25: " + hunt.getPontos());

        // Teste de perder vida
        hunt.perderVida();
        System.out.println("\nTeste de perder vida:");
        System.out.println("Vidas após perder 1: " + hunt.getVidas());

        // Teste de perder todas as vidas
        System.out.println("\nTeste de perder todas as vidas:");
        for (int i = 0; i < 3; i++) {
            hunt.perderVida();
            System.out.println("Vidas após perder 1: " + hunt.getVidas());
        }
    }
}
