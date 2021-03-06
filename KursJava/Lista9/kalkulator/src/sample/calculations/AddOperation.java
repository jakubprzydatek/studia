package sample.calculations;

import java.math.BigInteger;

public class AddOperation extends Operation{
    public AddOperation(Argument arg1, int mode) {
        super(arg1, mode);
    }

    public BigInteger execute()
    {
        return argument1.calculate().add(argument2.calculate());
    }

    public String toString(int mode)
    {
        return execute()+"";
    }
}
