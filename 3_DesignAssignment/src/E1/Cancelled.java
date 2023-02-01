package E1;

import java.util.HashMap;
public class Cancelled implements Step{
    static final Step uniqueInstance = new Cancelled();
    public static Step getUniqueInstance(){
        return uniqueInstance;
    }
    public void Info( HashMap<Product,Integer> cartMap){
        System.out.println("Phase: Cancelled order");    }
}