package e1.Technician;


import e1.Film;

public class Screenwriter extends Technician {
    public Screenwriter(String name, String surname, String id, String phonenumber, String nationality, int hours, Boolean original, Film film) {
        super.set(name, surname, id, phonenumber, nationality,hours, film);
        this.setCategory(super.getCategory());
        this.setSalaryPerHour(70);
        this.original=original;
        this.setBonus(getBonus());
        this.setRoyalties(0.05);
        this.setJob("Screenwriter");
        this.setBonustext("with extra for originality");
    }
    private final boolean original;
    public boolean isOriginal() {
        return original;
    }
    @Override
    public int getBonus() {
        if(isOriginal()){
            return 4000;
        }
        else return 0;
    }
    @Override
    public String toString(){
        return getName()+" " + getSurname()+" "+"("+getJob()+" " +getBonusText()+")"+":"+" "+(this.getSalaryPerHour()*this.getHours()+getBonus())+" euro";
    }
}
