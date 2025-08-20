package src.chess.controller;

import java.util.ArrayList;

public class ObserverSubject {

    public ArrayList<Observer> observers;

    public ObserverSubject() {
        observers = new ArrayList<>();
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.react();
        }
    }

}
