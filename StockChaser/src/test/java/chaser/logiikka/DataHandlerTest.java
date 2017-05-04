/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chaser.logiikka;

import java.io.File;
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
public class DataHandlerTest {

    private DataHandler dh;

    public DataHandlerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws URISyntaxException {
        File path = new File(DataHandlerTest.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
        this.dh = new DataHandler(path.getParent());
    }

    @After
    public void tearDown() {
    }

    @After
    public void poistaTestitiedostot() {

        File kansio2 = new File("ExampleData/");

        File[] tiedostot2 = kansio2.listFiles();

        for (File t : tiedostot2) {
            if (t.getName().equals("testi.csv") || t.getName().equals("mukaHtml.csv")) {
                t.delete();
            }
        }
    }
}
//    @Test
//    public void DataHandlerLuoTiedoston() throws IOException {
//        String url = "https://www.google.com/finance/historical?q=NASDAQ%3AAAPL&ei=YkkCWdn9EsHDswGB3K_ICg";
//        this.dh.makeFileFromUrl("testi", url);
//
//        File kansio = new File("ExampleData/");
//        File[] tiedostot = kansio.listFiles();
//
//        ArrayList<String> tiedostojenNimet = new ArrayList<>();
//        for (File t : tiedostot) {
//            tiedostojenNimet.add(t.getName());
//        }
//        assertTrue(tiedostojenNimet.contains("testi.csv"));
//    }
//
//    @Test
//    public void DataHandlerLuoOikeanLaisenTiedoston() throws IOException {
//        String url = "file:///home/samuli/StockChaser/StockChaser/src/test/aputiedostot/testiHtml.txt";
//
//        this.dh.makeFileFromUrl("mukaHtml", url);
//
//        File tiedosto = new File("ExampleData/mukaHtml.csv");
//        Scanner scanner = new Scanner(tiedosto);
//
//        String testiString = "";
//        while (scanner.hasNextLine()) {
//            testiString = testiString + scanner.nextLine();
//        }
//        assertTrue(testiString.substring(0, 10).equals("Date,Open,"));
//        assertTrue(testiString.substring(testiString.length() - 10, testiString.length()).equals("28,1556310"));
//
//    }
//
//    @Test
//    public void DataHandlerLukeeTiedoston() {
//        this.dh.readFile("src/test/aputiedostot/testi.csv");
//    }
//}
