public class Odcinek implements ObiektGeometryczny {
    private Punkt punkt1;
    private Punkt punkt2;

    public Odcinek(Punkt punkt1, Punkt punkt2)
    {
        if(punkt1.equals(punkt2))
        {
            throw new IllegalArgumentException("Podane punkty nie utworzą odcinka");
        }
        this.punkt1 = punkt1;
        this.punkt2 = punkt2;
    }

    @Override
    public void przesun(Wektor v) {
        punkt1.przesun(v);
        punkt2.przesun(v);
    }

    @Override
    public void obroc(Punkt p, double kat) {
        punkt1.obroc(p, kat);
        punkt2.obroc(p, kat);
    }

    @Override
    public void odbij(Prosta p) {
        punkt1.odbij(p);
        punkt2.odbij(p);
    }

    public static double dlugoscOdcinka(Punkt punkt1, Punkt punkt2)
    {
        return Math.sqrt(Math.pow((punkt2.getX() - punkt1.getX()), 2) + Math.pow((punkt2.getY() - punkt1.getY()), 2));
    }
}
