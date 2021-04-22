public class Main {

    public static void main(String[] args) {

        //WEKTOR TESTY
        /* //Test na złożenie wektorów
        Wektor wektor1 = new Wektor(-2, 2);
        Wektor wektor2 = new Wektor(2, -2);
        Wektor wektor = Wektor.skladanieWektora(wektor1, wektor2);
        System.out.println("Wektor po złożeniu wektorów [-2,2] i [2,-2] to" + wektor.toString());
        */

        //PROSTA TESTY
        // Test na przeuniecie prostej o wektor
        Wektor wektor = new Wektor(-2, 0);
        Prosta prosta = new Prosta(-2, -1, 3);
        Prosta prosta1;
        prosta1 = Prosta.przesunOWektor(prosta, wektor);
        System.out.println(prosta1);

        /* //Test na punkt przeciecia prostych
        Prosta prosta1 = new Prosta(4, -1, 2);
        Prosta prosta2 = new Prosta(-3, -1, 9);
        Punkt punkt1 = Prosta.punktPrzecieciaProstych(prosta1, prosta2);
        System.out.println(punkt1);
        */
        /* //Test na prostopadłe
        Prosta prosta1 = new Prosta(4, 1, 2);
        Prosta prosta2 = new Prosta(-2, 8, -8);
        System.out.println(Prosta.czyProstopadle(prosta1, prosta2));
         */
         /*//Test na rownolegle
        Prosta prosta1 = new Prosta(2, -6, 2);
        Prosta prosta2 = new Prosta(1, -3, 4);
        System.out.println(Prosta.czyRownolegle(prosta1, prosta2));*/


        //PUNKT TESTY
        /* //przesun
        Punkt punkt = new Punkt(0,0);
        Wektor wektor = new Wektor(5, -5);
        punkt.przesun(wektor);
        System.out.println(punkt);
         */
        //obroc
        /*Punkt punkt0 = new Punkt(0,0);
        Punkt punkt = new Punkt(3, 3);
        punkt.obroc(punkt0, 90);
        System.out.println(punkt);*/
        //odbij
        /*Punkt punkt1 = new Punkt(0, 0);
        Prosta prosta1 = new Prosta(-2, -1, 3);
        punkt1.odbij(prosta1);
        System.out.println(punkt1);*/

        //TESTY TRÓJKĄT
        /*Punkt punkt1 = new Punkt(-2, 6);
        Punkt punkt2 = new Punkt(6, -2);
        Punkt punkt3 = new Punkt(-3, 9);
        Wektor wektor = new Wektor(2, 2);

        Trojkat trojkat = new Trojkat(punkt1, punkt2, punkt3);
        trojkat.przesun(wektor);
        System.out.println(trojkat);
        */


    }
}
