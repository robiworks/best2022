import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class PodatkiParser {
    
    // pretvori vrstico datoteke v Meritev
    public static Meritev preberiMeritev(String s) { 
        String[] podatki = s.split(" ");
        System.out.printf("%s%n", String.join(", ", podatki));

        // TODO
        return new Meritev(new Date(), 3f);
    }

    public static ArrayList<Meritev> vrniMeritveIzDatoteke(String filename) {
        BufferedReader bralnik;  // za branje podatkov iz datoteke

        // odpre podano datoteko
        try {
            bralnik = new BufferedReader(new FileReader(filename));

            String vrstica;
            while ((vrstica = bralnik.readLine()) != null) {  // vse do konca datoteke
                Meritev m = preberiMeritev(vrstica);
            }
            
        } catch (FileNotFoundException e) {
            System.err.printf("Datoteka %s ne obstaja.%n", filename);
        } catch (IOException ioe) {
            System.err.printf("Napaka pri branju datoteke %s.%n", filename);
        }

        ArrayList<Meritev> meritve = new ArrayList<Meritev>();
        meritve.add(new Meritev(new Date(), 3f));

        System.out.printf("");
        return meritve;
    }

}
