import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class CheckoutTester {

    @Test
    public void testToothpaste() {
        Checkout c = new Checkout();
        c.addItem(1);
        c.addItem(1);
        assertEquals(49.90, c.sum());
        c.addItem(1);
        assertEquals(49.90, c.sum());
        c.addItem(1);
        assertEquals(74.85, c.sum());
        c.addItem(1);
        assertEquals(99.80, c.sum());
        c.addItem(1);
        assertEquals(99.80, c.sum());
    }

    @Test
    public void testCheese() {
        Checkout c = new Checkout();
        c.addItem(2, 1);
        assertEquals(59.00, c.sum());
        c.addItem(2, 1.5);
        assertEquals(147.50, c.sum());
    }

    @Test
    public void testBread() {
        Checkout c = new Checkout();
        c.addItem(3);
        c.addItem(3);
        assertEquals(23.90, c.sum());
        c.addItem(3);
        assertEquals(35.85, c.sum());
        c.addItem(3);
        assertEquals(47.80, c.sum());
    }

    @Test
    public void testCoffeeOne() {
        Checkout c = new Checkout();
        c.addItem(4);
        assertEquals(22.49, c.sum());
        c.addItem(4);
        assertEquals(40, c.sum());
        c.addItem(4);
        assertEquals(62.49, c.sum());
        c.addItem(4);
        assertEquals(80, c.sum());
    }

    @Test
    public void testApples() {
        Checkout c = new Checkout();
        c.addItem(5, 4);
        assertEquals(131.80, c.sum());
        c.addItem(5, 1);
        assertEquals(157.59, c.sum());
    }

    @Test
    public void testFlour() {
        Checkout c = new Checkout();
        c.addItem(6);
        c.addItem(6);
        c.addItem(6);
        assertEquals(35.85, c.sum());
        c.addItem(6);
        assertEquals(47.80, c.sum());
    }

    @Test
    public void testGroundBeef() {
        Checkout c = new Checkout();
        c.addItem(7, 2.5);
        assertEquals(232.50, c.sum());
        c.addItem(7, 1);
        assertEquals(325.50, c.sum());
    }

    @Test
    public void testMilk() {
        Checkout c = new Checkout();
        c.addItem(8);
        c.addItem(8);
        assertEquals(18.64, c.sum());
        c.addItem(8);
        c.addItem(8);
        assertEquals(37.28, c.sum());
        c.addItem(8);
        c.addItem(8);
        assertEquals(55.92, c.sum());
    }

    @Test
    public void testOneOfEach() {
        Checkout c = new Checkout();
        c.addItem(1);
        c.addItem(2, 1);
        c.addItem(3);
        c.addItem(4);
        assertEquals(118.39, c.sum());
        c.addItem(5, 1);
        assertEquals(150.69, c.sum());
        c.addItem(6);
        assertEquals(156.84, c.sum());
        c.addItem(7, 1);
        assertEquals(240.29, c.sum());
        c.addItem(8);
        assertEquals(249.61, c.sum());
    }

    @Test
    public void testEachDiscount() {
        Checkout c = new Checkout();
        c.addItem(1);
        c.addItem(1);
        c.addItem(4);
        assertEquals(72.39, c.sum());
        c.addItem(5, 3);
        assertEquals(160.93, c.sum());
        c.addItem(1);
        assertEquals(160.93, c.sum());
        c.addItem(4);
        assertEquals(169.93, c.sum());
    }
}
