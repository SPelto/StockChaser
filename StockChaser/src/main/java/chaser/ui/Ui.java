/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chaser.ui;

import chaser.logiikka.DataHandler;
import java.util.Scanner;

/**
 *
 * @author sPelto
 */
public class Ui {

    private Scanner scanner;
    private DataHandler dh;

    public Ui(Scanner scanner) {
        this.scanner = scanner;
        this.dh = new DataHandler();

        mainScreen();
    }

    private void mainScreen() {

        while (true) {
            System.out.println("\nValitse toiminto:\n"
                    + "1) Lataa dataa netistä\n"
                    + "2) Lue tiedoston data\n"
                    + "9) sammuta");

            System.out.print("Valitse: ");
            boolean sammuta = valinta(scanner.nextLine());
            if (sammuta) {
                break;
            }
        }

    }

    private boolean valinta(String valinta) {
        if (valinta.equals("1")) {
            dataaNetista();
            return false;
        }
        if (valinta.equals("2")) {
            lueData();
            return false;
        }
        if (valinta.equals("9")) {
            return true;
        }
        System.out.println("\nValinta ei kelpaa (kirjoita toiminnon numero)");

        return false;
    }

    private void dataaNetista() {
        while (true) {
            System.out.println("\nPoistu kirjoittamalla tiedoston nimeksi \"lopeta\"");

            System.out.print("\nSyötä haluttu nimi luotavalle tiedostolle: ");
            String fileName = scanner.nextLine();
            if (fileName.equals("lopeta")) {
                break;
            }
            System.out.print("\nSyötä url mistä data ladataan: ");
            String url = scanner.nextLine();

            try {
                dh.makeFileFromUrl(fileName, url);
                break;
            } catch (Exception e) {
                System.out.println("Antamassasi syötteessä oli vikaa, yritä uudelleen");
                System.out.println(e);
            }
        }
    }

    public void lueData() {
        while (true) {
            System.out.println("\nAnna tiedoston nimi: ");
            try {
                this.dh.readFile("ExampleData/" + scanner.nextLine() + ".csv");
            } catch (Exception e) {
                System.out.println("\nAntamasi tiedostonimi ei kelpaa. (Ei tarvitse kirjoittaa polkua eikä tiedostopäätettä. Vain nimi!)");
                continue;
            }
            break;
        }
    }

}
