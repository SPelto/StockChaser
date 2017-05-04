/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chaser.logiikka;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
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
public class DataFetcherTest {
    
    public DataFetcherTest() {
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
    public void hakeeOikeanHtmlKoodin() throws MalformedURLException, IOException {
        URL u = new URL("http://www.google.com/finance");
        Scanner scanner = new Scanner(u.openStream());
        String sourceString = "";
        while (scanner.hasNextLine()) {
            sourceString = sourceString + scanner.nextLine();
        }
        sourceString = sourceString.substring(100,200);
        DataFetcher df = new DataFetcher();
        String htmlKoodi = df.getHtml("http://www.google.com/finance").substring(100,200);
        assertTrue(sourceString.equals(htmlKoodi));
    }
}
