package com.company;

public class MyException extends Exception{
    public MyException(String errorMessage) {
        System.out.println(errorMessage);
    }
}
