package com.zipcodewilmington.looplabs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static java.util.Arrays.asList;

/**
 * Created by leon on 1/25/18.
 */
public abstract class DuplicateDeleter<T> implements DuplicateDeleterInterface<T> {
    protected T[] array;
    private T[] permArray;
    private HashMap<T, Integer> thisMap;
    private ArrayList<T> thisList;
    private ArrayList<T> dupsToRemove;


    public DuplicateDeleter(T[] intArray) {
        this.array = intArray;
        this.thisList = new ArrayList<>(asList(array));
        this.thisMap = new HashMap<>();
        this.permArray = intArray;
    }

    public T[] removeDuplicates(int maxNumberOfDuplications){
        setUp();

        for (Map.Entry<T, Integer> entry : thisMap.entrySet()){
            if (entry.getValue() >= maxNumberOfDuplications){
                this.dupsToRemove.add(entry.getKey());
            }
        }
        for (T element : dupsToRemove){
            removeValue(element);
        }
        return this.array;
    }

    public T[] removeDuplicatesExactly(int exactNumberOfDuplications){
        setUp();

        for (Map.Entry<T, Integer> entry : thisMap.entrySet()){
            if (entry.getValue() == exactNumberOfDuplications){
                this.dupsToRemove.add(entry.getKey());
            }
        }
        for (T element : dupsToRemove){
            removeValue(element);
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
        dupsToRemove = new ArrayList<>();
        this.thisMap = new HashMap<>();
        Integer count;
        for (T element : this.thisList){
            if (thisMap.get(element) == null){
                thisMap.put(element, 1);
            } else {
                count = thisMap.get(element);
                thisMap.put(element, count + 1);
            }
        }
    }
}
