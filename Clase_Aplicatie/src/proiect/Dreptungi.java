package proiect;

public class Dreptungi extends FiguraGeometrica implements Aria {
    int lungime ;
    int latime ;

    public int getLungime() {
        return lungime;
    }

    public void setLungime(int lungime) {
        this.lungime = lungime;
    }

    public int getLatime() {
        return latime;
    }

    public void setLatime(int latime) {
        this.latime = latime;
    }

    public Dreptungi(String nume, String culoare, int lungime,int id,  int latime) {
        super(nume, culoare,id);
        this.lungime = lungime;
        this.latime = latime;
    }

    public Dreptungi() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dreptungi)) return false;
        Dreptungi dreptungi = (Dreptungi) o;
        return lungime == dreptungi.lungime &&
                latime == dreptungi.latime;
    }

    @Override
    public int hashCode() {
       return (int) (lungime ^ (lungime >>> 32)^(latime ^ (latime >>> 32)));
    }

    @Override
    public float calculeazaAria() {
        return lungime*latime ;
    }

    @Override
    public String toString() {
        return lungime + ", " + latime ;
    }

}
