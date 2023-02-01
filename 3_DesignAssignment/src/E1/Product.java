package E1;

public class Product {
    private final String name;
    private int Stock;

    public Product(String name, int Stock){
        this.name = name;
        this.Stock = Stock;
    }
    public int getStock () {
        return Stock;
    }
    public void modStock(int num){
        this.Stock += num;
    }
    public String getName () {return this.name;}
}