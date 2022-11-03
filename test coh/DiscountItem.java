public abstract class DiscountItem {
    protected final int ID;

    public DiscountItem(int id){
        this.ID = id;
    }

    public int getId() {
        return ID;
    }
    public abstract double getDiscountAmount(Checkout c);
}