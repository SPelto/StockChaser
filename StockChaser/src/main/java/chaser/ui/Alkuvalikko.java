/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chaser.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 *
 * @author samuli
 */
public class Alkuvalikko implements Runnable {

    private JFrame frame;

    @Override
    public void run() {
        frame = new JFrame("StockChaser");
        frame.setPreferredSize(new Dimension(400, 200));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);

    }

    private void luoKomponentit(Container container) {
        BoxLayout layout = new BoxLayout(container, BoxLayout.Y_AXIS);
        container.setLayout(layout);

        container.add(alkuValikkoTekstit());
        container.add(alkuValikkoNapit());
    }

    private JPanel alkuValikkoNapit() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

        JButton haeData = new JButton("Hae dataa netistä");
        haeData.addActionListener(new KysyUrlKuuntelija());
        panel.add(haeData);

        JButton lueData = new JButton("Lue data");
        lueData.addActionListener(new LueDataKuuntelija());
        panel.add(lueData);

        JButton piirraGraafi = new JButton("Piirrä graafi");
        panel.add(piirraGraafi);

        return panel;
    }

    private JPanel alkuValikkoTekstit() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout());

        JLabel label = new JLabel("StockChaser");
        label.setFont(new Font("Mitä merkitystä tällä on?", Font.BOLD, 28));
        label.setHorizontalAlignment(label.CENTER);
        panel.add(label);
        return panel;
    }

    public JFrame getFrame() {

        return null;
    }

}
