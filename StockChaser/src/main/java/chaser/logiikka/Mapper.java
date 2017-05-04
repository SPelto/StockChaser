/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chaser.logiikka;

import java.util.ArrayList;

/**
 * Piirrettäessä kuvaajaa ei voida suoraan käyttää datapisteiden arvoja kuvaajan
 * y-akselin arvoina vaan ne täytyy sovittaa jollain järkevällä tavalla
 * annetulle alueelle johon graafi piirretään.
 *
 * @author Samuli Peltonen
 */
public class Mapper {

    private ArrayList<String[]> raakaData;
    private ArrayList<String> valittuData;
    private int[] mapattuDataY;
    private int[] mapattuDataX;
    private int[] dimensio;
    private int[] reunat;

    /**
     * Mapper luokan kontruktori.
     *
     * @param data Data joka halutaan sovittaa kuvaajaksi.
     * @param dimensio Piirrettävän alueen x- ja y-suuntaisten pikseleiden
     * määrä.
     * @param reunat Paljonko halutaan jättää väliä piirrettävän alueen ja
     * ikkunan reunojen väliin.
     */
    public Mapper(ArrayList<String[]> data, int[] dimensio, int[] reunat) {
        this.raakaData = data;
        this.valittuData = new ArrayList<>();
        this.dimensio = dimensio;
        this.reunat = reunat;
    }

    /**
     * Kuvaa luokalle annetun datan pisteet sisäisille muuttujille
     * "MapattuDataX" ja "MapattuDataY".
     *
     * @param dataValinta Valinta .csv tiedoston sarakkeista.
     */
    public void kuvaaData(String dataValinta) {
        int sarakeValinta = valinta(dataValinta);
        this.valittuData.clear();

        for (String[] s : this.raakaData) {
            this.valittuData.add(s[sarakeValinta]);
        }
        this.valittuData.remove(0);

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
        double valmisX = this.reunat[0];
        int iteraatio = 0;

        for (double d : apuLista) {
            double piirrettavaAlueX = this.dimensio[0] - 2 * this.reunat[0];
            double piirrettavaAlueY = this.dimensio[1] - 2 * this.reunat[1];
            valmisX = this.reunat[0] + iteraatio * (piirrettavaAlueX / (double) apuLista.size());

            double yValiarvo = d - min;
            valmisY = (yValiarvo / (max - min)) * piirrettavaAlueY;
            valmisY = peilaa(valmisY, piirrettavaAlueY) + this.reunat[1];

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

    private double peilaa(double peilattavaArvo, double piirrettavaAlueY) {
        double peilattuArvo = piirrettavaAlueY - peilattavaArvo;
        return peilattuArvo;
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
        if (dataValinta.equals("Volume")) {
            sarakeValinta = 5;
        }
        return sarakeValinta;
    }

    public int[] getMapattuDataX() {
        return mapattuDataX;
    }

    public int[] getMapattuDataY() {
        return mapattuDataY;
    }

    public int getMapattuDataSuurinX() {
        int suurin = Integer.MIN_VALUE;
        for (int i : this.mapattuDataX) {
            if (i > suurin) {
                suurin = i;
            }
        }
        return suurin;
    }

    public int getMapattuDataPieninX() {
        int pienin = Integer.MAX_VALUE;
        for (int i : this.mapattuDataX) {
            if (i < pienin) {
                pienin = i;
            }
        }
        return pienin;
    }

    public int getMapattuDataSuurinY() {
        int suurin = Integer.MIN_VALUE;
        for (int i : this.mapattuDataY) {
            if (i > suurin) {
                suurin = i;
            }
        }
        return suurin;
    }

    public int getMapattuDataPieninY() {
        int pienin = Integer.MAX_VALUE;
        for (int i : this.mapattuDataY) {
            if (i < pienin) {
                pienin = i;
            }
        }
        return pienin;
    }

    public int[] getDimensio() {
        return dimensio;
    }

    public int[] getReunat() {
        return reunat;
    }

}
