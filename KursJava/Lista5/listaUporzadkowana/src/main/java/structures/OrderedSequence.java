package structures;

public interface OrderedSequence<T extends Comparable<T>> {
    public void insert(T el) throws ExistsInListException;
    public void remove(T el);
    public T min() throws EmptyListException;
    public T max() throws EmptyListException;
    public T at(int pos) throws EmptyListException;
    public boolean search(T el);
    public int index(T el);

}
