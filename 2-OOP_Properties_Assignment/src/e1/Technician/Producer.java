package e1.Technician;


import e1.Film;

public class Producer extends Technician {
    public Producer(String name, String surname, String id, String phonenumber, String nationality, int hours, Film film) {
        super.set(name, surname, id, phonenumber, nationality, hours,film);
        this.setCategory(super.getCategory());
        this.setSalaryPerHour(90);
        this.setRoyalties(0.02);
        this.setJob("Producer");
    }
    @Override
    public String toString(){
        return getName() + " "+ getSurname()+" "+"("+getJob()+")"+":"+(getSalaryPerHour()*getHours())+" "+"euros";
    }

}
