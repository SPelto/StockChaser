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
public class MapperTest {

    public MapperTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
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
    public void mapDataToimiiOikein() throws URISyntaxException, IOException {
        String polku = new File(FileMaker.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()).getParent();
        File kansio = new File(polku + "/StockData");
        kansio.mkdir();
        DataHandler dh = new DataHandler(polku);
        dh.makeFileFromUrl("testi", "https://www.google.com/finance/historical?q=NASDAQ%3AMSFT&ei=STILWdCgJNvLsQGwxIKwCg", "0", "30");
        ArrayList<String[]> data = dh.readFile(polku + "/StockData/" + "testi.csv");

        int[] dimensio = new int[]{1000, 700};
        int[] reunat = new int[]{50, 50};
        Mapper m = new Mapper(data, dimensio, reunat);

        m.kuvaaData("Close");
        assertEquals(m.getMapattuDataSuurinY(), m.getDimensio()[1] - m.getReunat()[1]);
        assertEquals(m.getMapattuDataPieninY(), m.getReunat()[1]);

        m.kuvaaData("High");
        assertEquals(m.getMapattuDataSuurinY(), m.getDimensio()[1] - m.getReunat()[1]);
        assertEquals(m.getMapattuDataPieninY(), m.getReunat()[1]);

        m.kuvaaData("Low");
        assertEquals(m.getMapattuDataSuurinY(), m.getDimensio()[1] - m.getReunat()[1]);
        assertEquals(m.getMapattuDataPieninY(), m.getReunat()[1]);

        m.kuvaaData("Open");
        assertEquals(m.getMapattuDataSuurinY(), m.getDimensio()[1] - m.getReunat()[1]);
        assertEquals(m.getMapattuDataPieninY(), m.getReunat()[1]);

    }

}
