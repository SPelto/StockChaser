/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chaser.logiikka;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author samuli
 */
public class Mapper {

    private ArrayList<String[]> raakaData;
    private ArrayList<String> valittuData;
    private int[] mapattuDataY;
    private int[] mapattuDataX;
    private int[] dimensio;
    private int[] reunat;

    public Mapper(ArrayList<String[]> data, int[] dimensio, int[] reunat) {
        this.raakaData = data;
        this.valittuData = new ArrayList<>();
        this.dimensio = dimensio;
        this.reunat = reunat;
    }

    public void prosessoiXjaY(String dataValinta) {
        int sarakeValinta = valinta(dataValinta);
        this.valittuData.clear();

        for (String[] s : this.raakaData) {
            this.valittuData.add(s[sarakeValinta]);
        }
        this.valittuData.remove(0);
        mapData();
    }

    private void mapData() {
        this.mapattuDataY = new int[this.valittuData.size()];
        this.mapattuDataX = new int[this.valittuData.size()];
        ArrayList<Double> apuLista = new ArrayList<>();

        double pilkutonKokonaisluku = -2;
        double max = Integer.MIN_VALUE;
        double min = Integer.MAX_VALUE;

        for (String s : this.valittuData) {
            pilkutonKokonaisluku = poistaPilkku(s);
            apuLista.add(poistaPilkku(s));

            if (pilkutonKokonaisluku > max) {
                max = pilkutonKokonaisluku;
            }
            if (pilkutonKokonaisluku < min) {
                min = pilkutonKokonaisluku;
            }
        }

        double valmisY = -1;
        double valmisX = -1;
        int iteraatio = 0;
        for (double d : apuLista) {
            int piirrettavaAlue = this.dimensio[0] - this.reunat[0];
            valmisX = (piirrettavaAlue / this.raakaData.size()) * iteraatio;
            valmisY = (d / max) * (this.dimensio[1] - this.reunat[1]);

            this.mapattuDataX[iteraatio] = (int) Math.round(valmisX);
            this.mapattuDataY[iteraatio] = (int) Math.round(valmisY);
            iteraatio++;
        }
    }

    private double poistaPilkku(String s) {
        String alku = s.substring(0, s.length() - 3);
        String loppu = s.substring(s.length() - 2);
        String pilkuton = alku + loppu;
        return Double.parseDouble(pilkuton);
    }

    private double peilaaMediaaninSuhteen() {
        return -1;
    }

    private int valinta(String dataValinta) {
        int sarakeValinta = -1;

        if (dataValinta.equals("Open")) {
            sarakeValinta = 1;
        }
        if (dataValinta.equals("High")) {
            sarakeValinta = 2;
        }
        if (dataValinta.equals("Low")) {
            sarakeValinta = 3;
        }
        if (dataValinta.equals("Close")) {
            sarakeValinta = 4;
        }
//        if (dataValinta.equals("Volume")) {
//            sarakeValinta = 5;
//        }
        return sarakeValinta;
    }

    public int[] getMapattuDataX() {
        return mapattuDataX;
    }

    public int[] getMapattuDataY() {
        return mapattuDataY;
    }

}
