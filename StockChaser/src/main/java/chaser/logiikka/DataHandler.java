/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chaser.logiikka;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

/**
 * DataHandler koordinoi datan muovaamiseen, tallentamiseen ja hakemiseen
 * tarkoitettuja luokkia.
 *
 * @author sPelto
 */
public class DataHandler {

    private DataFetcher dFetch;
    private FileReader reader;
    private FileMaker writer;
    private String path;

    /**
     * DataHandlerin konstruktori luo itselleen tarvitsemansa luokat.
     *
     * @param path Ohjelman luoman "StockData" kansion sijainti.
     * @throws URISyntaxException
     */
    public DataHandler(String path) throws URISyntaxException {
        this.dFetch = new DataFetcher();
        this.reader = new FileReader();
        this.writer = new FileMaker();
        this.path = path;
    }

    /**
     * Luo .csv tiedoston.
     *
     * @param fileName Käyttäjän antama nimi luotavalle tiedostolle.
     * @param url Url josta data haetaan.
     * @throws IOException
     */
    public void makeFileFromUrl(String fileName, String url, String alku, String loppu) throws IOException {
        String teksti = this.dFetch.makeStringFromUrl(url, alku, loppu);

        this.writer.makeFile(fileName, teksti);
    }

    /**
     * Lukee parametrinä annetun tiedostonpolun datan ja muovaa siitä helposti
     * luettavan listan.
     *
     * @param filePath Luettava tiedosto.
     * @return palauttaa tiedostosta haetun datan.
     */
    public ArrayList<String[]> readFile(String filePath) {
        reader.readFile(filePath);
        reader.workFile();
        return reader.getData();
    }

    /**
     * Työkalu tiedoston sarakkeen suurimman arvon löytämiseen.
     *
     * @param dataValinta Valinta .csv-tiedoston sarakkeista "Open, High, Low,
     * Close ja Volume".
     * @param tiedosto Luettava tiedosto.
     * @return Palauttaa valitun tiedoston valitun sarakkeen suurimman arvon.
     * @throws FileNotFoundException
     */
    public double getSuurinArvo(String dataValinta, String tiedosto) throws FileNotFoundException {
        return this.reader.getSuurinArvo(dataValinta, new File(this.path + "/StockData/" + tiedosto + ".csv"));
    }

    /**
     * Työkalu tiedoston sarakkeen pienimmän arvon löytämiseen.
     *
     * @param dataValinta Valinta .csv-tiedoston sarakkeista "Open, High, Low,
     * @param tiedosto Luettava tiedosto.
     * @return Palauttaa valitun tiedoston valitun sarakkeen pienimmän arvon.
     * @throws FileNotFoundException
     */
    public double getPieninArvo(String dataValinta, String tiedosto) throws FileNotFoundException {
        return this.reader.getPieninArvo(dataValinta, new File(this.path + "/StockData/" + tiedosto + ".csv"));
    }

}
