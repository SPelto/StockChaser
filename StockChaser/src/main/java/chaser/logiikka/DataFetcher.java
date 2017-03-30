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
 */
public class DataFetcher {

    private Parser parser;

    public DataFetcher() {
        this.parser = new Parser();
    }

    public String makeStringFromUrl(String url) throws IOException {
        String sourceString = getHtml(url);
        return this.parser.makeMeaningfulString(sourceString);
    }

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
