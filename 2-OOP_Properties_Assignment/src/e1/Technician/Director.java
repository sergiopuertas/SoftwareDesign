package e1.Technician;

import e1.Film;

public class Director extends Technician {
    private int seniority;
    public Director(String name, String surname, String id, String phonenumber, String nationality , int hours, int seniority, Film film) {
        super.set(name, surname, id, phonenumber, nationality,hours,film);
        this.setCategory(super.getCategory());
        this.setSeniority(seniority);
        this.setSalaryPerHour(100);
        this.setBonus(getSeniority() * 1000);
        this.setRoyalties(0.05);
        this.setJob("Director");
        this.setBonustext("with extra for "+ seniority +" years of seniority");
    }

    public int getSeniority() {
        return seniority;
    }
    public void setSeniority(int seniority){
        this.seniority = seniority;
    }
    @Override
    public int getBonus() {
        return getSeniority() * 1000;
    }
    @Override
    public String toString(){
        return getName()+" " + getSurname()+" "+"("+getJob()+" " +getBonusText()+")"+":"+" "+(this.getSalaryPerHour()*this.getHours()+getBonus())+" euro";
    }
}
