package Graphic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class StartPanel extends JPanel {
    private Image backGroundImage;

    public StartPanel(GameFrame gameFrame) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        try {
            backGroundImage = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("images/menu/duckhunt.png"))).getImage();
            // Redimensiona a imagem apenas uma vez, no tamanho do painel
            backGroundImage = backGroundImage.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        } catch (Exception e) {
            System.out.println("Erro ao carregar a imagem: " + e.getMessage());
        }

        // Título
        JLabel titleLabel = new JLabel("Duck Hunt", JLabel.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 36));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 0, 20, 0);
        add(titleLabel, gbc);

        // Botão
        JButton startButton = new JButton("Iniciar Jogo");
        startButton.setPreferredSize(new Dimension(200, 50));
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameFrame.startGame();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(startButton, gbc);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backGroundImage != null) {
            g.drawImage(backGroundImage, 0, 0, this); // Desenha a imagem redimensionada
        }
    }
}