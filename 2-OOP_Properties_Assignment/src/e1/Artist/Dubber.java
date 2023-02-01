package e1.Artist;

import e1.Film;

public class Dubber extends Artist {
    public Dubber(String name, String surname, String id, String phonenumber, String nationality, int hours, Film film) {
        super.set(name, surname, id, phonenumber, nationality,hours,film);
        this.setCategory(super.getCategory());
        this.setSalaryPerHour(20);
        this.setJob("Dubber");
    }
    @Override
    public String toString(){
        return getName() + " "+ getSurname()+" "+"("+getJob()+")"+":"+(getSalaryPerHour()*getHours())+" "+"euros";
    }
}
