/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chaser.logiikka;

import chaser.logiikka.FileReader;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Samuli Peltonen
 */
public class FileReaderTest {

    private DataHandler dh;
    private FileReader f;
    private String polku;

    public FileReaderTest() {
    }

    @BeforeClass
    public static void setUpClass() throws URISyntaxException, IOException {

    }

    @AfterClass
    public static void tearDownClass() throws URISyntaxException {

    }

    @Before
    public void setUp() throws URISyntaxException, IOException {
        this.polku = new File(FileMaker.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()).getParent();
        this.dh = new DataHandler(polku);
        this.f = new FileReader();
        File kansio = new File(polku + "/StockData");
        kansio.mkdir();
        DataHandler dh = new DataHandler(polku);
        dh.makeFileFromUrl("testi", "https://www.google.com/finance/historical?q=NASDAQ%3AMSFT&ei=STILWdCgJNvLsQGwxIKwCg", "0", "30");
        ArrayList<String[]> data = dh.readFile(polku + "/StockData/" + "testi.csv");

        f.readFile(polku + "/StockData/testi.csv");
        f.workFile();

    }

    @After
    public void tearDown() throws URISyntaxException {
        String polku = new File(FileMaker.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()).getParent();
        File kansio = new File(polku + "/StockData/");

        for (File f : kansio.listFiles()) {
            if (f.getName().equals("testi.csv")) {
                f.delete();
                break;
            }
        }
        if (kansio.listFiles().length == 0) {
            kansio.delete();
        }
    }

    @Test
    public void workFileLuoOikeanlaistaDataa() throws URISyntaxException, IOException {

        assertEquals(f.getData().get(0)[0], "Date");
        assertEquals(f.getData().get(0)[1], "Open");
        assertEquals(f.getData().get(0)[2], "High");
        assertEquals(f.getData().get(0)[3], "Low");
        assertEquals(f.getData().get(0)[4], "Close");
        assertEquals(f.getData().get(0)[5], "Volume");

        Pattern p = Pattern.compile("\\d+\\.\\d+");
        Matcher m;
        for (int i = 1; i < f.getData().size(); i++) {
            for (int j = 1; j < 5; j++) {
                m = p.matcher(f.getData().get(i)[1]);
                assertTrue(m.find());
            }
        }

    }

    @Test
    public void workFilePuhdistaaMuuttujan() throws URISyntaxException, IOException {

        ArrayList<String[]> alkuEiValttamattaTyhja = f.getData();
        ArrayList<String[]> alkuTyhja = f.getData();

        int iteraatio = 0;
        for (String[] lista : alkuEiValttamattaTyhja) {
            assertTrue(lista[0].equals(alkuTyhja.get(iteraatio)[0]));
            iteraatio++;
        }
        assertTrue(alkuEiValttamattaTyhja.size() == alkuTyhja.size());
    }

    @Test
    public void getSuurinArvoToimiiOikein() throws URISyntaxException, IOException {
        dh.makeFileFromUrl("testi", "https://www.google.com/finance/historical?q=NASDAQ%3AMSFT&ei=STILWdCgJNvLsQGwxIKwCg", "0", "30");
        ArrayList<String[]> data = dh.readFile(polku + "/StockData/" + "testi.csv");

        FileReader f = new FileReader();

        f.readFile(polku + "/StockData/testi.csv");
        f.workFile();

        
        //       return this.reader.getSuurinArvo(dataValinta, new File(this.path + "/StockData/" + tiedosto + ".csv"));

        String dataValinta = "High";
        for (String[] s : f.getData()) {
            try {
                if (Double.parseDouble(s[valinta(dataValinta)]) > f.getSuurinArvo(dataValinta, new File(this.polku + "/StockData/testi.csv"))) {
                    assertTrue(false);
                }
            } catch (Exception e) {

            }
        }
        dataValinta = "Open";
        for (String[] s : f.getData()) {
            try {
                if (Double.parseDouble(s[valinta(dataValinta)]) > f.getSuurinArvo(dataValinta, new File(this.polku + "/StockData/testi.csv")))  {
                    assertTrue(false);
                }
            } catch (Exception e) {

            }
        }
        dataValinta = "Close";
        for (String[] s : f.getData()) {
            try {
                if (Double.parseDouble(s[valinta(dataValinta)]) > f.getSuurinArvo(dataValinta, new File(this.polku + "/StockData/testi.csv")))  {
                    assertTrue(false);
                }
            } catch (Exception e) {

            }
        }
        dataValinta = "Low";
        for (String[] s : f.getData()) {
            try {
                if (Double.parseDouble(s[valinta(dataValinta)]) > f.getSuurinArvo(dataValinta, new File(this.polku + "/StockData/testi.csv")))  {
                    assertTrue(false);
                }
            } catch (Exception e) {

            }
        }

    }
    @Test
    public void getPieninArvoToimiiOikein() throws URISyntaxException, IOException {
        dh.makeFileFromUrl("testi", "https://www.google.com/finance/historical?q=NASDAQ%3AMSFT&ei=STILWdCgJNvLsQGwxIKwCg","0", "30");
        ArrayList<String[]> data = dh.readFile(polku + "/StockData/" + "testi.csv");

        FileReader f = new FileReader();

        f.readFile(polku + "/StockData/testi.csv");
        f.workFile();

        
        //       return this.reader.getSuurinArvo(dataValinta, new File(this.path + "/StockData/" + tiedosto + ".csv"));

        String dataValinta = "High";
        for (String[] s : f.getData()) {
            try {
                if (Double.parseDouble(s[valinta(dataValinta)]) < f.getPieninArvo(dataValinta, new File(this.polku + "/StockData/testi.csv"))) {
                    assertTrue(false);
                }
            } catch (Exception e) {

            }
        }
        dataValinta = "Open";
        for (String[] s : f.getData()) {
            try {
                if (Double.parseDouble(s[valinta(dataValinta)]) < f.getPieninArvo(dataValinta, new File(this.polku + "/StockData/testi.csv"))) {
                    assertTrue(false);
                }
            } catch (Exception e) {

            }
        }
        dataValinta = "Close";
        for (String[] s : f.getData()) {
            try {
                if (Double.parseDouble(s[valinta(dataValinta)]) < f.getPieninArvo(dataValinta, new File(this.polku + "/StockData/testi.csv"))) {
                    assertTrue(false);
                }
            } catch (Exception e) {

            }
        }
        dataValinta = "Low";
        for (String[] s : f.getData()) {
            try {
                if (Double.parseDouble(s[valinta(dataValinta)]) < f.getPieninArvo(dataValinta, new File(this.polku + "/StockData/testi.csv"))) {
                    assertTrue(false);
                }
            } catch (Exception e) {

            }
        }

    }

    private int valinta(String dataValinta) {
        int sarakeValinta = -1;

        if (dataValinta.equals("Open")) {
            sarakeValinta = 1;
        }
        if (dataValinta.equals("High")) {
            sarakeValinta = 2;
        }
        if (dataValinta.equals("Low")) {
            sarakeValinta = 3;
        }
        if (dataValinta.equals("Close")) {
            sarakeValinta = 4;
        }
//        if (dataValinta.equals("Volume")) {
//            sarakeValinta = 5;
//        }
        return sarakeValinta;
    }
}
