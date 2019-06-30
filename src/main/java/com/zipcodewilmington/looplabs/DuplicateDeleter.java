package com.zipcodewilmington.looplabs;

import java.util.Arrays;


/**
 * Created by leon on 1/25/18.
 */
public abstract class DuplicateDeleter<T> implements DuplicateDeleterInterface<T> {
    protected T[] array;
    private T[] permArray;

    public DuplicateDeleter(T[] intArray) {
        this.array = intArray;
        this.permArray = intArray;
    }

    public T[] removeDuplicates(int maxNumberOfDuplications){
        this.array = permArray.clone();

        for (T t : permArray){
            if (countDups(t) >= maxNumberOfDuplications){
                removeValue(t);
            }
        }
        return this.array;
    }

    public T[] removeDuplicatesExactly(int exactNumberOfDuplications){
        this.array = permArray.clone();

        for (T t : permArray){
            if (countDups(t) == exactNumberOfDuplications){
                removeValue(t);
            }
        }
        return this.array;
    }


    public void removeValue(T item) {
        int index = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != item) {
                array[index++] = array[i];
            }
        }
        this.array = Arrays.copyOf(array, index);
    }

    private Integer countDups(T element){
        Integer count = 0;
        for (T t : permArray){
            if (t.equals(element)){
                count++;
            }
        }
        return count;
    }

}
