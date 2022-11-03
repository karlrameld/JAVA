public class PieceBasedDiscountItem extends DiscountItem {
    private int purchaseAmount;
    private double discountedPrice;
    private double normalPrice;

    public PieceBasedDiscountItem(int id, int purchaseAmount, double discountedPrice, double normalPrice) {
        super(id);
        this.purchaseAmount = purchaseAmount;
        this.discountedPrice = discountedPrice;
        this.normalPrice = normalPrice;
    }

    @Override
    public double getDiscountAmount(Checkout c) {
        if (c.getCart().containsKey(this.ID)) {
            PerPieceCheckoutItem item = (PerPieceCheckoutItem) c.getCart().get(this.ID);
            return calculatePriceDifference(item.getAmount());
        }
        return 0;
    }
    
    /*
     * IF we buy more than minimum to get discount (totalPurcasedAmount >= purchaseAmount)
     * then we can can apply the discounted price (discountedPrice)
     * to the amount of times we buy the required products (Math.floor(totalPurcasedAmount / purchaseAmount) * purchaseAmount)
     * and finally we add the normal price (normalPrice)
     * for the rest of the previos division (totalPurcasedAmount % purchaseAmount)
     * we then subtract this number from the normal total to get the total discount for the given amount of items. 
     */
    private double calculatePriceDifference(int totalPurcasedAmount) {
        if (totalPurcasedAmount >= purchaseAmount) {
            double d = Math.floor(totalPurcasedAmount / purchaseAmount) * purchaseAmount * discountedPrice
                    + totalPurcasedAmount % purchaseAmount * normalPrice;
            return totalPurcasedAmount * normalPrice - d;
        }
        return 0;
    }
}
