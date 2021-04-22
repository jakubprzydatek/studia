package com.company.testy;

import com.company.struktury.Para;

public class ParaTest {
    public static void testIllegalArguments()
    {
        String illegalArgument = "Test";
        try{
            Para para = new Para(illegalArgument, 10);
        }catch (IllegalArgumentException e)
        {
            System.out.println("Podany klucz zawiera nielegalny znak");
        }
    }

    public static void testMethods()
    {
        Para para = new Para("test", 10);
        Para para1 = new Para("test", 20);
        System.out.println(para.equals(para1));
        System.out.println(para.getWartosc());
        para.setWartosc(334);
        System.out.println(para.toString());

    }

}
