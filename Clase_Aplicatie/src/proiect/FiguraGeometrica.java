package proiect;

import java.io.Serializable;
import java.util.Objects;

public class FiguraGeometrica implements Comparable<FiguraGeometrica> , Serializable {
    int id ;
    private String nume ;
    private String culoare ;

    public String getNume() {
        return nume;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getCuloare() {
        return culoare;
    }

    public void setCuloare(String culoare) {
        this.culoare = culoare;
    }

    public FiguraGeometrica(String nume, String culoare,int id ) {
        this.id = id ;
        this.nume = nume;
        this.culoare = culoare;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FiguraGeometrica)) return false;
        FiguraGeometrica that = (FiguraGeometrica) o;
        return id == that.id &&
                nume.equals(that.nume) &&
                culoare.equals(that.culoare);
    }

    @Override
    public String toString() {
        return "Figura cu id-ul"+id+" are culoarea "+culoare+" si este un/o  "+nume ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nume, culoare);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        FiguraGeometrica copy = (FiguraGeometrica)super.clone() ;
        copy.id = this.id ;
        copy.culoare=this.culoare ;
        copy.nume=this.nume ;
        return copy ;
    }

    public FiguraGeometrica() {
    }


    @Override
    public int compareTo(FiguraGeometrica figuraGeometrica) {
        return culoare.compareTo(figuraGeometrica.culoare) ;
    }
}
