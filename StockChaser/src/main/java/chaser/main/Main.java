/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chaser.main;

import chaser.ui.GUI;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Ohjelmalla voi hakea markkinadataa google.com/finance sivustolta ja tallentaa
 * sen paikalliseen .csv-tiedostoon. Ohjelma sallii myös datan kuvaajan
 * piirtämisen.
 *
 * @author Samuli Peltonen
 */
public class Main {

    /**
     * Pääluokka ei tee muuta kuin asettaa käyttöliittymän näkyväksi.
     *
     * @param args
     * @throws IOException
     * @throws URISyntaxException
     */
    public static void main(String[] args) throws IOException, URISyntaxException {

        GUI g = new GUI();
        g.setVisible(true);
    }

}
