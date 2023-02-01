package e1.Artist;

import e1.Film;

import java.util.NoSuchElementException;

public class Actor extends Artist {
    public enum Role{PROTAGONIST,SECONDARY,FIGURANT}
    private Role role;
    public Actor(String name, String surname, String id, String phonenumber, String nationality, int hours,String role,Film film) {
        super.set(name, surname, id, phonenumber, nationality,hours,film);
        this.setCategory(super.getCategory());
        this.setSalaryPerHour(200);
        this.setJob("Actor");
        this.setRole(role);
        this.setBonus(getBonus());
        this.setBonustext("with extra for protagonism");

    }
    @Override
    public int getBonus() {
        if (this.role.equals(Role.PROTAGONIST)){
            return 400*getHours();
        }
        else return 0;
    }
    public void setRole(String role){
        switch (role.toLowerCase()){
            case "protagonist"-> this.role = Role.PROTAGONIST;
            case "secondary"-> this.role = Role.SECONDARY;
            case "figurant"-> this.role = Role.FIGURANT;
            default-> throw new NoSuchElementException("An actor must me either a Protagonist or a Secondary or a Figurant");
        }
    }
    @Override
    public String toString(){
        return getName()+" " + getSurname()+" "+"("+getJob()+" " +getBonusText()+")"+":"+" "+(this.getSalaryPerHour()*this.getHours()+getBonus())+" euro";
    }

}
