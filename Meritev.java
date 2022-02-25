import java.util.Date;

public class Meritev {  // razred za hranjenje posamezne meritve

    private Date datumCas;  // datum in čas meritve (oboje v isti strukturi, potem pa razdelimo na datum in čas posebej, če bo treba?)
    private double volumen;  // vrednost meritve (voluen)
    private boolean veljavno = false;  // ali je meritev veljavna

    public Meritev(Date datumCas, float volumen) {
        this.datumCas = datumCas;
        this.volumen = volumen;
    }

    public Date vrniDatum() {
        return this.datumCas;
    }

    public double vrniVolumen() {
        return this.volumen;
    }

    public boolean jeVeljavno() {
        return this.veljavno;
    }

    public void nastaviVeljavno(boolean veljavno) {
        this.veljavno = veljavno;
    }

    public int validate(double volume) {
        // explanations:
        // -1: INVALID INCREASE
        // 0: VALID
        // 1: VALID - INCREASE
        
        // if the difference between the volume and the previous volume is negative, it is valid
        if (this.volumen - volume < 0) {
            this.nastaviVeljavno(true);
            return 0;
        }
        // if the difference between the volume and previous volume is > 3 && <= 10, it is valid
        else if (this.volumen - volume > 3 && this.volumen - volume <= 10) {
            return 1;
        }
        // if the difference between the volume and previous volume is > 10, it is invalid
        else {
            return -1;
        }

    }

}