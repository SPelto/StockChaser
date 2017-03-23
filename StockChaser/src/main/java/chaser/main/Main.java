/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chaser.main;

import java.util.Scanner;
import chaser.logiikka.FileReader;

/**
 *
 * @author sPelto
 */
public class Main {

    public static void main(String[] args) {
        debug();
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

}
