
package beleg1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeMap;


/**
 * In dieser Klasse wird die Textdatei, die analysiert werden soll, eingelesen.
 * Desweiteren werden ueber eine TreeMap die Woerter gezaehlt und es kann in
 * Datei Geschrieben werden.
 */
public class DateiHandler {
  /**
   *  Der einzulesende Dateiname.
   */
  private String datei;
  
  /**
   *  String zum Speichern der Woerter ohne Sortierung.
   */
  private String inhalt_roh="";
  
  /**
   *  ArrayList zum Speichern des Inhalts der eingelesen Datei.
   */
  private ArrayList<String> inhalt;
  
  /**
   *  TreeMap zum Sortieren und Zaehlen der Woerterliste.
   */
  private TreeMap <String, Integer> wortanzahl_map; 
  
  /**
   * Wird benutzt zum Hochzaehlen der Woerteranzahl nach jedem 
   * Schleifendurchlauf.
   */
  private int aktuelleWortanzahl;
  
  /**
   * Wird benutzt, um beim Schreiben in Datei festzustellen, welche Operation
   * zuvor durchgefuehrt wurde.
   */
  private int aktuelleOption;
  
  /**
   *  Wird nebutzt, um beim Schreiben in Datei festzustellen, welcher min 
   *  Wert zuvor festgelegt wurde. 
   */
  private int aktuellesMin;
  
  /**
   *  Wird nebutzt, um beim Schreiben in Datei festzustellen, welcher max 
   *  Wert zuvor festgelegt wurde. 
   */
  private int aktuellesMax;
  
  DateiHandler(String datei){
    this.datei = datei;
  }
  
  /**
   * In dieser Methode wird die textdatei eingelesen. Der String Builder wird
   * benutzt, da die Datei dadurch wesentlich schneller eingelesen wird. Mit 
   * dem String Tokenizer werden die Woerter getrennt und jegliche unerwuenschte
   * Zeichen werden entfernt. Woerter muessen Buchstaben inklusive Umlaute sein
   * die groesse als ein Zeichen sind.
   * @param file Die einzulesende Datei
   */
  public void lesen(String file){
    this.inhalt = new ArrayList();
    StringBuilder builder = new StringBuilder();
    try {
      BufferedReader br = new BufferedReader(new FileReader(file));
      String zeile ;
      br.read();
      while ((zeile = br.readLine()) != null) {
          builder.append(zeile);
          builder.append("\n");
      }
      this.inhalt_roh = builder.toString();
      StringTokenizer inhalt4inhalt = new StringTokenizer(this.inhalt_roh,
        "§$«»<>.,_+!?;:\"\n ()[]");
      while (inhalt4inhalt.hasMoreElements()){
        String tempWort = inhalt4inhalt.nextElement().toString() ;
        if(tempWort.length()>1 && tempWort.matches("[a-zA-ZäÄüÜöÖß]*")) {
          this.inhalt.add(tempWort);
        }
      }
    }
    catch (IOException e) {
      System.out.println("\nDie angegebene Datei konnte nicht gefunden "
        + "werden.\n"
        + "Bitte ueberpruefen Sie ihre Eingabe\n"
        + "Syntax: beispiel.txt\n");
    }
  }
  
   /**
   * In dieser methode wird die Textdatei 1:1 auf dem Bildschirm ausgegeben.
   */
  public void ausgeben(){
       System.out.print(this.inhalt_roh);
   }
  
  /**
   * In dieser Methode wird durch die ArrayListe inhalt iteriert und die Werte
   * in die Treemap geschrieben. Die Worte werden gezaehlt.
   */
  private void zaehleWortanzahl(){
    for(String wort : this.inhalt){
      if(!wortanzahl_map.containsKey(wort)) {
        wortanzahl_map.put(wort, 1);
      }
      else {
        wortanzahl_map.put(wort,wortanzahl_map.get(wort)+1);
      }
    }
  }
      
  /**
   * Diese Methode gibt die Wortanzahl fuer Option 9 aus.
   */
  public void ausgabeAktuelleWortanzahlInListe(){
    System.out.println("Die Liste enthält "+ this.aktuelleWortanzahl 
      + " Wörter.");
  }
  
  /**
   * In dieser Methode wird durch die TreeMap iteriert und die Formatierung 
   * wird ueber printf realisiert.
   * @param min der min Wert
   * @param max der max Wert
   */
  private void druckeWort(int min, int max){
    System.out.println("\nErgebnis der Analyse der Datei " +datei +":\n");
    this.aktuelleWortanzahl = 0;
    for(String wort : wortanzahl_map.keySet()){ 
      if(wortanzahl_map.get(wort)> min && wortanzahl_map.get(wort)< max) {
        System.out.printf("%-40s", wort) ;
        System.out.printf("%10s", wortanzahl_map.get(wort));
        System.out.println("");
        this.aktuelleWortanzahl++;
      }
    }     
  }
  
  /**
   * Diese Methode dient dazu die aktuelle Option zu uebergebn, damit 
   * die zuvor ausgewaehlte Option in Datei geschrieben werden kann.
   * @param option die jeweilige Operation, die zuvor ausgefuehrt wurde
   */
  public void option(int option){
    aktuelleOption = option;
    }
    
  /**
   * Diese Methode setzt die aktuellen min und max Werte, damit diese beim
   * in Datei schreiben bekannt sind.
   * @param min aktueller min Wert
   * @param max aktueller max Wert
   */
  public void minMax(int min, int max){
    aktuellesMin = min;
    aktuellesMax = max;
  }
  
