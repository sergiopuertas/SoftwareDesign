package e1.Artist;


import e1.Film;

public class Stunt extends Artist {
    public Stunt(String name, String surname, String id, String phonenumber, String nationality, int hours, Boolean danger, Film film) {
        super.set(name, surname, id, phonenumber, nationality,hours,film);
        this.setCategory(super.getCategory());
        this.setSalaryPerHour(40);
        this.setDanger(danger);
        this.setBonus(getBonus());
        this.setJob("Stunt");
        this.setBonustext("with extra for danger");
    }
    private boolean danger;
    public boolean getDanger() {
        return danger;
    }
    public void setDanger(boolean danger) {
        this.danger = danger;
    }

    @Override
    public int getBonus() {
        if (this.getDanger()) {
            return 1000;
        }
        else return 0;
    }
    @Override
    public String toString(){
        return getName()+" " + getSurname()+" "+"("+getJob()+" " +getBonusText()+")"+":"+" "+(this.getSalaryPerHour()*this.getHours()+getBonus())+" euro";
    }
}
