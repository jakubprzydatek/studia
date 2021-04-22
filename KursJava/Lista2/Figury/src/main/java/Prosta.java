public class Prosta {
    public final double a;
    public final double b;
    public final double c;

    public Prosta(double a, double b, double c)
    {
        if(a == 0 & b == 0) throw new IllegalArgumentException("A i B = 0");
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public static Prosta przesunOWektor(Prosta p, Wektor v)
    {
        double tempX1 = 0;
        double tempY1 = (-p.getA() * tempX1 - p.getC()) / p.getB();

        double tempX2 = 1;
        double tempY2 = (-p.getA() * tempX2 - p.getC()) / p.getB();

        Punkt punkt1 = new Punkt(tempX1, tempY1);
        punkt1.przesun(v);
        Punkt punkt2 = new Punkt(tempX2, tempY2);
        punkt2.przesun(v);

        double tempWspA = getWspA(punkt1, punkt2);
        double tempWspB = getWspB(punkt1, punkt2);
        double tempWspC = getWspC(punkt1, punkt2);

        return new Prosta(tempWspA, tempWspB, tempWspC);

    }

    public static boolean czyRownolegle(Prosta prosta1, Prosta prosta2)
    {
        return prosta1.getA() * prosta2.getB() == prosta2.getA() * prosta1.getB();
    }

    public static boolean czyProstopadle(Prosta prosta1, Prosta prosta2)
    {
        return prosta1.getA() * prosta2.getA() == -prosta1.getB() * prosta2.getB();
    }

    public static Punkt punktPrzecieciaProstych(Prosta prosta1, Prosta prosta2)
    {
        if(czyRownolegle(prosta1, prosta2)) return null;
        double W = (prosta1.getA() * prosta2.getB()) - (prosta2.getA() * prosta1.getB());

        double Wx = (-prosta1.getC() * prosta2.getB()) - (-prosta2.getC() * prosta1.getB());

        double Wy = (prosta1.getA() * -prosta2.getC()) - (prosta2.getA() * -prosta1.getC());

        return new Punkt(Wx/W, Wy/W);
    }

    public String toString()
    {
        return this.getA() + "x " + this.getB() + "y " + this.getC();
    }

    public static double getWspA(Punkt a, Punkt b)
    {
        return b.getY() - a.getY();
    }

    public static double getWspB(Punkt a, Punkt b)
    {
        return a.getX() - b.getX();
    }

    public static double getWspC(Punkt a, Punkt b)
    {
        return (b.getX() * a.getY()) - (a.getX() * b.getY());
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }
}
