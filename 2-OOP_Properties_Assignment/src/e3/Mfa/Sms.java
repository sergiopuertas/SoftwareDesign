package e3.Mfa;

import e3.Mfa.MfaStrategy;

import java.util.Random;

public class Sms implements MfaStrategy {
    public Sms(){

    }
    Random r = new Random();

    public String generateCode(){
        return String.format("%06d",r.nextInt(1000000));
    }

}
