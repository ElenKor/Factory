package models.supplier;

import models.storage.Storage;

public abstract class Supplier<T> extends Thread{
    protected models.storage.Storage<T> Storage;
    protected int Delay;

    public Supplier(Storage<T> storage, int delay){
        Storage = storage;
        Delay = delay;
    }

    public void SetDelay(int delay){
        Delay = delay;
    }

    public int GetDelay(){
        return Delay;
    }
    @Override
    public synchronized void run() {
        while (!interrupted()) {
            try {
                Thread.sleep(Delay);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Supply();
        }
    }

    protected abstract void Supply();
}
