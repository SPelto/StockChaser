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
import java.net.URISyntaxException;
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

    private String polku;
    private Parser parser;
    private DataHandler dh;
    private DataFetcher df;
    private FileReader f;
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
    public void setUp() throws FileNotFoundException, URISyntaxException, IOException {
        this.polku = new File(FileMaker.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()).getParent();
        File kansio = new File(polku + "/StockData");
        kansio.mkdir();
        this.df = new DataFetcher();
        this.dh = new DataHandler(polku);
        this.f = new FileReader();
        this.html = df.getHtml("https://www.google.com/finance/historical?q=NASDAQ%3AMSFT&ei=STILWdCgJNvLsQGwxIKwCg");

//        File htmlString = new File(polku + "/StockData/testiHtml.txt");
//        FileWriter fr = new FileWriter(htmlString);
        this.parser = new Parser(this.html);

    }

    @After
    public void tearDown() throws URISyntaxException {
        String polku = new File(FileMaker.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()).getParent();
        File kansio = new File(polku + "/StockData/");

        for (File f : kansio.listFiles()) {
            if (f.getName().equals("testiHtml.txt")) {
                f.delete();
                break;
            }
        }
        if (kansio.listFiles().length == 0) {
            kansio.delete();
        }
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void makeRivitLuoOikeanlaisenListan() throws FileNotFoundException {

        this.parser.makeRivit();

        ArrayList<String> rivit = this.parser.getHalututTrimmaamattomatRivit();

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
        this.parser.setRawData(html);

        String teksti = parser.makeMeaningfulString();
        assertTrue(teksti.substring(0, 31).equals("Date,Open,High,Low,Close,Volume"));

    }
}
