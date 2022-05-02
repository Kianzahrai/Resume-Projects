package textEditor.core.structures;

public interface List<String> {
    int size();
    
    boolean isEmpty();

    String get(int x) throws IndexOutOfBoundsException;

    String set(int x, String e) throws IndexOutOfBoundsException;

    void add(int x, String e) throws IndexOutOfBoundsException;

    String remove (int x) throws IndexOutOfBoundsException;

    
}
