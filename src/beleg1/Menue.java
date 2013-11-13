package beleg1;

import java.io.IOException;
import java.util.Scanner;

/**
 * Die Klasse Menue druckt das Menue, welches die Optionen zur Analyse der
 * Textdatei, enthaelt. Desweiteren werden ueber die Tastatur der Dateiname
 * zur Analyse fuer die Option 10, 11 und 12 erfasst, sowie die min und max
 * Werte zur Begrenzung der Woerterliste.
 * 
 */
public class Menue {
  /**
   *  Wird benutzt, um den Dateinahmen aus dem Kommandozeilenargument zu
   *  uebergeben args[0]
   */
  private String file;
  
  /**
   * Wird benutzt, um den Dateinamen ueber die Tastatur einzulesen
   */
  private String dateiName;
  
  /**
   * Der min Wert, der benutzt wird, um die Woerterliste zu begrenzen
   */
  private int min=0;
  
  /**
   * Der max Wert, der benutzt wird, um die Woerterliste zu begrenzen
   */
  private int max=0;
  
  
  /**
   * Konstruktur der Klasse Menue
   * @param file der Name der einzulesenden Datei
   */
  Menue(String file){
    this.file = file;
  }
  
  DateiHandler r = new DateiHandler(file);
  
  /**
   * Dies Methode  zeigt das Menue
   */
  private void zeigeMenue(){
    System.out.println("---\t Menueauswahl \t----------------------------");
    System.out.println("   [1] Woerter aufsteigend alphabetisch sortiert ");
    System.out.println("   [2] Woerter mit Haeufigkeit von min bis max\n"
                          + "\taufsteigend alphabetisch sortiert");
    System.out.println("   [3] Woerter absteigend alphabetisch sortiert");
    System.out.println("   [4] Woerter mit Haeufigkeit von min bis max\n "
                          + "\tabsteigend alphabetisch sortiert ");
    System.out.println("   [5] Woerter absteigend nach Haeufigkeit\n "
                          + "\tsortiert ");
    System.out.println("   [6] Woerter mit Haeufigkeit von min bis max\n "
                          + "\tabsteigend nach Haeufigkeit sortiert ");
    System.out.println("   [7] Woerter aufsteigend nach Haeufigkeit\n "
                          + "\tsortiert  ");
    System.out.println("   [8] Woerter mit Haeufigkeit von min bis max\n "
                          + "\taufsteigend nach Haeufigkeit sortiert");
    System.out.println("   [9] Anzahl Woerter feststellen");
    System.out.println("  [10] Woerterliste speichern ");
    System.out.println("  [11] Neue Woerterliste erzeugen");
    System.out.println("  [12] Textdatei anzeigen");
    System.out.println("  [13] Programm beenden");
    System.out.println("-------------------------------------------------");
  }
  
  /**
   * Hier wird das Menue aufgerufen. Das Menue wird solange angezeigt bis
   * die 13 zum Beenden ausgewahlt wird. Ueber die Switch Case werden die
   * einzelnen Optionen abgefragt.
   * @throws IOException
   */
  public void auswahltreffen() throws IOException{
    String eingabe = "";
    do{
      zeigeMenue();
      Scanner sc = new Scanner(System.in);
      while(eingabe.matches("^[0-9]+$")== false) {
        System.out.println("Bitte eine Zahl eingeben (1-13):\n");
        eingabe = sc.nextLine();
      }
      switch(Integer.parseInt(eingabe)){
        case 1:
          r.lesen(file);
          r.option(1);
          r.minMax(0, Integer.MAX_VALUE);
          r.AusgabeA_Z(0, Integer.MAX_VALUE);
          eingabe = "";
          break;
        case 2:
          r.lesen(file);
          r.option(2);
          minMaxAngeben();
          r.minMax(min,max);
          r.AusgabeA_Z(min,max);
          eingabe = "";
          break;
        case 3:
          r.lesen(file);
          r.option(3);
          r.minMax(0, Integer.MAX_VALUE);
          r.AusgabeZ_A(0, Integer.MAX_VALUE);
          eingabe = "";
          break;
        case 4:
          r.lesen(file);
          r.option(4);
          minMaxAngeben();
          r.minMax(min,max);
          r.AusgabeZ_A(min,max);
          eingabe = "";
          break;
        case 5:
          r.lesen(file);
          r.option(5);
          r.minMax(0, Integer.MAX_VALUE);
          r.woerterNachHaeufigkeit(0, Integer.MAX_VALUE, true);
          eingabe = "";
          break;
        case 6:
          r.lesen(file);
          r.option(6);
          minMaxAngeben();
          r.minMax(min, max);
          r.woerterNachHaeufigkeit(min, max, true);
          eingabe = "";
          break;
        case 7:
          r.lesen(file);
          r.option(7);
          r.minMax(0, Integer.MAX_VALUE);
          r.woerterNachHaeufigkeit(0, Integer.MAX_VALUE, false);
          eingabe = "";
          break;
        case 8:
          r.lesen(file);
          r.option(8);
          minMaxAngeben();
          r.minMax(min, max);
          r.woerterNachHaeufigkeit(min, max, false);
          eingabe = "";
          break;
        case 9:
          r.ausgabeAktuelleWortanzahlInListe();
          eingabe = "";
          break;
        case 10:
          r.dateiNameWaehlen();
          //r.schreibeWort();
          //r.schreiben();
          eingabe = "";
          break;
        case 11:
          dateiAngeben();
          file = dateiName;
          r.lesen(file);
          eingabe = "";
          break;
        case 12:
          dateiAngeben();
          file = dateiName;
          r.lesen(file);
          r.ausgeben();
          eingabe = "";
          break;
        case 13:
          System.out.println("Programm beendet!");
          System.exit(0);
          break;  
        default:
          System.out.println("Falsche Eingabe!\n"
            + "Bitte eine Zahl eingeben (1-13)!\n");
          eingabe="";
          break;
      }
    } while (!"13".equals(eingabe));
  }
  
  /**
   * In dieser Methode wird der dateiname angegeben, um in Operation 11 eine
   * neue Woerterliste zu erzeugen
   */
  private void dateiAngeben(){
    Scanner sc = new Scanner(System.in);
    this.dateiName = "";
    System.out.println("Bitte geben Sie den Dateinamen an: \n");
    dateiName = sc.nextLine();
  }
  
  
  /**
   * In dieser Methode wird der Min und der Max Wert ueber die Tastatur ein-
   * gelesen um die Woerterliste zu beschraenken
   */
  private void minMaxAngeben(){
    
    Scanner sc = new Scanner(System.in);
    String eingabe;
    boolean eingabeRichtig = false;
    while (eingabeRichtig == false) {
      System.out.println("Geben Sie den Min- Wert an: ");
      try {
        eingabe = sc.nextLine();
        this.min = Integer.parseInt(eingabe);
        eingabeRichtig = true;
      } catch (NumberFormatException e) {
        System.out.println("Ups! Das war keine Zahl.");
        eingabeRichtig = false;
      }
    }
    eingabeRichtig = false;
    
      while (eingabeRichtig == false) {
      System.out.println("Geben Sie den Max- Wert an: ");
      try {
        eingabe = sc.nextLine();
        this.max = Integer.parseInt(eingabe);
        eingabeRichtig = true;
      } 
      catch (NumberFormatException e) {
        System.out.println("Ups! Das war keine Zahl.");
        eingabeRichtig = false;
      }
    }
  }
}
