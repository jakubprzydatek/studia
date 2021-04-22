public class Punkt implements ObiektGeometryczny {
    private double x, y;

    public Punkt(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void przesun(Wektor v)
    {
        this.x += v.getDx();
        this.y += v.getDy();
    }

    @Override
    public void obroc(Punkt p, double kat) {
        kat = Math.toRadians(kat);
        double tmpX = (this.x - p.getX()) * Math.cos(kat) - (this.y - p.getY()) * Math.sin(kat) + p.getX();
        double tmpY = (this.x - p.getX()) * Math.sin(kat) + (this.y - p.getY()) * Math.cos(kat) + p.getY();
        this.x = tmpX;
        this.y = tmpY;
    }

    @Override
    public void odbij(Prosta p) {

        double a = p.getB() / p.getA();
        double b = this.getY() - a * this.getX();

        double srodekX = -1 * (p.getC() + p.getB() * b) / (p.getB() * a + p.getA());
        double srodekY = a * srodekX + b;

        this.x = 2 * srodekX - this.getX();
        this.y = 2* srodekY - this.getY();

    }

    public String toString()
    {
        return "X: " + getX() + " Y: " + getY();
    }



    public boolean equals(Punkt point)
    {
        return this.x == point.getX() && this.y == point.getY();
    }
}
