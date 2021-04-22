package com.company.struktury;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class Para {
    public final String klucz;
    private double wartosc;

    public Para(String klucz, double wartosc)
    {
        if(klucz.equals("") || klucz == null)
        {
            throw new IllegalArgumentException("Nieprawidłowy argument klucza");
        }
        if(!checkIfOnlyLegalLetters(klucz)) throw new IllegalArgumentException("Nieprawidłowy argument klucza");
        this.klucz = klucz;
        this.wartosc = wartosc;
    }

    public double getWartosc() {
        return wartosc;
    }

    public void setWartosc(double wartosc) {
        this.wartosc = wartosc;
    }

    @Override
    public String toString() {
        return "Para{" +
                "klucz='" + klucz + '\'' +
                ", wartosc=" + wartosc +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Para)) return false;
        Para para = (Para) o;
        return klucz.equals(para.klucz);
    }

    @Override
    public int hashCode() {
        return Objects.hash(klucz, wartosc);
    }

    public static boolean checkIfOnlyLegalLetters(String stringToCheck)
    {
        int length = stringToCheck.length();
        byte[] bytes = stringToCheck.getBytes(StandardCharsets.US_ASCII);
        int value;
        for(int i = 0; i < length; i++)
        {
            if(bytes[i] < 97 || bytes[i] > 122)
            {
                return false;
            }

        }
        return true;
    }
}
