/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chaser.logiikka;

import chaser.logiikka.FileReader;
import java.io.File;
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
 * @author samuli
 */
public class FileReaderTest {

    public FileReaderTest() {
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
    public void workFileLuoOikeanlaistaDataa() {
        FileReader f = new FileReader();
        
        f.readFile("src/test/aputiedostot/testi.csv");
        f.workFile();

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
    public void workFilePuhdistaaMuuttujan() {
        FileReader f = new FileReader();

        f.readFile("src/test/aputiedostot/testi.csv");

        f.workFile();
        ArrayList<String[]> alkuTyhja = f.getData();

        f.workFile();
        ArrayList<String[]> alkuEiValttamattaTyhja = f.getData();

        int iteraatio = 0;
        for (String[] lista : alkuEiValttamattaTyhja) {
            assertTrue(lista[0].equals(alkuTyhja.get(iteraatio)[0]));
            iteraatio++;
        }
        assertTrue(alkuEiValttamattaTyhja.size() == alkuTyhja.size());
    }

}
