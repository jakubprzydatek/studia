package com.company.struktury;

import com.company.InsertException;
import com.company.MyException;
import com.company.NoFoundException;
import com.company.struktury.Para;

public abstract class Zbior {
    public abstract Para szukaj (String k) throws MyException, NoFoundException;
    public abstract void wstaw (Para p) throws MyException, InsertException;
    public abstract void usun (String k);
    public abstract double czytaj (String k) throws Exception;
    public abstract void ustaw (Para p) throws Exception;
    public abstract void czysc ();
    public abstract int ile ();
}
