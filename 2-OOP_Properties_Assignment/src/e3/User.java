package e3;

import e3.Mfa.MfaStrategy;

public class User {
    private final String id;
    private final String password;
    private MfaStrategy mfa;
    public User(String id, String password, MfaStrategy mfa){
        this.id=id;
        this.password=password;
        this.mfa=mfa;
    }
    public MfaStrategy getMfaUser (){
        return this.mfa;
    }
    public void setMfaUser (MfaStrategy mfa){
        this.mfa=mfa;
    }
    public String getPassword (){
        return this.password;
    }
    public String getId (){return this.id;}
}
