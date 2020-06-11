package proiect;

public class Cerc extends FiguraGeometrica implements Aria {
private final float pi= 3.14f;
private int raza ;

    public Cerc(String nume, String culoare,int id,  int raza) {
        super(nume, culoare,id);
        this.raza = raza;
    }
    public Cerc(){

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cerc)) return false;
        Cerc cerc = (Cerc) o;
        return Float.compare(cerc.pi, pi) == 0 &&
                raza == cerc.raza;
    }

    @Override
    public int hashCode() {
        return  (int) (raza ^ (raza >>> 32));
    }

    public int getRaza() {
        return raza;
    }

    public void setRaza(int raza) {
        this.raza = raza;
    }

    @Override
    public float calculeazaAria() {
        return pi*raza *raza ;
    }

    @Override
    public String toString() {
        return  pi + ", " + raza ;
    }

}
