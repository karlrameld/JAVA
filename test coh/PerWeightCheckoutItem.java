public class PerWeightCheckoutItem extends CheckoutItem {
    private double weight;

    public PerWeightCheckoutItem(int id, double price, double weight) {
        super(id, price);
        this.weight = weight;
    }

    public void addWeight(double d) {
        this.weight += d;
    }

    public double getWeight() {
        return this.weight;
    }

    @Override
    public double calculateNormalPrice() {
        return this.price * this.weight;
    }
}
