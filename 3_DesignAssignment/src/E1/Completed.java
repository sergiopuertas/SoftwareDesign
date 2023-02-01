package E1;

import java.util.HashMap;
public class Completed implements Step{
    static final Step uniqueInstance = new Completed();
    public static Step getUniqueInstance(){
        return uniqueInstance;
    }
    public void Info( HashMap<Product,Integer> cartMap){
        System.out.println("Phase: Completed Order " + cartMap.size() + " products");
    }
}