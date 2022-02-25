import java.util.Date;

public class Meritev {  // razred za hranjenje posamezne meritve

    private Date datumCas;  // datum in čas meritve
    private float volumen;  // vrednost meritve (voluen)

    public Meritev(Date datumCas, float volumen) {
        this.datumCas = datumCas;
        this.volumen = volumen;
    }

}