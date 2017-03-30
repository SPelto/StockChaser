/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chaser.ui;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.Random;
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
public class UiTest {

    public UiTest() {
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
            if (t.getName().equals("UiTesttesti.csv")) {
                t.delete();
            }
        }
    }

    @Test(timeout = 1000)
    public void testaaTiedostonLuku() {

        File kansio = new File("ExampleData/");
        File[] tiedostot = kansio.listFiles();

        Random rand = new Random();
        int arpa = rand.nextInt(tiedostot.length);
        String tiedostonNimi = tiedostot[arpa].getName().substring(0, tiedostot[arpa].getName().length() - 4);

        String syote = "2\n"
                + tiedostonNimi + "\n"
                + "9";

        Scanner simuloituSyote = new Scanner(syote);
        Ui u = new Ui(simuloituSyote);

    }


}
