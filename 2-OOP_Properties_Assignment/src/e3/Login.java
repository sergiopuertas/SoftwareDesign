package e3;
import e3.LS.Dni;
import e3.LS.Email;
import e3.LS.LoginStrategy;
import e3.LS.Phone;
import e3.Mfa.MfaStrategy;

import java.util.NoSuchElementException;
import java.util.HashMap;
public class Login {


    LoginStrategy def;
    HashMap<String, User> users;

    public LoginStrategy getLogin(String s){
        LoginStrategy LS;
        switch (s.toLowerCase()){
            case "dni"-> LS = new Dni();
            case "phone"-> LS = new Phone();
            default -> LS = new Email();
        }
        return LS;
    }
   
    public Login(String ls) {
        this.def=getLogin(ls);
        users= new HashMap<>();
    }
    public void setLoginStrategy (String s ) {
        this.def = getLogin(s) ;}

    public void newUser (User usr){
        this.users.put(usr.getId(),usr);
    }
    public void setMfaStrategy(User user, MfaStrategy mf){
        if (inList(user.getId())){
            user.setMfaUser(mf);
        }
        else throw new NoSuchElementException();
    }
    public boolean inList(String s) {
        return users.containsKey(s);
    }

    public String mfaCode(String id, String password) {
        LoginStrategy LS= this.def;
        if (!LS.validateId(id)){
          throw new IllegalArgumentException();
        }
        if (!inList(id)) {
            throw new NoSuchElementException();
        }
        if (def.authenticatePassword(users.get(id),password)){
            return users.get(id).getMfaUser().generateCode();
        }
        throw new IllegalArgumentException();
    }
}
