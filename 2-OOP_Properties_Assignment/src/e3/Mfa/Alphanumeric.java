package e3.Mfa;

import java.util.Random;

public class Alphanumeric implements MfaStrategy {
    public Alphanumeric(){
    }
    Random r = new Random();
    StringBuilder code=new StringBuilder ();
    public String generateCode(){
        while (code.length()<9) {
            int i = r.nextInt(2);
            if (i == 0) {
                char c = (char) (r.nextInt(26) + 'a');
                code.append(c);
            }
            else {
                int n= r.nextInt(10);
                code.append(n);
            }
        }
        return code.toString();
    }

}
