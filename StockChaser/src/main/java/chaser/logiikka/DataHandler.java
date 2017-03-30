/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chaser.logiikka;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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

    public void makeFileFromUrl(String fileName, String url) throws IOException {
        String kirjoitettava = this.dFetch.makeStringFromUrl(url);
                
        writeFile(fileName, kirjoitettava);
    }
    
    public void writeFile(String fileName, String kirjoitettava) throws IOException {
        this.writer.makeFile(fileName, kirjoitettava);
    }
    
    public void readF(String filePath) {
        reader.readFile(filePath);
        reader.workFile();
        reader.printData();
    }

}
