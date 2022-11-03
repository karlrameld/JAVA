public class WeightBasedDiscountItem extends DiscountItem {
    private double discountedPrice;
    private double normalPrice;
    private double minimumCartAmount;

    public WeightBasedDiscountItem(int id,double minimumCartAmount, double discountedPrice, double normalPrice) {
        super(id);
        this.minimumCartAmount = minimumCartAmount;
        this.discountedPrice = discountedPrice;
        this.normalPrice = normalPrice;
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