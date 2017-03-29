/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chaser.main;

import chaser.logiikka.*;
import chaser.ui.Ui;
import java.util.Scanner;
import java.io.IOException;

/**
 *
 * @author sPelto
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Ui kayttis = new Ui(scanner);

    }

    public static void debug() {
        FileReader f = new FileReader();
        f.readFile("ExampleData/googl.csv");
        f.workFile();
        f.printData();

        f.readFile("ExampleData/msft.csv");
        f.workFile();
        f.printData();

        System.out.println(f.getData().get(0)[5]);
    }

    public void testailua() {
        String name = "Ramirent";
        String url = "https://www.google.com/finance/historical?q=HEL%3ARMR1V&ei=-8rbWJGvNISHswHAlITwCg";

        FileHandler handler = new FileHandler();

//        handler.makeFileFromUrl(name, url);
        handler.readF("ExampleData/Ramirent.csv");
    }

}
