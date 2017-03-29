/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chaser.ui;

import chaser.logiikka.FileHandler;
import java.util.Scanner;

/**
 *
 * @author samuli
 */
public class Ui {

    private Scanner scanner;
    private FileHandler fh;

    public Ui(Scanner scanner) {
        this.scanner = scanner;
        this.fh = new FileHandler();

        mainScreen();
    }

    private void mainScreen() {

        while (true) {
            System.out.println("\nValitse toiminto:\n"
                    + "1) Lataa dataa netistä");

            System.out.print("Valitse: ");
            valinta(scanner.nextLine());

        }

    }

    private void valinta(String valinta) {
        if (valinta.equals("1")) {
            dataaNetista();
        }
    }

    private void dataaNetista() {
        while (true) {
            System.out.println("\nPoistu kirjoittamalla tiedoston nimeksi \"lopeta\"");
            
            System.out.print("\nSyötä haluttu nimi luotavalle tiedostolle: ");
            String fileName = scanner.nextLine();
            if (fileName.equals("lopeta")) {
                break;
            }
            System.out.print("\nSyötä url mistä tieto ladataan: ");
            String url = scanner.nextLine();

            try {
                fh.makeFileFromUrl(fileName, url);
                break;
            } catch (Exception e) {
                System.out.println("Antamassasi syötteessä oli vikaa, yritä uudelleen");
                System.out.println(e);
            }
        }
    }

}
