/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chaser.logiikka;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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

    public void workFile() {
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
    
    public static boolean onkoEnsimmainenKirjainD(String s) {
        //Jostain syystä tiedostojen alusta löytyy tuntematon merkki, vaikka kaiken datan pitäisi alkaa "Date"
        return s.charAt(0) == 'D';
    }

    public void printData() {
        for (String[] s : this.data) {
            System.out.println(s[0] + ", " + s[1] + ", " + s[2] + ", " + s[3] + ", " + s[4] + ", " + s[5]);
        }
    }

}
