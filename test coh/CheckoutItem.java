public abstract class CheckoutItem {
    protected final int ID;
    protected double price;
    
    public CheckoutItem(int id, double price){
        this.ID = id;
        this.price = price;
    }

    public int getId() {
        return ID;
    }

    public abstract double calculateNormalPrice();
}
