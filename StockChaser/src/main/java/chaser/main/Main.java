/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chaser.main;

import chaser.logiikka.FileReader;
import chaser.ui.GUI;
import java.util.Scanner;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.SwingUtilities;

/**
 * Ohjelmalla voi hakea markkinadataa google.com/finance sivustolta ja tallentaa
 * sen paikalliseen .csv-tiedostoon. Ohjelma sallii myös datan kuvaajan
 * piirtämisen.
 *
 * @author sPelto
 */
public class Main {

    /**
     * Pääluokka ei tee muuta kuin asettaa käyttöliittymän näkyväksi.
     * @param args
     * @throws IOException
     * @throws URISyntaxException 
     */
    public static void main(String[] args) throws IOException, URISyntaxException {

        GUI g = new GUI();
        g.setVisible(true);
    }

}
