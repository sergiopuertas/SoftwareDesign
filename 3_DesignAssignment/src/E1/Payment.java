package E1;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class Payment implements Step {
    static final Step uniqueInstance = new Payment();

    public static Step getUniqueInstance(){
        return uniqueInstance;
    }
    public Step next(){
        return Completed.getUniqueInstance() ;
    }
    LocalDateTime time;

    @Override
    public void setTime() {
        this.time = LocalDateTime.now();
    }
    @Override
    public LocalDateTime getTime() {
        return this.time;
    }
    public void Info(HashMap<Product,Integer> cartMap){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("Phase: Paid Order " + cartMap.size() + " products -- date " + dtf.format(this.time));    }
}