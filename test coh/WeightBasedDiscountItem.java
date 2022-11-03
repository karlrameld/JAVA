public class WeightBasedDiscountItem extends DiscountItem {
    private double minimumCartAmount;

    public WeightBasedDiscountItem(int id, double discountedPrice, double normalPrice, double minimumCartAmount) {
        super(id, discountedPrice, normalPrice);
        this.minimumCartAmount = minimumCartAmount;
    }

    @Override
    public double getDiscountAmount(Checkout c) {
        if (c.getCart().containsKey(this.ID) && c.calculateBaseCartPrice() >= minimumCartAmount) {
            PerWeightCheckoutItem item = (PerWeightCheckoutItem) c.getCart().get(this.ID);
            return item.getWeight() * normalPrice - item.getWeight() * discountedPrice;
        }
        return 0;
    }
}