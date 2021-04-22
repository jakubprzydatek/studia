package tests;

import structures.EmptyListException;
import structures.ExistsInListException;
import structures.OrderedList;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarListTest {

    public static void methodsTest(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd");
        Calendar calendar1 = new GregorianCalendar(2013, Calendar.JULY,25);
        Calendar calendar2 = new GregorianCalendar(2016, Calendar.JANUARY,8);
        Calendar calendar3 = new GregorianCalendar(2001, Calendar.FEBRUARY,31);
        Calendar calendar4 = new GregorianCalendar(2020, Calendar.JANUARY,3);
        Calendar calendar5 = new GregorianCalendar(2010, Calendar.APRIL,11);
        Calendar calendar6 = new GregorianCalendar(2010, Calendar.AUGUST,8);

        OrderedList<Calendar> orderedList = new OrderedList<>();

        try{
            orderedList.insert(calendar1);
            orderedList.insert(calendar2);
            orderedList.insert(calendar3);
            orderedList.insert(calendar4);
            orderedList.insert(calendar5);
        }catch (ExistsInListException e){
            System.out.println("Element już znajduje się na liście");
        }

        System.out.println("Zawartosc listy: ");
        for(Calendar i : orderedList){
            System.out.println(sdf.format(i.getTime()));
        }

        try{
            System.out.println("Min: " + sdf.format(orderedList.min().getTime()));
            System.out.println("Max: " + sdf.format(orderedList.max().getTime()));
        }catch (EmptyListException e){
            System.out.println("Lista jest pusta");
        }

        try{
            System.out.println("Indeks 0: " + sdf.format(orderedList.at(0).getTime()));
            System.out.println("Indeks 4: " + sdf.format(orderedList.at(4).getTime()));
            System.out.println("Indeks 2: " + sdf.format(orderedList.at(2).getTime()));
        }catch (EmptyListException e){
            System.out.println("Lista jest pusta");
        }

        try{
            System.out.println("Indeks -1:" + orderedList.at(-1));
            System.out.println("Indeks 5:" + orderedList.at(5));
        }catch (IndexOutOfBoundsException | EmptyListException e){
            System.out.println("Indeks poza tablicą");
        }

        System.out.println("Element AB ma indeks: " + orderedList.index(calendar2));
        System.out.println("Czy element UFG znajduje sie na liscie: " + orderedList.index(calendar6));

        System.out.println("Usuwamy element pierwszy, srodkowy i ustatni");

        orderedList.remove(calendar3);
        orderedList.remove(calendar1);
        orderedList.remove(calendar4);
        System.out.println("Zawartosc listy po usunieciu: ");
        for(Calendar i : orderedList){
            System.out.println(sdf.format(i.getTime()));
        }

    }
}
