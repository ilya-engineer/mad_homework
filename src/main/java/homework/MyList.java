package homework;

import java.util.Comparator;
import java.util.Optional;

import java.lang.reflect.Array;
import java.util.Random;

public class MyList<T> implements AdvancedList<T>, AuthorHolder {

    private T[] list;
    private int size;

    @Override
    public String author() {
        return "Abramov Ilya";
    }

    @Override
    public void add(T item) {
        T[] list = (T[])Array.newInstance(Object.class, size + 1);
        if (size != 0){
            for(int i = 0; i < size; i++)
                list[i] = this.list[i];
        }
        list[size] = item;
        size++;
        this.list = list;
    }

    @Override
    public void insert(int index, T item) throws Exception {
        if(index < size) {
            T[] list = (T[]) Array.newInstance(Object.class, size + 1);
            for (int i = size; i > index; i--) {
                list[i] = this.list[i - 1];
            }
            list[index] = item;
            for (int i = 0; i < index; i++) {
                list[i] = this.list[i];
            }
            size++;
            this.list = list;
        }
        else{
            T[] list = (T[]) Array.newInstance(Object.class, index + 1);
            for (int i = 0; i < size; i++) {
                list[i] = this.list[i];
            }
            for (int i = size; i < index; i++) {
                list[i] = null;
            }
            list[index] = item;
            size = index + 1;
            this.list = list;
        }
    }

    @Override
    public void remove(int index) throws Exception {
        T[] list = (T[])Array.newInstance(Object.class, size - 1);
        for(int i = 0; i < index; i++){
            list[i] = this.list[i];
        }
        for (int i = index; i < size - 1; i++) {
            list[i] = this.list[i + 1];
        }
        this.list = list;
        size--;
    }

    @Override
    public Optional<T> get(int index) {
        System.out.println(size);
        return Optional.ofNullable(list[index]);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void addAll(SimpleList<T> list) {
        T[] List = (T[])Array.newInstance(Object.class, size + list.size());
        for(int i = 0; i < size; i++)
            List[i] = this.list[i];
        for(int i = 0; i <list.size(); i++)
            List[i + size] = list.get(i).get();
        this.list = List;
        size += list.size();
    }

    @Override
    public int first(T item) {
        int index = -1;
        for(int i = 0; i < size; i++){
            if(this.list[i].equals(item)) {
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public int last(T item) {
        int index = -1;
        for(int i = size - 1; i >= 0; i--){
            if(this.list[i].equals(item)) {
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public boolean contains(T item) {
        int k = 0;
        for(int i = 0; i < size; i++){
            if(this.list[i].equals(item)){
                k++;
                break;
            }
        }
        if (k == 1)
            return true;
        else
            return false;
    }

    @Override
    public boolean isEmpty() {
        if(this.list == null)
            return true;
        else
            return false;
    }

    @Override
    public AdvancedList<T> shuffle() throws Exception {
        T[] list = (T[]) Array.newInstance(Object.class, size);
        list = this.list;
        Random r = new Random();
        for(int i = size - 1; i > 0; i--){
            T temp = list[i];
            int j = r.nextInt(i);
            list[i] = list[j];
            list[j] = temp;
        }
        MyList List = new MyList<T>(); ;
        for(int i = 0; i < size; i++){
            List.add(list[i]);
        }
        return List;
    }



    @Override
    public AdvancedList<T> sort(Comparator<T> comparator) {
        MyList List = new MyList<T>();
        int in, out;
        T[] list = this.list;
        MyList pr = new MyList<T>();
        for (out = 1; out < size; out++){
            if(list[out] == null)
                continue;
            T temp = list[out];
            in = out;
            while((in > 0) && ((list[in-1] == null) || (comparator.compare(list[in - 1], temp) >= 0))){
                list[in] = list[in - 1];
                --in;
            }
            list[in] = temp;
        }
        for(int i = 0; i < size; i++){
            pr.add(list[i]);
        }
        return pr;
    }
}