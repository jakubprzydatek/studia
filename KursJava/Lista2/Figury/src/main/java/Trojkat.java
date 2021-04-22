public class Trojkat implements ObiektGeometryczny {
    private Punkt punkt1;
    private Punkt punkt2;
    private Punkt punkt3;

    public Trojkat(Punkt punkt1, Punkt punkt2, Punkt punkt3)
    {
        double a = Odcinek.dlugoscOdcinka(punkt1, punkt2);
        double b = Odcinek.dlugoscOdcinka(punkt1, punkt3);
        double c = Odcinek.dlugoscOdcinka(punkt2, punkt3);
        if((punkt1.getX() == punkt2.getX() && punkt1.getX() == punkt3.getX()) || (punkt1.getY() == punkt2.getY() && punkt1.getY() == punkt3.getY()))
        {
            throw new IllegalArgumentException("Podane punkty są współliniowe");
        }else if(!((a+b)>c && (a+c)>b && (b+c)>a))
        {
            throw new IllegalArgumentException("Podane punkty nie utworzą trójkąta");
        }
        else{
            this.punkt1 = punkt1;
            this.punkt2 = punkt2;
            this.punkt3 = punkt3;
        }
    }

    @Override
    public void przesun(Wektor v) {
        punkt1.przesun(v);
        punkt2.przesun(v);
        punkt3.przesun(v);
    }

    @Override
    public void obroc(Punkt p, double kat) {
        punkt1.obroc(p, kat);
        punkt2.obroc(p, kat);
        punkt3.obroc(p, kat);

    }

    @Override
    public void odbij(Prosta p) {
        punkt1.odbij(p);
        punkt2.odbij(p);
        punkt3.odbij(p);

    }

    public String toString()
    {
        return this.punkt1.toString() + " " + this.punkt2.toString() + " " + this.punkt3.toString();
    }
}
