package E1;

import java.time.LocalDateTime;
import java.util.HashMap;

public interface Step {
    static Step getUniqueInstance() {
        return null;
    }
    default Step next (){
        return null;
    }

    default void setTime(){throw new IllegalStateException();}
    default LocalDateTime getTime (){throw new IllegalStateException();}
    default HashMap<Product, Integer> op1(Product pr, int i, HashMap<Product,Integer> cartMap){
        throw new IllegalStateException();
    }

    default HashMap<Product, Integer> op2(Product pr, int i, HashMap<Product,Integer> cartMap){
        throw new IllegalStateException();
    }
    void Info ( HashMap<Product,Integer> cartMap);

}