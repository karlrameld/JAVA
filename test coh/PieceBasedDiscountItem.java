public class PieceBasedDiscountItem extends DiscountItem {
    private int purchaseAmount;

    public PieceBasedDiscountItem(int id, double discountedPrice, double normalPrice, int purchaseAmount) {
        super(id, discountedPrice, normalPrice);
        this.purchaseAmount = purchaseAmount;
    }

    @Override
    public double getDiscountAmount(Checkout c, double discount) {
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
