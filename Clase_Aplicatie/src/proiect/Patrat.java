package proiect;

public class Patrat extends FiguraGeometrica implements Aria {
    int latura ;

    public int getLatura() {
        return latura;
    }

    public void setLatura(int latura) {
        this.latura = latura;
    }


    public Patrat(String nume, String culoare,int id, int latura) {
        super(nume, culoare,id);
        this.latura = latura;
    }

    public Patrat(int latura) {
        this.latura = latura;
    }

    public Patrat() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Patrat)) return false;
        Patrat patrat = (Patrat) o;
        return latura == patrat.latura;
    }

    @Override
    public int hashCode() {
        return (int) (latura ^ (latura >>> 32));
    }

    @Override
    public float calculeazaAria() {
        return latura*latura ;
    }

    @Override
    public String toString() {
        return  latura+"";
    }
}
