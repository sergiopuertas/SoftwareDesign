package E2;

import java.util.*;
public abstract class Aquarium {
    static Alert.AlertComparator comp = new Alert.AlertComparator();
    static PriorityQueue<Alert> report = new PriorityQueue<>(comp);
    List<Observers> observers = new ArrayList<>();
    public void attach(Observers obs) { observers.add(obs);}
    public void detach(Observers obs) { observers.remove(obs);}

    public void notifyObservers(Alert alert){
        for (Observers obs : observers) {
            obs.update(alert);
        }
    }
}
