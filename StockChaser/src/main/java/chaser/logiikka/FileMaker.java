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
 * @author Samuli Peltonen
 */
public class FileMaker {

    private String tiedostoSijainti;

    /**
     * FileMakerin konstruktori tarkastaa sijaintinsa ja sen perusteella
     * valitsee tiedostojen talletuspaikan.
     *
     * @throws URISyntaxException
     */
    public FileMaker() throws URISyntaxException {
        File polku = new File(FileMaker.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
        this.tiedostoSijainti = polku.getParent() + "/StockData/";
    }

    /**
     * Luo tiedoston jos kansiossa ei viellä ole sen nimistä tiedostoa.
     *
     * @param tiedostonNimi Tiedostolle annettava nimi (saa automaattisesti .csv
     * päätteen)
     * @param teksti Merkkijono joka tiedostolle kirjoitetaan.
     * @return palauttaa joko null jos tiedosto on jo olemassa, tai luodun
     * tiedoston.
     * @throws IOException
     */
    public File makeFile(String tiedostonNimi, String teksti) throws IOException {
        tiedostonNimi = tiedostonNimi + ".csv";
        File tiedosto = new File(this.tiedostoSijainti + tiedostonNimi);

        if (!tiedosto.createNewFile()) {
            return null;
        }
        FileWriter writer = new FileWriter(tiedosto);
        writer.write(teksti);
        writer.close();

        return tiedosto;
    }

    public String getTiedostoSijainti() {
        return tiedostoSijainti;
    }

}
