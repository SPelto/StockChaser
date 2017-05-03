/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chaser.logiikka;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author sPelto
 */
public class FileReader {

    private File tiedosto;
    private ArrayList<String[]> data;

    public FileReader() {
        this.data = new ArrayList<>();
    }

    public void readFile(String filePath) {
        this.tiedosto = new File(filePath);

    }

    /**
     * Luo sisäisen muuttujan ArrayList<String[]> this.data jossa on hyvin
     * jäsenneltynä .csv tiedostolta luettu data.
     */
    public void workFile() {
        this.data.clear();
        Scanner tiedostoLukija = null;
        try {
            tiedostoLukija = new Scanner(this.tiedosto);
        } catch (Exception e) {
            System.out.println("Tiedosto ei kelpaa");
        }

        String[] taulu = new String[6];
        while (tiedostoLukija.hasNextLine()) {
            taulu = tiedostoLukija.nextLine().split(",");
            this.data.add(taulu);
        }
        siistiListanTaulut(this.data);
    }

    public static void siistiListanTaulut(ArrayList<String[]> lista) {
        ArrayList<String[]> apuLista = new ArrayList<>();
        if (!onkoEnsimmainenKirjainD(lista.get(0)[0])) {
            lista.get(0)[0] = lista.get(0)[0].substring(1);
        }
        for (String[] s : lista) {
            String[] apuTaulu = new String[6];
            for (int i = 0; i < 6; i++) {
                String trimmattu = s[i].trim();
                apuTaulu[i] = trimmattu;
            }
            apuLista.add(apuTaulu);
        }
        lista = apuLista;
    }

    public ArrayList<String[]> getData() {
        return data;
    }

    public double getSuurinArvo(String dataValinta, File tiedosto) throws FileNotFoundException {
        int sarakeValinta = valinta(dataValinta);
        Scanner lukija = new Scanner(tiedosto);
        double suurin = Double.MIN_VALUE;
        while (lukija.hasNextLine()) {
            String rivi = lukija.nextLine();
            String[] teksti = rivi.split(",");
            double arvo;
            try {
                arvo = Double.parseDouble(teksti[sarakeValinta]);
            } catch (Exception e) {
                continue;
            }
            if (arvo > suurin) {
                suurin = arvo;
            }
        }
        return suurin;
    }

    public double getPieninArvo(String dataValinta, File tiedosto) throws FileNotFoundException {
        int sarakeValinta = valinta(dataValinta);
        Scanner lukija = new Scanner(tiedosto);
        double pienin = Double.MAX_VALUE;
        while (lukija.hasNextLine()) {
            String rivi = lukija.nextLine();
            String[] teksti = rivi.split(",");
            double arvo;
            try {
                arvo = Double.parseDouble(teksti[sarakeValinta]);
            } catch (Exception e) {
                continue;
            }
            if (arvo < pienin) {
                pienin = arvo;
            }
        }
        return pienin;
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

    public static boolean onkoEnsimmainenKirjainD(String s) {
        //Jostain syystä tiedostojen alusta löytyy tuntematon merkki, vaikka kaiken datan pitäisi alkaa "Date"
        return s.charAt(0) == 'D';
    }
}
