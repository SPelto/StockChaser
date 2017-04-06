/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chaser.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
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
        frame.setPreferredSize(new Dimension(400, 150));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);

    }

    private void luoKomponentit(Container container) {
        BoxLayout layout = new BoxLayout(container, BoxLayout.Y_AXIS);
        container.setLayout(layout);

        container.add(alkuValikkoNapit());
        container.add(alkuValikkoTekstit());
    }

    private JPanel alkuValikkoNapit() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(400, 100));
        panel.setLayout(new GridLayout());

        JButton haeData = new JButton("Hae dataa netistä");
        haeData.addActionListener(new KysyUrlKuuntelija());
        panel.add(haeData);

        JButton lueData = new JButton("Lue data");
        lueData.addActionListener(new LueDataKuuntelija());
        panel.add(lueData);

        return panel;
    }

    private JPanel alkuValikkoTekstit() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(400, 50));
        panel.setLayout(new FlowLayout());

        JTextArea tekstikentta = new JTextArea("Kirjoita url josta data haetaan");
        panel.add(tekstikentta);

        return panel;
    }

    public JFrame getFrame() {

        return null;
    }

}
