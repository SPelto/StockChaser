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
 *
 * @author sPelto
 */
public class Parser {

    private String raakaData;
    private String muokattuData;
    private ArrayList<String> halututTrimmaamattomatRivit;

    public Parser() {
        this.halututTrimmaamattomatRivit = new ArrayList<String>();
    }

    public Parser(String raw) {
        this.raakaData = raw;
        this.halututTrimmaamattomatRivit = new ArrayList<String>();
    }

    public String makeMeaningfulString() {
        // Luodaan html-koodista halutut osat riveiksi, niin että yhdellä rivillä on saman päivämäärän markkinadata
        makeRivit();
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

    public void makeRivit() {
        //Leikkaa html-koodista palan halutun datan ympäriltä. Helpottaa käsittelyä
        cutRaakaData();
        //

        //Käsitellään irtileikattua osaa
        Pattern p = Pattern.compile(
                "(td class=|th class=\"bb)"
                + "(.*?)"
                + "(tr|</table>)");
        Matcher m = p.matcher(this.muokattuData);

        while (m.find()) {
            this.halututTrimmaamattomatRivit.add(m.group(2));
        }
        //
    }

    public void cutRaakaData() {

        Pattern p = Pattern.compile("(<table class=\"gf-table historical_price\"><tr class=bb>)"
                + "(.*?)"
                + "(</table></div></form></div></div></div></div>)");
        Matcher m = p.matcher(this.raakaData);

        m.find();
        this.muokattuData = m.group(2);
    }

    public String getRaakaData() {
        return raakaData;
    }

    public String getMuokattuData() {
        return muokattuData;
    }

    public void tulostaRivit() {
        for (String s : this.halututTrimmaamattomatRivit) {
            System.out.println(s);
        }
    }

    public void setRaakaData(String raakaData) {
        this.raakaData = raakaData;
    }

    public ArrayList<String> getHalututTrimmaamattomatRivit() {
        return halututTrimmaamattomatRivit;
    }
    
    

}
