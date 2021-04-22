public interface ObiektGeometryczny {

    void przesun(Wektor v);
    void obroc(Punkt p, double kat);
    void odbij(Prosta p);
}
