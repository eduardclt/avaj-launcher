package simulator;

import simulator.Vehicles.Flyable;

import java.util.ArrayList;

public class Tower{

    private ArrayList<Flyable> observers = new ArrayList<Flyable>();

    public void register(Flyable flyable){
        if (observers.contains(flyable))
            return;
        observers.add(flyable);
    }

    public void unregister(Flyable flyable){
        this.observers.remove(flyable);

    }

    protected void conditionsChanged(){

        for (int i = 0; i < observers.size(); i++) {
            observers.get(i).updateConditions();
        }

    }
}
