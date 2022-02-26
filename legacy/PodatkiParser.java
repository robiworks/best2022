import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class PodatkiParser {

    private static String TXT_NAME = "WWT_VOLUMEN_17_LIT277_.TXT";
    
    // pretvori vrstico datoteke v Meritev
    public static Meritev preberiMeritev(String s) { 
        String[] podatki = s.split("!");
        String datumCas = podatki[0].trim();
        float volumen = Float.parseFloat(podatki[1].trim());

        // pretvori zapisan datum in čas v Date objekt
        Date d;
        try {
            d = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").parse(datumCas);
        } catch (ParseException e) {
            d = new Date();  // TODO mogoče kaj boljšega?
            System.err.printf("Napačen zapis datuma.%n");
        }

        return new Meritev(d, volumen);
    }

    // vrni vse meritve, ki so shranjene v datoteki
    public static ArrayList<Meritev> vrniMeritveIzDatoteke(String filename) {
        BufferedReader bralnik;  // za branje podatkov iz datoteke
        ArrayList<Meritev> meritve = new ArrayList<Meritev>();

        // odpre podano datoteko
        try {
            bralnik = new BufferedReader(new FileReader(filename));

            String vrstica;
            while ((vrstica = bralnik.readLine()) != null) {  // vse do konca datoteke
                Meritev m = preberiMeritev(vrstica);
                meritve.add(m);
            }
            
        } catch (FileNotFoundException e) {
            System.err.printf("Datoteka %s ne obstaja.%n", filename);
        } catch (IOException ioe) {
            System.err.printf("Napaka pri branju datoteke %s.%n", filename);
        }

        return meritve;
    }

    // vrni vse meritve (iz vseh datotek) za posamezno leto
    public static ArrayList<Meritev> vrniVseMeritve(int leto) {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd_MM_yyyy");
        LocalDate trenutni = LocalDate.parse("01_01_" + leto, f);
        LocalDate konec = LocalDate.parse("01_01_" + (leto + 1), f);

        ArrayList<Meritev> meritve = new ArrayList<Meritev>();

        while (trenutni.isBefore(konec)) {
            String datumString = trenutni.format(f);
            String imeDatoteke = "Arhiv_" + leto + "/Arhiv_" + datumString + "/" + TXT_NAME;
            meritve.addAll(vrniMeritveIzDatoteke(imeDatoteke));

            trenutni = trenutni.plusDays(1);
        }

        return meritve;
    }

}
