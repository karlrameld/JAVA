public class WeightBasedDiscountItem extends DiscountItem {
    private double minimumCartAmount;

    public WeightBasedDiscountItem(int id, double discountedPrice, double normalPrice, double minimumCartAmount) {
        super(id, discountedPrice, normalPrice);
        this.minimumCartAmount = minimumCartAmount;
    }

    @Override
    public double getDiscountAmount(Checkout c, double discount) {
        if (c.getCart().containsKey(this.ID) && c.calculateBaseCartPrice() - discount > minimumCartAmount) {
            PerWeightCheckoutItem item = (PerWeightCheckoutItem) c.getCart().get(this.ID);
            double baseCartPrice = c.calculateBaseCartPrice() - discount - item.calculateNormalPrice();
            // All of product should be discounted
            if(baseCartPrice >= minimumCartAmount) 
                return item.getWeight() * normalPrice - item.getWeight() * discountedPrice;
            // A percent of product should be discounted
            double normalPriceWeight = (minimumCartAmount - baseCartPrice) / normalPrice; 
            double discountedPriceWeight = item.getWeight() - normalPriceWeight;
            return item.getWeight() * normalPrice - (normalPriceWeight * normalPrice + discountedPriceWeight * discountedPrice);
        }
        return 0;
    }
}