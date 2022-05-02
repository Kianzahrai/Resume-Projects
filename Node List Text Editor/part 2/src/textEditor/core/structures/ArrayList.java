package textEditor.core.structures;

import java.util.Arrays;

public class ArrayList implements List<String> {

    public static final int VOLUME = 1000;
    private String[] stats;
    private int size = 0; 

    public ArrayList() {
        this(VOLUME);
    }

    public ArrayList(int volume) { 
        stats = new String[volume];
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public String[] getStats(){
        return this.stats;
    }



    public void expand() {
        String[] temporary = stats;
        stats = new String[size() + VOLUME];
        for (int x = 0; x < temporary.length; x++) {
            stats[x] = temporary[x];
        }
    }

    @Override
    public String get(int x) throws IndexOutOfBoundsException {
        indexCheck(x, size);
        return stats[x]; 

    }

    @Override
    public String set(int x, String e) throws IndexOutOfBoundsException {
        indexCheck(x, size);
        String temporary = stats[x];
        stats[x] = e;
        return temporary;
    }

    @Override
    public void add(int x, String e) throws IndexOutOfBoundsException {
        

        if (!e.equals("")) {
            indexCheck(x, size + 1);
            if (size == stats.length) {
                throw new IllegalStateException("Array full");
            }

            stats[x] = e.toLowerCase().strip();
            size++;

        }
    }

    @Override
    public String remove(int x) throws IndexOutOfBoundsException {
        indexCheck(x, size);
        String temporary = stats[x];
        for (int y = x; y < size - 1; x++) {
            stats[y] = stats[y + 1];
        }
        stats[size - 1] = null;
        size--;
        return temporary;
    }

    protected void indexCheck(int x, int z) throws IndexOutOfBoundsException {
        if (x < 0 || x >= z) {
            throw new IndexOutOfBoundsException("Illegal index: " + x);
        }
        if (z == stats.length + 1) {
            expand();
        }
    } 

    public boolean search(String[] array, int first, int last, String thisString) {
        if (last >= first){
            int middle = first + (last - first)/2;
            if(array[middle].compareTo(thisString) == 0){ 
                return true;
            }
            if(array[middle].compareTo(thisString) > 0){
                return search(array, first, middle - 1, thisString);
            }else{
                return search(array, middle+1, last, thisString);

            }
        }
        return false;

    }

    public void toFindUnsorted(String[] stats){
        for(int x = 0; x < size-1; x++) {
            for (int y = x + 1; y < size; y++) {
               if(stats[x].compareTo(stats[y])>0) {
                System.out.println("FALSE ENTITY HAS BEEN DISCOVERED!");
               }
            }
         }
    }



    public boolean falseSearch(ArrayList array, String string) {
        for (int x = 0; x < size(); x++) {
            if (array.get(x).equals(string)) {
                return true;
            }
        }
        return false;
    }

}