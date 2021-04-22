public class Wektor {

    public final double dx;
    public final double dy;

    public double getDx() {
        return dx;
    }

    public double getDy() {
        return dy;
    }

    public Wektor(double x, double y) {
        this.dx = x;
        this.dy = y;
    }

    public static Wektor skladanieWektora(Wektor w1, Wektor w2)
    {
        double x = w1.getDx() + w2.getDx();
        double y = w1.getDy() + w2.getDy();
        return new Wektor(x, y);
    }

    public String toString()
    {
        return "[" + this.dx + "," +this.dy + "]";
    }
}
