public class PerPieceCheckoutItem extends CheckoutItem {
    private int amount;

    public PerPieceCheckoutItem(int id, double price) {
        super(id, price);
        this.amount = 1;
    }

    public void addOne() {
        this.amount++;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public double calculateNormalPrice() {
        return this.price * this.amount;
    }
}
