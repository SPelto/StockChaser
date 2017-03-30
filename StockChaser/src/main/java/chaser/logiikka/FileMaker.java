/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chaser.logiikka;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author sPelto
 */
public class FileMaker {
    private String path;
    
    public FileMaker() {
        
    }

    public FileMaker(String path) {
        this.path = path;
    }
    
    
        public void makeFile(String fileName, String kirjoitettava) throws IOException {
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
        java.io.FileWriter writer = new java.io.FileWriter(tiedosto);
        writer.write(kirjoitettava);
        writer.close();
    }
    
}
