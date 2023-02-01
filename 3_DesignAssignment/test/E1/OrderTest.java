package E1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(OrderAnnotation.class)


public class OrderTest {
    private static final Product p1 = new Product("Shephone 14", 20);
    private static final Product p2 = new Product("Xiaotu 12", 100);
    private static final Product p3 = new Product("Ramenmon Rosa", 20000);
    private static final Product p4 = new Product("Suspenso en DS", 0);

    private static final Ord o1 = new Ord(111);
    private static final Ord o2 = new Ord(111);


    @BeforeAll
    static void InsertProducts(){
       o1.newProduct(p1);
       o1.newProduct(p2);
       o1.newProduct(p3);
        o2.newProduct(p1);
        o2.newProduct(p2);
        o2.newProduct(p3);
    }
    @Test
    @Order(1)
    void addItems (){
        o1.ScreenInfo();
        o1.addItems(p1,3);
        o1.addItems(p2,4);
        o1.addItems(p3,85);
        o1.addItems(p3,2);
        o2.addItems(p1,3);
        o2.addItems(p2,4);
        o2.addItems(p3,87);
        assertEquals(3,o1.getCartMap().get(p1));
        assertEquals(87,o1.getCartMap().get(p3));
        assertEquals(4,o1.getCartMap().get(p2));
        o1.Log();
        o1.ScreenInfo();


    }
    @Test
    @Order(2)
    void removeItems (){
        o1.removeItems(p1,2);
        o1.removeItems(p2,2);
        o1.removeItems(p3,2);
        o2.removeItems(p1,3);
        o2.removeItems(p2,2);
        o2.removeItems(p3,2);
        assertEquals(1,o1.getCartMap().get(p1));
        assertEquals(2,o1.getCartMap().get(p2));
        assertEquals(85,o1.getCartMap().get(p3));
        assertThrows(NoSuchElementException.class,()->o2.removeItems(p4,2));
        o1.Log();
        o1.ScreenInfo();


    }
    @Test
    @Order(3)
    void goCheckout () {
        assertThrows(IllegalStateException.class,()-> o1.modifyOrder(p1,1));
        o1.goCheckout();
        o2.goCheckout();
        assertDoesNotThrow(()->o1.modifyOrder(p1,1));
        o1.Log();
        o1.ScreenInfo();

    }
    @Test
    @Order(4)
    void modifyOrder (){
        o1.modifyOrder(p1,2);
        o1.modifyOrder(p2,4);
        o1.modifyOrder(p3,34);
        o2.modifyOrder(p2,4);
        o2.modifyOrder(p3,34);
        assertEquals(2,o1.getCartMap().get(p1));
        assertEquals(4,o1.getCartMap().get(p2));
        assertEquals(34,o1.getCartMap().get(p3));
        assertThrows(IndexOutOfBoundsException.class,()-> o1.modifyOrder(p1,200));
        o1.modifyOrder(p1,0);

        o1.Log();
        o1.ScreenInfo();


        assertThrows(NoSuchElementException.class,()-> o1.modifyOrder(p1,200) );
    }
    @Test
    @Order(5)
    void returnToCart () {
        assertThrows(IllegalStateException.class,()-> o1.removeItems(p1,1));
        o1.returnToCart();
        o2.returnToCart();

        assertDoesNotThrow(()-> o1.removeItems(p2,1));
        o1.ScreenInfo();

    }
    @Test
    @Order(6)
    void Pay () {
        assertThrows(IllegalStateException.class, o1::Pay);
        assertThrows(IllegalStateException.class, o1::Cancel);
        o1.goCheckout();
        o1.Pay();
        o2.goCheckout();
        o2.Pay();
        assertThrows(IllegalStateException.class, o1::goCheckout);
        o1.ScreenInfo();
    }
    @Test
    @Order(7)
    void Cancel (){
        assertThrows(IllegalStateException.class, o1::Pay);
        assertThrows(IllegalStateException.class, o1::returnToCart);
        o1.setTimeCheck(true);
        o1.Cancel();
        o2.Cancel();
        assertThrows(IllegalStateException.class, o1::goCheckout);
        o1.ScreenInfo();
        o2.ScreenInfo();
    }
}