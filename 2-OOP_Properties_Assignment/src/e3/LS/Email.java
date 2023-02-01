package e3.LS;

import e3.User;

public class Email implements LoginStrategy {
    public Email(){

    }
    public boolean validateId (String Id){
        return (Id.contains("@"));
    }
    public boolean authenticatePassword (User user, String password){
        return password.equals(user.getPassword());
    }
}
