package com.company;

import com.company.part2.*;

public class Main {

    public static void main(String[] args)
    {
        RisingList<Integer> risingList = new RisingList<Integer>();
        risingList.insert(15);
        risingList.insert(-293);
        risingList.insert(1000);
        risingList.insert(-3);
        risingList.insert(29);
        risingList.delete(1);
        System.out.println(risingList.toString());

        Soldier general = new General();
        Soldier colonel = new Colonel();
        Soldier corporal = new Corporal();
        Soldier sergeant = new Sergeant();

        System.out.println(colonel.compareTo(general));
        System.out.println(corporal.compareTo(sergeant));
    }
}
