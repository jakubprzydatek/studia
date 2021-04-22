import structures.ExistsInListException;
import structures.OrderedList;
import tests.CalendarListTest;
import tests.IntegerListTest;
import tests.StringListTest;

public class Main {

    public static void main(String[] args) {

        IntegerListTest.testOnEmptyList();

        IntegerListTest.methodsTest();

        StringListTest.methodsTest();

        CalendarListTest.methodsTest();
    }
}