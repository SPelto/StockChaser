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
 *
 * @author sPelto
 * 
 * Hakee html-koodia netistä ja muovaa sen merkkijonoksi.
 */
public class DataFetcher {

    private Parser parser;

    public DataFetcher() {
        this.parser = new Parser();
    }

 /**
 * @param   url   Sivuston josta data haetaan url. Hyväksyy vain "google.com/finance" kautta haetun "historical prices" datan
 * 
 * @return palauttaa parserilla käyneen html- koodin josta on leikattu irti vain tarpeellinen data.
 */
    public String makeStringFromUrl(String url) throws IOException {
        String sourceString = getHtml(url);
        this.parser.setRaakaData(sourceString);
        return this.parser.makeMeaningfulString();
    }
/**
 * Hakee sille syötetyn sivuston html-koodin.
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
