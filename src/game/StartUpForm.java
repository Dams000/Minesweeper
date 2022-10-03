package game;

import javax.swing.*;
import java.awt.*;

public class StartUpForm extends JFrame {

    public StartUpForm() {
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 600);

        JPanel contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setBackground(Color.BLACK);

        JLabel title = new JLabel("MINESWEEPER");
        title.setBounds(100, 100, 400, 50);
        title.setForeground(Color.white);
        title.setFont(new Font("ZX Spectrum", Font.BOLD, 50));
        contentPane.add(title);

        JButton startBtnEasy = new JButton("Easy");
        startBtnEasy.addActionListener(e -> {
            setVisible(false);
            Minesweeper.startEasy();
        });
        startBtnEasy.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        startBtnEasy.setBounds(100, 260, 400, 40);
        startBtnEasy.setBackground(Color.LIGHT_GRAY);
        startBtnEasy.setFocusable(false);
        contentPane.add(startBtnEasy);

        JButton startBtnMedium = new JButton("Medium");
        startBtnMedium.addActionListener(e -> {
            setVisible(false);
            Minesweeper.startMedium();
        });
        startBtnMedium.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        startBtnMedium.setBounds(100, 320, 400, 40);
        startBtnMedium.setBackground(Color.LIGHT_GRAY);
        startBtnMedium.setFocusable(false);
        contentPane.add(startBtnMedium);

        JButton startBtnHard = new JButton("Hard");
        startBtnHard.addActionListener(e -> {
            setVisible(false);
            Minesweeper.startHard();
        });
        startBtnHard.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        startBtnHard.setBounds(100, 380, 400, 40);
        startBtnHard.setBackground(Color.LIGHT_GRAY);
        startBtnHard.setFocusable(false);
        contentPane.add(startBtnHard);

        JButton startBtnCustom = new JButton("Custom");
        startBtnCustom.addActionListener(e -> {
            setVisible(false);
            Minesweeper.showCustomForm();
        });
        startBtnCustom.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        startBtnCustom.setBounds(100, 440, 400, 40);
        startBtnCustom.setBackground(Color.LIGHT_GRAY);
        startBtnCustom.setFocusable(false);
        contentPane.add(startBtnCustom);

        setLocationRelativeTo(null);
    }

}
