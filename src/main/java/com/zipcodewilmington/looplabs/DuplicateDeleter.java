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
    private HashMap<T, Integer> thisMap;
    private ArrayList<T> thisList;
    ArrayList<T> dupsToRemove = new ArrayList<>();



    public DuplicateDeleter(T[] intArray) {
        this.array = intArray;
        this.thisList = new ArrayList<>(asList(array));
        this.thisMap = new HashMap<>();
        mapSetUp();
    }

    public T[] removeDuplicates(int maxNumberOfDuplications){
        T[] cloneArray = this.array.clone();

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


    public void mapSetUp(){
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

    public void setUpRemoveList(Integer amount){
        for (Map.Entry<T, Integer> entry : thisMap.entrySet()){
            if (entry.getValue() >= amount){
                this.dupsToRemove.add(entry.getKey());
            }
        }
    }



    public HashMap<T, Integer> getThisMap() {
        return thisMap;
    }

    public ArrayList<T> getThisList() {
        return thisList;
    }

    public ArrayList<T> getDupsToRemove() {
        return dupsToRemove;
    }
}
