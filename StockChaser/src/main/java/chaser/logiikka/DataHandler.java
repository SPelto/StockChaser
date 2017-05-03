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
        String teksti = this.dFetch.makeStringFromUrl(url);

        this.writer.makeFile(fileName, teksti);
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
    
    public double getSuurinArvo(String dataValinta, String tiedosto) throws FileNotFoundException {
       return this.reader.getSuurinArvo(dataValinta, new File("ExampleData/" + tiedosto + ".csv"));
    }
    
    public double getPieninArvo(String dataValinta, String tiedosto) throws FileNotFoundException {
        return this.reader.getPieninArvo(dataValinta, new File("ExampleData/" + tiedosto + ".csv"));
    }

}
