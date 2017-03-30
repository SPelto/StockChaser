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
        // Ladataan sivuston html koodi ja tehdään siitä merkkijono
        String htmlString = this.dFetch.htmlToString(url);
        
        // Haetaan parser luokassa olevan regexin avulla html-koodista haluttu osa
        String kirjoitettava = this.parser.makeMeaningfulString(htmlString);
        
        
        fileWriter(fileName, kirjoitettava);
    }
    
    public void fileWriter(String fileName, String kirjoitettava) throws IOException {
        //Annetaan tiedostolle nimi ja tyyppi sekä sijainti
        fileName = fileName + ".csv";
        File tiedosto = new File("ExampleData/" + fileName);

        //Luodaan tiedosto
        if (tiedosto.createNewFile()) {
            System.out.println("\nTiedosto luotu");
        } else {
            System.out.println("\nTiedosto on jo olemassa! Keskeytetään");
        }

        //Kirjoitetaan tiedostoon
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
