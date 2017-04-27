/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chaser.logiikka;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author samuli
 */
public class ParserTest {

    private String html;

    public ParserTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws FileNotFoundException {
        File htmlString = new File("src/test/aputiedostot/testiHtml.txt");
        Scanner scanner = new Scanner(htmlString);

        String teksti = "";
        String rivi = "";
        while (scanner.hasNextLine()) {
            rivi = scanner.nextLine();
            teksti = teksti + rivi;
        }

        this.html = teksti;
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void makeRivitLuoOikeanlaisenListan() throws FileNotFoundException {

        Parser p = new Parser(this.html);
        p.makeRivit();

        ArrayList<String> rivit = p.getHalututTrimmaamattomatRivit();

        for (String s : rivit) {
            s = s.trim();
            assertTrue(s.substring(0, 2).equals("lm") || s.substring(0, 4).equals("\"lm\""));
            assertTrue(s.substring(s.length() - 1, s.length()).equals("<"));
        }
    }

//    @Test
//    public void cutRawStringLeikkaaHtmlKoodiaOikeastaKohtaa() throws FileNotFoundException {
//        Parser p = new Parser(this.html);
//        p.cutRaakaData();
//
//        assertTrue(p.getMuokattuData().substring(0, 10).equals("<th class="));
//        assertTrue(p.getMuokattuData().substring(p.getMuokattuData().length() - 10, p.getMuokattuData().length()).equals(">1,495,714"));
//
//    }

    @Test
    public void makeMeaningfullStringToimii() {
        Parser p = new Parser(this.html);
        p.setRawData(html);
        
        String teksti = p.makeMeaningfulString();
        assertTrue(teksti.substring(0, 31).equals("Date,Open,High,Low,Close,Volume"));
        assertTrue(teksti.substring(32, 74).equals("Apr 5 2017,13.53,13.54,12.96,13.11,6804212"));        
        assertTrue(teksti.substring(teksti.length() - 10, teksti.length() - 0).equals("8,1556310\n"));

    }
}
