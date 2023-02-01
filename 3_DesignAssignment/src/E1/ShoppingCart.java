package E1;

import java.util.*;

public class ShoppingCart  implements Step{
    static final Step uniqueInstance = new ShoppingCart();


    public static Step getUniqueInstance(){
        return uniqueInstance;
    }
public Step next(){
        return CheckOut.getUniqueInstance();
}
    @Override
    public HashMap<Product, Integer> op1(Product pr, int i, HashMap<Product,Integer> cart) {
        if (pr.getStock()<i) throw new IndexOutOfBoundsException();
        if(cart.containsKey(pr)){
            int quant = cart.get(pr);
            cart.replace(pr,quant+i);
        }
        else cart.put(pr,i);
        pr.modStock(-i);
        return cart;
    }
    @Override
    public HashMap<Product, Integer> op2(Product pr, int i, HashMap<Product,Integer> cart)  {

        if(!cart.containsKey(pr))
            throw new NoSuchElementException();

        else {
            int quant = cart.get(pr);
            if (quant>i) {
                cart.replace(pr, quant - i);
                pr.modStock(i);
            }
            else {
                pr.modStock(quant);
                cart.remove(pr);
            }
            return cart;
        }
    }
    public void Info(HashMap<Product,Integer> cartMap){
        if (!cartMap.isEmpty())
            System.out.println("Phase: Shopping -- "+ cartMap.size()+" products");
        else
            System.out.println("Phase: Shopping -- Welcome to online shop");
    }


}