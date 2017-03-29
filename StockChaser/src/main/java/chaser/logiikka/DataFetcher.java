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

/**
 *
 * @author samuli
 */
public class DataFetcher {

    private String urlString;
    private String sourceString;
    
    public DataFetcher(String url) {
        this.urlString = url;
    }
    
    public DataFetcher() {
        
    }
    
    public void htmlToString(String url) throws MalformedURLException, IOException{
        URL u = new URL(url);
        Scanner scanner = new Scanner(u.openStream());
        
        while(scanner.hasNextLine()) {
            this.sourceString = this.sourceString + scanner.nextLine();
        }
    }

    public String getSourceString() {
        return sourceString;
    }

    public String getUrlString() {
        return urlString;
    }
   

}
