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
 * @author samuli
 */
public class FileHandler {

    private DataFetcher dFetch;
    private Parser parser;
    private FileReader reader;

    public FileHandler() {
        this.dFetch = new DataFetcher();
        this.parser = new Parser();
        this.reader = new FileReader();
    }

    public void makeFileFromUrl(String fileName, String url) throws IOException {
        this.dFetch.htmlToString(url);
        this.parser.setRaakaData(this.dFetch.getSourceString());
        String kirjoitettava = this.parser.makeString();
        
        fileName = fileName + ".csv";
        File tiedosto = new File("ExampleData/" + fileName);

//Create the file
        if (tiedosto.createNewFile()) {
            System.out.println("\nFile is created!");
        } else {
            System.out.println("\nFile already exists. File was not created!");
        }

//Write Content
        FileWriter writer = new FileWriter(tiedosto);
        writer.write(kirjoitettava);
        writer.close();
        
    }
    
    public void readF(String filePath) {
        reader.readFile(filePath);
        reader.workFile();
        reader.printData();
    }

}
