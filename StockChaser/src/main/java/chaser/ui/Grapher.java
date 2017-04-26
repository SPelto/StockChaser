/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chaser.ui;

import chaser.logiikka.DataHandler;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author samuli
 */
public class Grapher extends JPanel {

    private String valinta;
    private ArrayList<String[]> data;
    private DataHandler dh;
    private int[] dimensio;

    public Grapher(int[] dimensio, String valinta, DataHandler dh) {
        this.valinta = valinta;
        this.data = new ArrayList<>();
        this.dh = dh;
        this.dimensio = dimensio;

        try {
            this.data = this.dh.readFile("ExampleData/" + this.valinta + ".csv");

        } catch (Exception e) {
        }
    }

    public void paintComponent(Graphics g) {
        double y_tarkka = Double.parseDouble(this.data.get(1)[1]);
        int y_pyoristetty = (int) Math.round(y_tarkka);
        double y2_tarkka = Double.parseDouble(this.data.get(2)[1]);
        int y2_pyoristetty = (int) Math.round(y2_tarkka);
        
        g.drawLine(10, y_pyoristetty, this.dimensio[0] - 10, y2_pyoristetty);

    }
}
