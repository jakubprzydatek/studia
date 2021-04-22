public class Main {

    public static void main(String[] args) {

        Spirala spirala = null;
        int number = 5;
        if(args.length > 0)
        {
            number = Integer.parseInt(args[0]);
        }
        try
        {
            spirala = new Spirala(number);
        }
        catch (IllegalArgumentException exception)
        {
            exception.printStackTrace();
            System.out.println("n nie jest w zakresie");
        }

        if(spirala != null)
        {
            spirala.startSpiral();
            spirala.print();
            spirala.printFilledSpiral();
        }

    }
}
