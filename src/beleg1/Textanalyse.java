/*
 * Mit diesem Programm koennen Textdateien eingelesen und analysiert werden.
 * Das Programm extrahiert die einzelnen Woerter aus einer txt Datei und
 * kann diese entsprechend gewaehlter Funktion alphabetisch aufsteigend
 * oder absteigend sortieren. Des weiteren kann die Haeufigkeit der einzelnen
 * Woerter gezaehlt werden. Auch ist eine Sortierung auf- oder absteigend
 * nach Anzahl der Haeufigkeit der einzelnen Woerter moeglich. Die Ausgabe 
 * laesst sich durch einen min- und max Wert begrenzen. 
 */
package beleg1;

import java.io.IOException;

/**
 *
 * @author Jens Fiesselmann (s0539077)
 * @version 1.0 30.10.2013
 * @
 */
public class Textanalyse {
    
    /**
     * das Hauptprogramm ueberprueft, ob die Anzahl der Parameter zutreffend
     * ist. Es muss ein Parameter - die zu untersuchende datei angegeben
     * werden. Werden zu wenig oder zu viel Parameter angegeben, wird eine
     * Anleitung ausgegeben. Es wird die Klasse Menue aufgerufen und der
     * Parameter, der die zu untersuchende Textdatei enthaelt uebergeben.
     * zuletzt wird die Funktion auswahltreffen ausgefuehrt, die das Menue
     * zeigt.
     * @param args das Kommandozeilenargument mit der zu untersuchenden 
     * Textdatei
     */
  
    public static void main(String[] args) throws IOException {
      
      if(args.length != 1) {
        System.out.println("Bitte zu untersuchende Datei als Paramteter "
                + "angeben");
        System.out.println("Syntax: java Textanalyse <beispiel.txt>");
        System.exit(1);
      }
      else {
        new Parameter(args[0]).zeigeDateiName();
      }
      Menue m = new Menue(args[0]);
      m.auswahltreffen();
    }
}
