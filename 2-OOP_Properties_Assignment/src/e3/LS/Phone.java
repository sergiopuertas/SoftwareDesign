package e3.LS;


import e3.User;

public class Phone implements LoginStrategy {
    public Phone(){

    }
    public boolean validateId (String Id){
        try {Double.parseDouble(Id);}
        catch (IllegalArgumentException iae){
            return false;
        }
        return true;
    }
    public boolean authenticatePassword (User user, String password){
        return password.equals(user.getPassword());
    }

}
