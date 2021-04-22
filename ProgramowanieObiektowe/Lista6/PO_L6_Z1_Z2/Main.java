package com.company;

public class Main {

    public static void main(String[] args) {

        OrderList<Integer> orderList = new OrderList<>();
        orderList.add(5);
        orderList.add(6);
        orderList.add(28);
        orderList.add(1);
        orderList.add(7);
        orderList.add(0);
        orderList.add(100);
        orderList.add(-8);
        System.out.println("Zbior 1");
        orderList.printList();

        OrderList<Integer> orderList2 = new OrderList<>();
        orderList2.add(5);
        orderList2.add(6);
        orderList2.add(28);
        orderList2.add(1);
        orderList2.add(7);
        System.out.println("Zbior 2");
        orderList2.printList();


        System.out.println("--------Interfejs Collection zapewnia faktyczne dzialania jak na zbiorach--------");
        orderList.retainAll(orderList2);
        System.out.println("Czesc wspolna zbiorow 1 i 2");
        orderList.printList();
        orderList.removeAll(orderList2);
        System.out.println("Roznica zbiorow 1 i 2 po operacji czesci wspolnej");
        orderList.printList();
        orderList.addAll(orderList2);
        System.out.println("Suma zbiorow 1 i 2");
        orderList.printList();


        System.out.println("--------Interfejs Collection pozwala sprawdzic wlasciwosci typowe dla zbioru--------");
        System.out.println("Rozmiar zbioru 1 po operacjach wykonanych wyzej: ");
        System.out.println(orderList.size());
        System.out.println("Sprawdzenie czy zbior jest pusty");
        System.out.println(orderList.isEmpty());


        System.out.println("--------Interfejs Collection umozliwia uzywanie petli for na elementach zbioru--------");

        for(Integer t : orderList)
        {
            System.out.println(t);
        }

        System.out.println("--------Reczne iterowanie po kolekcji--------");
        CustomIterator<Integer> customIterator = (CustomIterator<Integer>) orderList.iterator();
        System.out.println("hasNext umozliwia sprawdzenie, czy mozna dalej iterowac po tablicy");
        System.out.println(customIterator.hasNext());
        System.out.println("Pierwsza iteracja");
        System.out.println(customIterator.next());
        System.out.println("Kolejne dwie iteracje");
        System.out.println(customIterator.next());
        System.out.println(customIterator.next());









    }
}
