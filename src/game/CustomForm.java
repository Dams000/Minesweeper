package game;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class CustomForm extends JFrame implements ChangeListener {

    JSlider sliderRows;
    JSlider sliderColumns;
    JSlider sliderBombs;
    JLabel boardDescription;
    Font font = new Font("Times New Roman", Font.PLAIN, 18);

    public CustomForm() {
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 600);

        JPanel contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setBackground(Color.GRAY);

        JButton returnBtn = new JButton("Back");
        returnBtn.addActionListener(e -> {
            setVisible(false);
            Minesweeper.showStartUp();
        });
        returnBtn.setFont(font);
        returnBtn.setBounds(20, 20, 100, 20);
        returnBtn.setBackground(Color.LIGHT_GRAY);
        returnBtn.setFocusable(false);
        contentPane.add(returnBtn);

        sliderRows = new JSlider(10, 50);
        sliderRows.setOrientation(SwingConstants.VERTICAL);
        sliderRows.setPaintTicks(true);
        sliderRows.setSnapToTicks(true);
        sliderRows.setPaintLabels(true);
        sliderRows.setMajorTickSpacing(15);
        sliderRows.setMinorTickSpacing(1);
        sliderRows.setFont(font);
        sliderRows.setFocusable(false);
        sliderRows.setBounds(150, 50, 50, 400);
        sliderRows.setBackground(Color.GRAY);
        sliderRows.setForeground(Color.BLACK);
        sliderRows.addChangeListener(this);
        contentPane.add(sliderRows);

        sliderColumns = new JSlider(10, 50);
        sliderColumns.setOrientation(SwingConstants.VERTICAL);
        sliderColumns.setPaintTicks(true);
        sliderColumns.setSnapToTicks(true);
        sliderColumns.setPaintLabels(true);
        sliderColumns.setMajorTickSpacing(15);
        sliderColumns.setMinorTickSpacing(1);
        sliderColumns.setFont(font);
        sliderColumns.setFocusable(false);
        sliderColumns.setBounds(250, 50, 100, 400);
        sliderColumns.setBackground(Color.GRAY);
        sliderColumns.setForeground(Color.BLACK);
        sliderColumns.addChangeListener(this);
        contentPane.add(sliderColumns);

        sliderBombs = new JSlider(0, 500, 50);
        sliderBombs.setOrientation(SwingConstants.VERTICAL);
        sliderBombs.setPaintTicks(true);
        sliderBombs.setSnapToTicks(false);
        sliderBombs.setPaintLabels(true);
        sliderBombs.setMajorTickSpacing(sliderBombs.getMaximum()/20);
        sliderBombs.setFont(font);
        sliderBombs.setFocusable(false);
        sliderBombs.setBounds(350, 50, 100, 400);
        sliderBombs.setBackground(Color.GRAY);
        sliderBombs.setForeground(Color.BLACK);
        sliderBombs.addChangeListener(this);
        contentPane.add(sliderBombs);

        String text = "ROWS: 25, COLUMNS: 25, BOMBS: 50";
        boardDescription = new JLabel(text);
        boardDescription.setFont(font);
        FontMetrics fm = boardDescription.getFontMetrics(font);
        int x = (getWidth() - fm.stringWidth(text)) / 2;
        boardDescription.setBounds(x - 50, 450, 400, 100);
        contentPane.add(boardDescription);

        JButton startBtn = new JButton("Start");
        startBtn.addActionListener(e -> {
            setVisible(false);
            Minesweeper.startCustom(sliderRows.getValue(), sliderColumns.getValue(), Math.max(5, sliderBombs.getValue()));
        });
        startBtn.setFont(font);
        startBtn.setBounds(450, 450, 100, 100);
        startBtn.setBackground(Color.LIGHT_GRAY);
        startBtn.setFocusable(false);
        contentPane.add(startBtn);

        setLocationRelativeTo(null);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        sliderBombs.setMajorTickSpacing(sliderBombs.getMaximum()/20);
        sliderBombs.setMaximum(Math.min(500, (sliderRows.getValue() * sliderColumns.getValue())/2));
        sliderBombs.setMajorTickSpacing(sliderBombs.getMaximum()/10);
//        System.out.println("ROWS: " + sliderRows.getValue() + ", COLUMNS: " + sliderColumns.getValue() + ", BOMBS: " + sliderBombs.getValue());
        boardDescription.setText("ROWS: " + sliderRows.getValue() + ", COLUMNS: " + sliderColumns.getValue() + ", BOMBS: " + sliderBombs.getValue());
    }
}
