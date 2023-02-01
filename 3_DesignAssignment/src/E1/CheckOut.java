package E1;

import java.util.HashMap;
public class CheckOut  implements Step {
    static final Step uniqueInstance = new CheckOut();

    public static Step getUniqueInstance(){
        return uniqueInstance;
    }
    public Step next (){
        return Payment.getUniqueInstance();
    }
    @Override
    public HashMap<Product, Integer> op1(Product pr, int i, HashMap<Product, Integer> cart) {
        int quant = cart.get(pr);
        if (pr.getStock() < i - quant) throw new IndexOutOfBoundsException();
        else if (i == 0) cart.remove(pr);
        else {
            cart.replace(pr, i);
            pr.modStock(i - quant);
        }
        return cart;
    }

    public void Info(HashMap<Product, Integer> cartMap) {
        System.out.println("Phase: Checkout -- " + cartMap.size() + " products");
    }
}