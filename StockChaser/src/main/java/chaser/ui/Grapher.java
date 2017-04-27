/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chaser.ui;

import chaser.logiikka.DataHandler;
import chaser.logiikka.Mapper;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author samuli
 */
public class Grapher extends JPanel {

    private String tiedostoValinta;
    private String dataValinta;
    private ArrayList<String[]> data;
    private DataHandler dh;
    private int[] dimensio;
    private int[] reunat;
    private Mapper mapper;

    public Grapher(int[] dimensio, int[] reunat, String tiedostoValinta, String dataValinta, DataHandler dh) {
        this.tiedostoValinta = tiedostoValinta;
        this.dataValinta = dataValinta;
        this.data = new ArrayList<>();
        this.dh = dh;
        this.dimensio = dimensio;
        this.reunat = reunat;
        try {
            this.data = this.dh.readFile("ExampleData/" + this.tiedostoValinta + ".csv");

        } catch (Exception e) {
        }
        this.mapper = new Mapper(this.data, this.dimensio, this.reunat);
        this.mapper.prosessoiXjaY(this.dataValinta);
    }

    public void paint(Graphics g) {
//        double y_tarkka = Double.parseDouble(this.data.get(1)[1]);
//        int y_pyoristetty = (int) Math.round(y_tarkka);
//
//        double y2_tarkka = Double.parseDouble(this.data.get(2)[1]);
//        int y2_pyoristetty = (int) Math.round(y2_tarkka);
//
//        g.drawLine(10, y_pyoristetty, this.dimensio[0] - 10, y2_pyoristetty);
//        for(int i : this.mappaaja.getMapattuDataY()) {
//            System.out.println(i);
//        }
        g.drawPolyline(this.mapper.getMapattuDataX(), this.mapper.getMapattuDataY(), this.mapper.getMapattuDataX().length);
//        for (int i = 1; i < this.mappaaja.getMapattuData().size(); i++) {
//            g.drawLine(this.mappaaja.getMapattuData().get(i - 1)[0], this.mappaaja.getMapattuData().get(i - 1)[1], this.mappaaja.getMapattuData().get(i)[0], this.mappaaja.getMapattuData().get(i)[1]);
//        }

    }

}

