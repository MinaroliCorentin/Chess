package src.chess.controller;

import java.util.ArrayList;

public class ObserverSubject {

    public ArrayList<Observer> observers;

    public ObserverSubject() {
        observers = new ArrayList<>();
    }

    /**
     * Add a Observer
     * @param observer Any class that implements Observer
     */
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    /**
     * Call all the observed
     */
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.react();
        }
    }

}
