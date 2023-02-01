package e1.Technician;


import e1.Film;

public class Musician extends Technician {
    public Musician(String name, String surname, String id, String phonenumber, String nationality, int hours, Film film) {
        super.set(name, surname, id, phonenumber, nationality,hours,film);
        this.setCategory(super.getCategory());
        this.setSalaryPerHour(60);
        this.setRoyalties(0.04);
        this.setJob("Musician");
    }
    @Override
    public String toString(){
        return getName() + " "+ getSurname()+" "+"("+getJob()+")"+":"+(getSalaryPerHour()*getHours())+" "+"euros";
    }
}
