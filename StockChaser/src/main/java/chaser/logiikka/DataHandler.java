/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chaser.logiikka;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author sPelto
 */
public class DataHandler {

    private DataFetcher dFetch;
    private FileReader reader;
    private FileMaker writer;

    public DataHandler() {
        this.dFetch = new DataFetcher();
        this.reader = new FileReader();
        this.writer = new FileMaker();
    }

    /**
     * Luo .csv tiedoston.
     *
     * @param fileName Käyttäjän antama nimi luotavalle tiedostolle.
     * @param url Url josta data haetaan.
     * @throws IOException
     */
    public void makeFileFromUrl(String fileName, String url) throws IOException {
        String kirjoitettava = this.dFetch.makeStringFromUrl(url);

        this.writer.makeFile(fileName, kirjoitettava);
    }

    /**
     * @param filePath Luettava tiedosto.
     * @return palauttaa tiedostosta haetun datan.
     */
    public ArrayList<String[]> readFile(String filePath) {
        reader.readFile(filePath);
        reader.workFile();
        return reader.getData();
    }

}
