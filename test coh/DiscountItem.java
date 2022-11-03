public abstract class DiscountItem {
    protected final int ID;
    protected double discountedPrice;
    protected double normalPrice;


    public DiscountItem(int id, double discountedPrice, double normalPrice){
        this.ID = id;
        this.discountedPrice = discountedPrice;
        this.normalPrice = normalPrice;
    }

    public int getId() {
        return ID;
    }
    public abstract double getDiscountAmount(Checkout c);
}