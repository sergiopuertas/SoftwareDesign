package e1.Technician;

import e1.Film;
import e1.Worker;

public abstract class Technician extends Worker {
    public void set (String name, String surname, String id, String phonenumber, String nationality, int hours, Film film){
        super.set(name, surname, id, phonenumber, nationality,hours, film);
        this.setCategory("Technician");
        this.setRoyalties(getRoyalties()* film.getInbox());
    }
}
