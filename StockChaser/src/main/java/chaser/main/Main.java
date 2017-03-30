/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chaser.main;

import chaser.logiikka.FileReader;
import chaser.ui.Ui;
import java.util.Scanner;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author sPelto
 */
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Ui kayttis = new Ui(scanner);
    }

    public static void debug() {
        FileReader fr = new FileReader();
        fr.readFile("ExampleData/Ramirent.csv");
        fr.workFile();

        Pattern p = Pattern.compile("\\d+\\.\\d+");
        Matcher m = p.matcher("2.2");
        System.out.println(m.find());
    }
}
