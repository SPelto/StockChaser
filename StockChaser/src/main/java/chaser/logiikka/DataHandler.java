/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chaser.logiikka;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

/**
 * DataHandler koordinoi datan muovaamiseen, tallentamiseen ja hakemiseen
 * tarkoitettuja luokkia.
 *
 * @author Samuli Peltonen
 */
public class DataHandler {

    private DataFetcher dFetch;
    private FileReader reader;
    private FileMaker writer;
    private String StockDataKansionSijainti;

    /**
     * DataHandlerin konstruktori luo itselleen tarvitsemansa luokat.
     *
     * @param kansionSijainti Ohjelman luoman "StockData" kansion sijainti.
     * @throws URISyntaxException
     */
    public DataHandler(String kansionSijainti) throws URISyntaxException {
        this.dFetch = new DataFetcher();
        this.reader = new FileReader();
        this.writer = new FileMaker();
        this.StockDataKansionSijainti = kansionSijainti;
    }

    /**
     * Luo .csv tiedoston.
     *
     * @param tiedostonNimi Käyttäjän antama nimi luotavalle tiedostolle.
     * @param url Url josta data haetaan.
     * @throws IOException
     */
    public void makeFileFromUrl(String tiedostonNimi, String url, String alku, String loppu) throws IOException {
        String teksti = this.dFetch.makeStringFromUrl(url, alku, loppu);

        this.writer.makeFile(tiedostonNimi, teksti);
    }

    /**
     * Lukee parametrinä annetun tiedostonpolun datan ja muovaa siitä helposti
     * luettavan listan.
     *
     * @param tiedostoPolku Luettava tiedosto.
     * @return palauttaa tiedostosta haetun datan.
     */
    public ArrayList<String[]> readFile(String tiedostoPolku) {
        reader.readFile(tiedostoPolku);
        reader.workFile();
        return reader.getData();
    }

    /**
     * Työkalu tiedoston sarakkeen suurimman arvon löytämiseen.
     *
     * @param dataValinta Valinta .csv-tiedoston sarakkeista "Open, High, Low,
     * Close ja Volume".
     * @param tiedostonNimi Luettava tiedosto.
     * @return Palauttaa valitun tiedoston valitun sarakkeen suurimman arvon.
     * @throws FileNotFoundException
     */
    public double getSuurinArvo(String dataValinta, String tiedostonNimi) throws FileNotFoundException {
        return this.reader.getSuurinArvo(dataValinta, new File(this.StockDataKansionSijainti + "/StockData/" + tiedostonNimi + ".csv"));
    }

    /**
     * Työkalu tiedoston sarakkeen pienimmän arvon löytämiseen.
     *
     * @param dataValinta Valinta .csv-tiedoston sarakkeista "Open, High, Low,
     * Close ja Volume.
     * @param tiedostonNimi Luettava tiedosto.
     * @return Palauttaa valitun tiedoston valitun sarakkeen pienimmän arvon.
     * @throws FileNotFoundException
     */
    public double getPieninArvo(String dataValinta, String tiedostonNimi) throws FileNotFoundException {
        return this.reader.getPieninArvo(dataValinta, new File(this.StockDataKansionSijainti + "/StockData/" + tiedostonNimi + ".csv"));
    }

}
