package tests;

import structures.EmptyListException;
import structures.ExistsInListException;
import structures.OrderedList;

public class IntegerListTest {

    public static void testOnEmptyList(){
        OrderedList<Integer> orderedList = new OrderedList<>();

        orderedList.remove(10); // kiedy nie znajduje elementu, nie robi nic
        System.out.println(orderedList.index(2)); // jesli nie znajdzie zwraca -1
        try{
            System.out.println(orderedList.at(1)); //na pustej liscie EmptyListException
        }catch (EmptyListException e){
            System.out.println("Lista jest pusta");
        }

        try{
            System.out.println(orderedList.min()); //na pustej liscie EmptyListException
        }catch (EmptyListException e){
            System.out.println("Lista jest pusta");
        }

        try{
            System.out.println(orderedList.max()); //na pustej liscie EmptyListException
        }catch (EmptyListException e){
            System.out.println("Lista jest pusta");
        }

        System.out.println(orderedList.search(8)); //na pustej liscie zawsze false

        try{
            orderedList.insert(null);
        }catch (NullPointerException | ExistsInListException e){
            System.out.println("Nie można do listy wsadzić elementu null");
        }

        try{
            orderedList.insert(1);
        }catch (ExistsInListException e){
            System.out.println("Element juz znajduje sie w liscie");
        }

        try{
            orderedList.insert(1); // drugi raz probujemy wsadzic te sama wartosc do listy
        }catch (ExistsInListException e){
            System.out.println("Element juz znajduje sie w liscie");
        }
    }

    public static void methodsTest(){
        OrderedList<Integer> orderedList = new OrderedList<>();

        try{
            orderedList.insert(-8);
            orderedList.insert(30);
            orderedList.insert(15);
            orderedList.insert(8);
            orderedList.insert(100);
        }catch (ExistsInListException e){
            System.out.println("Element już znajduje się na liście");
        }

        System.out.println("Zawartosc listy: ");
        for(Integer i : orderedList){
            System.out.println(i);
        }

        try{
            System.out.println("Min: " + orderedList.min());
            System.out.println("Max: " + orderedList.max());
        }catch (EmptyListException e){
            System.out.println("Lista jest pusta");
        }

        try{
            System.out.println("Indeks 0: " + orderedList.at(0));
            System.out.println("Indeks 4: " + orderedList.at(4));
            System.out.println("Indeks 2: " + orderedList.at(2));
        }catch (EmptyListException e){
            System.out.println("Lista jest pusta");
        }

        try{
            System.out.println("Indeks -1:" + orderedList.at(-1));
            System.out.println("Indeks 5:" + orderedList.at(5));
        }catch (IndexOutOfBoundsException | EmptyListException e){
            System.out.println("Indeks poza tablicą");
        }

        System.out.println("Element 30 ma indeks: " + orderedList.index(30));
        System.out.println("Czy element 27 znajduje sie na liscie: " + orderedList.index(27));

        System.out.println("Usuwamy element pierwszy, srodkowy i ustatni");

        orderedList.remove(-8);
        orderedList.remove(15);
        orderedList.remove(100);
        System.out.println("Zawartosc listy po usunieciu: ");
        for(Integer i : orderedList){
            System.out.println(i);
        }

    }
}
