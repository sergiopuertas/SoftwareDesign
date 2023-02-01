package e3.Mfa;

import java.util.Random;

public class Authenticator implements MfaStrategy {
    public Authenticator(){
    }
    Random r = new Random();
    public String generateCode(){
        return String.format(Integer.toString(r.nextInt(1000000,100000000)));
    }
}
