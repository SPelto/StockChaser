/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chaser.logiikka;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
public class FileMakerTest {

    public FileMakerTest() {
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

    @After
    public void poistaTestitiedostot() {
        File kansio = new File("ExampleData/");

        File[] tiedostot = kansio.listFiles();

        for (File t : tiedostot) {
            if (t.getName().equals("testi.csv")) {
                t.delete();
            }
        }
    }

    @Test
    public void tiedostoLuodaan() throws IOException {
        FileMaker fm = new FileMaker();
        fm.makeFile("testi", "testi");

        File kansio = new File("ExampleData/");
        File[] tiedostot = kansio.listFiles();

        ArrayList<String> tiedostojenNimet = new ArrayList<>();
        for (File t : tiedostot) {
            tiedostojenNimet.add(t.getName());
        }
        assertTrue(tiedostojenNimet.contains("testi.csv"));

    }

    @Test
    public void konstruktoriToimiiOikein() {
        FileMaker fm = new FileMaker("ExampleData/");

        assertEquals(fm.getPath(), "ExampleData/");
    }

    @Test
    public void palauttaaTiedoston() throws IOException {
        FileMaker fm = new FileMaker("ExampleData/");
        
        File tiedosto = fm.makeFile("testi", "testitesti");
        
        assertTrue(tiedosto.isFile());
    }

}
