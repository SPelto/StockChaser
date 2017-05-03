/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chaser.logiikka;

import java.util.ArrayList;
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
    public void tearDown() {
    }

    @Test
    public void prosessoiXjaYToimiiOikein() {
        DataHandler dh = new DataHandler();
        FileReader fr = new FileReader();
        fr.readFile("src/test/aputiedostot/testi.csv");
        fr.workFile();
        ArrayList<String[]> data = fr.getData();
        int[] dimensio = new int[]{1000, 700};
        int[] reunat = new int[]{50, 50};
        Mapper m = new Mapper(data, dimensio, reunat);
        m.prosessoiXjaY("Open");
        assertEquals(m.getMapattuDataY()[0], Math.round((1465.00 / 1498.00) * (700.00 - 50.00)));
        
        m.prosessoiXjaY("High");
        assertEquals(m.getMapattuDataY()[0], Math.round((1483.00 / 1504.00) * (700.00 - 50.00)));
        
        m.prosessoiXjaY("Low");
        assertEquals(m.getMapattuDataY()[0], Math.round((1461.00 / 1490.00) * (700.00 - 50.00)));
        
        m.prosessoiXjaY("Close");
        assertEquals(m.getMapattuDataY()[0], Math.round((1483.00 / 1498.00) * (700.00 - 50.00)));

    }

    @Test
    public void valintqaToimiiOikein() {
       

    }

}
