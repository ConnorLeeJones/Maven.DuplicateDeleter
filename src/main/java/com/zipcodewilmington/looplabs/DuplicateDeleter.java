package com.zipcodewilmington.looplabs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by leon on 1/25/18.
 */
public abstract class DuplicateDeleter<T> implements DuplicateDeleterInterface<T> {
    protected T[] array;
    private T[] permArray;
    private HashMap<T, Integer> thisMap;


    public DuplicateDeleter(T[] intArray) {
        this.array = intArray;
        this.thisMap = new HashMap<>();
        this.permArray = intArray;
    }

    public T[] removeDuplicates(int maxNumberOfDuplications){
        setUp();

        for (Map.Entry<T, Integer> entry : thisMap.entrySet()){
            if (entry.getValue() >= maxNumberOfDuplications){
                removeValue(entry.getKey());
            }
        }
        return this.array;
    }

    public T[] removeDuplicatesExactly(int exactNumberOfDuplications){
        setUp();

        for (Map.Entry<T, Integer> entry : thisMap.entrySet()) {
            if (entry.getValue() == exactNumberOfDuplications) {
                removeValue(entry.getKey());
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


    public void setUp(){
        this.array = permArray.clone();
        this.thisMap = new HashMap<>();
        Integer count;
        for (T t : array) {
            if (thisMap.get(t) == null) {
                thisMap.put(t, 1);
            } else {
                count = thisMap.get(t);
                thisMap.put(t, count + 1);
            }
        }
    }
}
