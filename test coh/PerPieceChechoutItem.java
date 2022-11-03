public class PerPieceChechoutItem extends CheckoutItem {
    private int amount;

    public PerPieceChechoutItem(int id, double price) {
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
    protected double calculateNormalPrice() {
        return this.price * this.amount;
    }
}
