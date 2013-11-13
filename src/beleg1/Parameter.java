
package beleg1;

/**
 * Die Klasse Parameter implemnetiert eine Methode, die den Dateinamen, der
 * ueber die Kommandozeile angegebn wurde, ausgibt.
 * @author Jens Fiesselmann (s0539077)
 * @version 1.0
 */
public class Parameter {
  
  private final String dateiName;
   
  public Parameter(String arg0) {
    this.dateiName = arg0;
  }

  /**
   *
   */
  public void zeigeDateiName() {
    System.out.println("Dateiname\t: " + this.dateiName);
  }
}
