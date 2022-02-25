import java.util.Date;

public class Meritev {  // razred za hranjenje posamezne meritve

    private Date datumCas;  // datum in čas meritve (oboje v isti strukturi, potem pa razdelimo na datum in čas posebej, če bo treba?)
    private float volumen;  // vrednost meritve (voluen)
    private boolean veljavno = false;  // ali je meritev veljavna

    public Meritev(Date datumCas, float volumen) {
        this.datumCas = datumCas;
        this.volumen = volumen;
    }

    public Date vrniDatum() {
        return this.datumCas;
    }

    public float vrniVolumen() {
        return this.volumen;
    }

    public boolean jeVeljavno() {
        return this.veljavno;
    }

    public void nastaviVeljavno(boolean veljavno) {
        this.veljavno = veljavno;
    }

}