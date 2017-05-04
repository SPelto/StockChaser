/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chaser.logiikka;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * FileMaker on työkalu tiedostojen luontiin.
 *
 * @author sPelto
 */
public class FileMaker {

    private String path;

    /**
     * FileMakerin konstruktori tarkastaa sijaintinsa ja sen perusteella
     * valitsee tiedostojen talletuspaikan.
     *
     * @throws URISyntaxException
     */
    public FileMaker() throws URISyntaxException {
        File path = new File(FileMaker.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());

        this.path = path.getParent() + "/StockData/";
    }

    /**
     * Luo tiedoston jos kansiossa ei viellä ole sen nimistä tiedostoa.
     *
     * @param fileName Tiedostolle annettava nimi (saa automaattisesti .csv
     * päätteen)
     * @param teksti Merkkijono joka tiedostolle kirjoitetaan.
     * @return palauttaa joko null jos tiedosto on jo olemassa, tai luodun
     * tiedoston.
     * @throws IOException
     */
    public File makeFile(String fileName, String teksti) throws IOException {
        //Annetaan tiedostolle nimi ja tyyppi sekä sijainti
        fileName = fileName + ".csv";
        File tiedosto = new File(this.path + fileName);

        if (!tiedosto.createNewFile()) {
            return null;
        }
        FileWriter writer = new FileWriter(tiedosto);
        writer.write(teksti);
        writer.close();

        return tiedosto;
    }

    public String getPath() {
        return path;
    }

}
