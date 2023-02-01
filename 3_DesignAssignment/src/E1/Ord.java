package E1;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

public class Ord {
    List<String> Log = new ArrayList<>();
    HashMap<Product, Integer> cartMap= new HashMap<>();
    List <Product> stock = new ArrayList<>();
    Step step;

    boolean timecheck = false;
    private final int Ordernumber;

    public Ord(int num){
        this.Ordernumber = num;
        this.setCartMap(getCartMap());
        this.step=ShoppingCart.getUniqueInstance();
    }
    public HashMap<Product, Integer> getCartMap() {
        return this.cartMap;
    }
    public void setTimeCheck (boolean time){
        this.timecheck=time;
    }

    public void setCartMap(HashMap<Product, Integer> cartMap) {
        this.cartMap = cartMap;
    }
    public void newProduct (Product pr){
        this.stock.add(pr);
    }

    public void addItems(Product pr, int i){
        if (this.step != ShoppingCart.getUniqueInstance()) throw new IllegalStateException();
        this.cartMap=this.step.op1(pr, i, this.cartMap);
        Log.add("- Add: Item: "+pr.getName()+" - Quantity: "+i+" -> Shopping cart -- Products: "+this.cartMap.size() );
    }
    public void removeItems(Product pr, int i){
        if (this.step != ShoppingCart.getUniqueInstance()) throw new IllegalStateException();
        this.cartMap=this.step.op2(pr, i, this.cartMap);
        Log.add("- Remove: Item: "+pr.getName()+" - Quantity: "+i+" -> Shopping cart -- Products: "+this.cartMap.size() );

    }
    public void goCheckout(){
        if(this.step != ShoppingCart.getUniqueInstance()){
            throw new IllegalStateException();
        }
        this.step =this.step.next();
        Log.add("Order " + Ordernumber + ": Check out phase");
    }
    public void modifyOrder(Product pr, int newQuant){
        if(this.step != CheckOut.getUniqueInstance()) throw new IllegalStateException();
        if (!this.getCartMap().containsKey(pr)) throw new NoSuchElementException();
        this.cartMap=this.step.op1(pr, newQuant, this.cartMap);
        Log.add("- Modify: Item: "+pr.getName()+" - Quantity: "+newQuant+" -> Checkout Order -- Products: "+this.cartMap.size() );
    }
    public void returnToCart(){
        if(this.step != CheckOut.getUniqueInstance()){
            throw new IllegalStateException();
        }
        this.step=ShoppingCart.getUniqueInstance();
        Log.add("Order " + Ordernumber + ": Shopping phase");
    }
    public void Pay(){
        if(CheckOut.getUniqueInstance() != this.step){
            throw new IllegalStateException();

        }
        Log.add("Order " + Ordernumber + ": Payment phase");
        this.step=this.step.next();
        this.step.setTime();
        System.out.println("You have now 24 hours to cancel your purchase\n");
    }

    public void Cancel(){
        LocalDateTime timereq = LocalDateTime.now();
        if(step != Payment.getUniqueInstance()){
            throw new IllegalStateException();
        }
        if (this.timecheck){timereq=ChronoUnit.HOURS.addTo(timereq, 24);}

        if(ChronoUnit.HOURS.between(this.step.getTime(),timereq)<24) {
            this.step=Cancelled.getUniqueInstance();
        }
        else {
            System.out.println("Cancellation time expired\n");
            this.step= this.step.next();
        }
    }
    public void ScreenInfo(){
        System.out.println("Actual state:\nOrder number: "+Ordernumber);
        step.Info(cartMap);
        System.out.println();

    }
    public void Log (){
        for (String string: this.Log){
            System.out.println(string);
        }
        System.out.println();
    }
}