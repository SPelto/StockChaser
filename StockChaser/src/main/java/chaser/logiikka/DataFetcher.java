/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chaser.logiikka;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Hakee html-koodia netistä ja muovaa sen merkkijonoksi.
 *
 * @author Samuli Peltonen
 */
public class DataFetcher {

    private Parser parser;

    /**
     * DataFetcher luokan konstruktori ei vaadi parametreja.
     */
    public DataFetcher() {
        this.parser = new Parser();
    }

    /**
     * Luokka luo sille annetun url:in perusteella merkkijonon. Hyväksyy vain
     * "google.com/finance" kautta haetun "historical prices" datan
     *
     * @param url Sivuston josta data haetaan url.
     * @param alku Google listaa sivullaan joukon markkinadataa, alku on
     * monennestako listan jäsenestä aloitetaan.
     * @param loppu Montako riviä parametrin alku jälkeen ladataan koneelle.
     * @return palauttaa parserilla käyneen html- koodin josta on leikattu irti
     * vain tarpeellinen data.
     * @throws IOException
     */
    public String makeStringFromUrl(String url, String alku, String loppu) throws IOException {
        if (Integer.parseInt(loppu) > 250) {
            loppu = "250";
        }
        if (Integer.parseInt(alku) >= 250) {
            alku = "249";
        }
        String mistaMihin = "&start=" + alku + "&num=" + loppu;
        String sourceString = getHtml(url + mistaMihin);
        this.parser.setRaakaData(sourceString);
        return this.parser.luoJarkevaMerkkijono();
    }

    /**
     * Hakee sille syötetyn sivuston html-koodin.
     *
     * @param url Sivuston josta html-koodi haetaan url.
     * @return palautaa html-koodin merkkijonona.
     * @throws MalformedURLException
     * @throws IOException
     */
    public String getHtml(String url) throws MalformedURLException, IOException {
        URL u = new URL(url);
        Scanner scanner = new Scanner(u.openStream());
        String sourceString = "";
        while (scanner.hasNextLine()) {
            sourceString = sourceString + scanner.nextLine();
        }

        return sourceString;
    }
}
