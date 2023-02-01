package e3.LS;


import e3.User;

public class Dni implements LoginStrategy {
    public Dni(){

    }
    public boolean validateId (String Id){
        if (Id.length()!=9){return false;}
        String num = Id.substring(0,7);
        try {Double.parseDouble(num);}
        catch (NumberFormatException nfe) {return false;}
        return (Character.isAlphabetic(Id.charAt(8)));
    }

    public boolean authenticatePassword (User user, String password){
        return password.equals(user.getPassword());
    }
}
