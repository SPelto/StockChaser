/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chaser.logiikka;

import chaser.logiikka.FileReader;
import java.util.ArrayList;
import java.util.Random;
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void workFileLuoOikeanlaistaDataa() {
        FileReader f = new FileReader();
        f.readFile("ExampleData/googl.csv");
        f.workFile();
        Random rand = new Random();
        assertEquals(f.getData().get(rand.nextInt(f.getData().size())).getClass(), new String[6].getClass());
        assertEquals(f.getData().get(1)[2], "855.35");
        assertEquals(f.getData().get(0)[0], "Date");
        
    }

    @Test
    public void tiedostonVaihtaminenOnnistuu() {
        FileReader f = new FileReader();
        f.readFile("ExampleData/googl.csv");
        f.workFile();
        ArrayList<String[]> data1 = f.getData();
        
        f.readFile("ExampleData/msft.csv");
        f.workFile();
        ArrayList<String[]> data2 = f.getData();
        data1 = f.getData();
        
        
        
        Random rand = new Random();
        int listanKoko = Math.min(data1.size(), data2.size());
        int indeksi = rand.nextInt(listanKoko);
        assertEquals(data1.get(indeksi)[5], data2.get(indeksi)[5]);

    }
    
}
