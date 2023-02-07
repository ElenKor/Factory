package models.storage;

import java.util.LinkedList;
import java.util.List;

public class Storage<T> {
    private int size;
    private List<T> itemsList;
    private int itemsPut;
    private int itemsTaken;
    public Storage(int size){
        this.itemsList = new LinkedList<T>();
        this.size = size;
        this.itemsTaken = 0;
    }
    public synchronized boolean Add(T item) throws InterruptedException {
        while (itemsList.size() == size){
            wait();
        }

        if (itemsList.size() != size){
            itemsList.add(item);
            itemsPut++;
            notifyAll();
            return true;
        }

        return false;
    }

    public synchronized T Get() throws InterruptedException {
        while (itemsList.size() < 0){
            wait();
        }
        notifyAll();
        if (itemsList.size() > 0){
            T item = itemsList.get(itemsList.size() - 1);
            itemsList.remove(itemsList.size() - 1);
            itemsTaken++;
            return item;
        }
        return null;
    }
    public boolean isFull(){
        return itemsList.size() == size;
    }
    public int getItemsTaken() {
        return itemsTaken;
    }

    public void setItemsTaken(int itemsTaken) {
        this.itemsTaken = itemsTaken;
    }

    public int getItemsPut() {
        return itemsPut;
    }

    public void setItemsPut(int itemsPut) {
        this.itemsPut = itemsPut;
    }
    public List<T> getItemsList() {
        return itemsList;
    }

    public void setItemsList(List<T> itemsList) {
        this.itemsList = itemsList;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
