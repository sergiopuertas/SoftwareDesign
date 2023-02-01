package e3.LS;


import e3.User;

public interface LoginStrategy{
    abstract boolean validateId (String Id);
    abstract boolean authenticatePassword (User user, String password);
}