  /**
   * Diese Methode dient zur alphabetisch aufsteigenden Ausgabe. Um die Woerter
   * zu zaehlen wird die Methode zaehleWotanzahl aufgerufen. Fuer die 
   * formatierte Ausgabe wird die Methode druckeWort aufgerufen.
   * @param min
   * @param max
   */
  public void AusgabeA_Z(int min , int max){
    this.wortanzahl_map = new TreeMap();
    zaehleWortanzahl();
    druckeWort(min, max);
  }
  
  /**
   * Diese Methode dient zur alphabetisch absteigenden Ausgabe. Um die Woerter
   * zu zaehlen wird die Methode zaehleWotanzahl aufgerufen. Fuer die
   * formatierte Ausgabe wird die Methode druckeWort aufgerufen.
   * @param min
   * @param max
   */
  public void AusgabeZ_A(int min , int max){
    this.wortanzahl_map = new TreeMap(Collections.reverseOrder());
    zaehleWortanzahl();
    druckeWort(min, max);      
  }
  
  /**
   * Diese Methode gibt die Woerterliste sortiert mit Begrenzung durch min und
   * max Wert, sowie entweder auf -oder absteigend sortiert aus.
   * @param min der min Wert
   * @param max der max Wert
   * @param aufsteigend wenn aufsteigend false ist wird absteigend sortiert.
   */
  public void woerterNachHaeufigkeit(int min , int max, boolean aufsteigend){
    this.wortanzahl_map = new TreeMap();
    zaehleWortanzahl();
    System.out.println("\nErgebnis der Analyse der Datei " +this.datei +":\n");
    this.aktuelleWortanzahl = 0;
    LinkedList<Integer> list = new LinkedList();
    list.addAll(this.wortanzahl_map.values());
    if(aufsteigend == true) {
      Collections.sort(list, Collections.reverseOrder());
    }
    else if (aufsteigend == false) {
      Collections.sort(list);
    }
    int last = -1;
    for (Integer i : list) { 
      if (last == i) {
        continue;
      }
      last = i;
        for (String wort : this.wortanzahl_map.keySet()) { 
          if (this.wortanzahl_map.get(wort) == i 
             && wortanzahl_map.get(wort)> min && wortanzahl_map.get(wort)< max){
             System.out.printf("%-40s", wort) ;
             System.out.printf("%10s", wortanzahl_map.get(wort));
             System.out.println("");
             this.aktuelleWortanzahl++;
          }
        }
    }
  }
  
  /**
   * Diese Methode wird verwendet, um den Dateinamen zu erfassen, in den die
   * jeweilige Wortliste geschrieben wwerden soll. Die Funktion ruft die Methode
   * schreiben auf.
   * @throws IOException
   */
  public void dateiNameWaehlen() throws IOException{
    String neu;
    Scanner sc = new Scanner(System.in);
    //String dateiName = "";
    System.out.println("Bitte geben Sie den Dateinamen an: \n"
            + "Syntax: beispiel.txt");
    String dateiName = sc.nextLine();
    if (dateiName.contains(".txt")){
      System.out.println("\nSchreibe in: " +dateiName);
    }
    else if (!dateiName.contains(".txt")){
      neu =dateiName.concat(".txt");
      System.out.println("\nIhre Eingabe wurde ergaenzt: " + neu);
      dateiName =neu;
    }
    schreiben(dateiName);
  }
  
  /**
   * Diese Methode schreibt je nach zuvor gewaehlter Operation in Datei.
   * @param dateiName Dateiname,der ueber die Tastatur erfasst wurde
   * @throws IOException
   */
  private void schreiben(String dateiName) throws IOException {
    String auswahl; 
    File file = new File(dateiName);
    if(file.exists()){
      System.out.println(dateiName + " bereits vorhanden\n"
        + "Moechten Sie die Datei ueberschreiben? j/n");
      Scanner sc = new Scanner(System.in);
      auswahl = sc.nextLine();
      if (!auswahl.contentEquals("j")){
        dateiNameWaehlen();
      }
    }
    else {
      try (PrintWriter pW = new PrintWriter (dateiName)) {
        if (aktuelleOption == 1 || aktuelleOption ==2 || aktuelleOption ==3
           ||aktuelleOption==4) {
          if(aktuelleOption==3 || aktuelleOption==4){
            this.wortanzahl_map = new TreeMap(Collections.reverseOrder());
          }
          else{
            this.wortanzahl_map = new TreeMap();
          }
          zaehleWortanzahl();
          for(String wort : wortanzahl_map.keySet()){ 
            if(wortanzahl_map.get(wort)> aktuellesMin && 
              wortanzahl_map.get(wort)< aktuellesMax){
              pW.printf("%-40s", wort);
              pW.printf("%10s", wortanzahl_map.get(wort));
              pW.println("");
            }
          }
        }
        else {
          this.wortanzahl_map = new TreeMap();
          zaehleWortanzahl();
          LinkedList<Integer> list = new LinkedList();
          list.addAll(this.wortanzahl_map.values());
          if(aktuelleOption == 5 || aktuelleOption ==6) {
            Collections.sort(list, Collections.reverseOrder());
          }
          else {
            Collections.sort(list);
          }
          int last = -1;
          for (Integer i : list) { 
            if (last == i) {
              continue;
            }
            last = i;
            for (String wort : this.wortanzahl_map.keySet()) { 
              if(this.wortanzahl_map.get(wort) == i 
                && wortanzahl_map.get(wort)> aktuellesMin 
                && wortanzahl_map.get(wort)< aktuellesMax) {
                  pW.printf("%-40s", wort) ;
                  pW.printf("%10s", wortanzahl_map.get(wort));
                  pW.println("");
              }
            }
          }
        }
      }
      catch (Exception e){
        System.out.println("Ups! Da ist ein Fehler aufgetreten: " 
          +e.getMessage());
      }
    }
  }
}

    
  
   
  
    
  


  
