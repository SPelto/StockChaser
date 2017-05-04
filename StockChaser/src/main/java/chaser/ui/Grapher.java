/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chaser.ui;

import chaser.logiikka.DataHandler;
import chaser.logiikka.Mapper;
import java.awt.Graphics;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Samuli Peltonen
 */
public class Grapher extends JPanel {

    private String tiedostoValinta;
    private String dataValinta;
    private ArrayList<String[]> data;
    private DataHandler dh;
    private int[] dimensio;
    private int[] reunat;
    private Mapper mapper;

    public Grapher(int[] dimensio, int[] reunat, String tiedostoValinta, String dataValinta, DataHandler dh, String polku) {
        this.tiedostoValinta = tiedostoValinta;
        this.dataValinta = dataValinta;
        this.data = new ArrayList<>();
        this.dh = dh;
        this.dimensio = dimensio;
        this.reunat = reunat;
        try {
            this.data = this.dh.readFile(polku + "/StockData/" + this.tiedostoValinta + ".csv");

        } catch (Exception e) {
        }
        this.mapper = new Mapper(this.data, this.dimensio, this.reunat);
        this.mapper.kuvaaData(dataValinta);
    }

    public void paint(Graphics g) {
        piirraData(g);
        piirraAkselit(g);
        piirraNimi(g);
        try {
            piirraMaxjaMinArvo(g);
        } catch (FileNotFoundException ex) {
        }
        piirraPaivamaarat(g);
    }

    private void piirraData(Graphics g) {
        g.drawPolyline(this.mapper.getMapattuDataX(), this.mapper.getMapattuDataY(), this.mapper.getMapattuDataX().length);
    }

    private void piirraAkselit(Graphics g) {
        int xAlku = this.reunat[0];
        int xLoppu = this.mapper.getMapattuDataSuurinX();
        int yAlku = this.reunat[1];
        int yLoppu = this.mapper.getMapattuDataSuurinY();

        piirraX(g, xAlku, xLoppu, yAlku, yLoppu);
        piirraY(g, xAlku, xLoppu, yAlku, yLoppu);
    }

    private void piirraX(Graphics g, int xAlku, int xLoppu, int yAlku, int yLoppu) {
        g.drawLine(xAlku, yLoppu, xLoppu, yLoppu);

    }

    private void piirraY(Graphics g, int xAlku, int xLoppu, int yAlku, int yLoppu) {
        g.drawLine(this.reunat[0], yAlku, this.reunat[0], yLoppu);

    }

    private void piirraNimi(Graphics g) {
        g.drawString(tiedostoValinta, this.dimensio[0] / 2, this.reunat[1] / 2);
    }

    private void piirraMaxjaMinArvo(Graphics g) throws FileNotFoundException {
        g.drawString("" + this.dh.getSuurinArvo(dataValinta, tiedostoValinta), this.reunat[0] / 10, this.reunat[1]);
        g.drawString("" + this.dh.getPieninArvo(dataValinta, tiedostoValinta), this.reunat[0] / 10, this.dimensio[1] - this.reunat[1]);

    }

    private void piirraPaivamaarat(Graphics g) {
        g.drawString(this.data.get(1)[0], this.reunat[0], this.dimensio[1] - this.reunat[1] / 2);
        g.drawString(this.data.get(this.data.size() - 1)[0], this.dimensio[0] - 2*this.reunat[0], this.dimensio[1] - this.reunat[1] / 2);

    }

}
