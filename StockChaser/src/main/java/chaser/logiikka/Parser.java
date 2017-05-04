/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chaser.logiikka;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Luokka jolla saadaan html-koodista haluttu data irti.
 *
 * @author Samuli Peltonen
 *
 * Luokka lukee raakaa html-koodia ja etsii sieltä oleellisen markkinadatan.
 */
public class Parser {

    private String raakaData;
    private String muokattuData;
    private ArrayList<String> halututTrimmaamattomatRivit;

    /**
     * Parser luokan konstruktori jossa luodaan tyhjä lista.
     */
    public Parser() {
        this.halututTrimmaamattomatRivit = new ArrayList<String>();
    }

    /**
     * Parser luokan konstruktori jossa luodaan tyhjä lista, ja annetaan
     * sisäiselle muuttujalle "raakaData" merkkijono jota lähdetään muovaamaan.
     *
     * @param raw Muovaamatonta html-koodia.
     */
    public Parser(String raw) {
        this.raakaData = raw;
        this.halututTrimmaamattomatRivit = new ArrayList<String>();
    }

    /**
     * Metodi luo html-koodista siistin merkkijonon joka on valmiissa muodossa
     * tiedostolle kirjoittamista varten.
     *
     * @return Metodi palauttaa siistin merkkijonon jossa on tarpeellinen data
     * siististi aseteltuna.
     */
    public String luoJarkevaMerkkijono() {
        // Luodaan html-koodista halutut osat riveiksi, niin että yhdellä rivillä on saman päivämäärän markkinadata
        luoRivit();
        //

        // Poistetaan luoduista riveistä "roska" pois ympäriltä ja tehdään niistä yksi merkkijono joka on helppo kirjoittaa tiedostolle.
        String teksti = "";
        Pattern p = Pattern.compile("(>)"
                + "(.*?)"
                + "(<)");
        Matcher m;
        for (String rivi : this.halututTrimmaamattomatRivit) {
            m = p.matcher(rivi);
            while (m.find()) {
                teksti = teksti + m.group(2).replaceAll(",", "");
                teksti = teksti + ",";
            }
            teksti = teksti.substring(0, teksti.length() - 1) + "\n";
        }
        return teksti;
    }

    /**
     * Metodi luo apulistan jossa on paloja html-koodia haluttujen
     * datakappaleiden ympäriltä.
     */
    public void luoRivit() {
        //Leikkaa html-koodista palan halutun datan ympäriltä. Helpottaa käsittelyä
        Pattern p = Pattern.compile("(<table class=\"gf-table historical_price\"><tr class=bb>)"
                + "(.*?)"
                + "(</table></div></form></div></div></div></div>)");
        Matcher m = p.matcher(this.raakaData);

        m.find();
        this.muokattuData = m.group(2);
        //

        // Käsitellään irtileikattua osaa
        p = Pattern.compile(
                "(td class=|th class=\"bb)"
                + "(.*?)"
                + "(tr|</table>)");
        m = p.matcher(this.muokattuData);

        // Varmistetaan että lista on tyhjä
        this.halututTrimmaamattomatRivit.clear();
        while (m.find()) {
            this.halututTrimmaamattomatRivit.add(m.group(2));
        }
        //
    }

    public String getRaakaData() {
        return raakaData;
    }

    public String getMuokattuData() {
        return muokattuData;
    }

    public void setRaakaData(String raakaData) {
        this.raakaData = raakaData;
    }

    public ArrayList<String> getHalututTrimmaamattomatRivit() {
        return halututTrimmaamattomatRivit;
    }

}
