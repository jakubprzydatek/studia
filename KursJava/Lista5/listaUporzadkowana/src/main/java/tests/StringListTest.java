package tests;

import structures.EmptyListException;
import structures.ExistsInListException;
import structures.OrderedList;

public class StringListTest {

    public static void methodsTest(){
        OrderedList<String> orderedList = new OrderedList<>();

        try{
            orderedList.insert("A");
            orderedList.insert("AB");
            orderedList.insert("XZ");
            orderedList.insert("ABCD");
            orderedList.insert("XYZ");
        }catch (ExistsInListException e){
            System.out.println("Element już znajduje się na liście");
        }

        System.out.println("Zawartosc listy: ");
        for(String i : orderedList){
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

        System.out.println("Element AB ma indeks: " + orderedList.index("AB"));
        System.out.println("Czy element UFG znajduje sie na liscie: " + orderedList.index("UFG"));

        System.out.println("Usuwamy element pierwszy, srodkowy i ustatni");

        orderedList.remove("A");
        orderedList.remove("ABCD");
        orderedList.remove("XZ");
        System.out.println("Zawartosc listy po usunieciu: ");
        for(String i : orderedList){
            System.out.println(i);
        }

    }
}
