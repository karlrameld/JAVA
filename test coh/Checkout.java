import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

class Checkout {
    private HashMap<Integer, CheckoutItem> cart;
    /*
     * This should be stored in a holder class, but because of the need to apply
     * discounts in different stages
     * all discounts can be tiered and they'll be applied according to the key in the hashmap, lower key means earlier application.
     * TIER_DISCOUNTS_LIST
     */
    private static final ArrayList<DiscountItem> DISCOUNTS_LIST_FIRST = new ArrayList<>();
    private static final ArrayList<DiscountItem> DISCOUNTS_LIST_SECOND = new ArrayList<>();
    private static final HashMap<Integer, ArrayList<DiscountItem>> TIER_DISCOUNTS_MAP = new HashMap<>();
    private static final HashSet<Integer> ITEM_WITH_WEIGHT_BASED_PRICE = new HashSet<>();
    private static final HashMap<Integer, Double> NORMAL_PRICE_MAP = new HashMap<>();
    static {
        NORMAL_PRICE_MAP.put(1, 24.95);
        DISCOUNTS_LIST_FIRST.add(
                new PieceBasedDiscountItem(1, (NORMAL_PRICE_MAP.get(1) / 3) * 2, NORMAL_PRICE_MAP.get(1), 3));
        NORMAL_PRICE_MAP.put(2, 59.00);
        ITEM_WITH_WEIGHT_BASED_PRICE.add(2);
        NORMAL_PRICE_MAP.put(3, 11.95);
        NORMAL_PRICE_MAP.put(4, 22.49);
        DISCOUNTS_LIST_FIRST.add(new PieceBasedDiscountItem(4, 20, NORMAL_PRICE_MAP.get(4), 2));
        NORMAL_PRICE_MAP.put(5, 32.95);
        ITEM_WITH_WEIGHT_BASED_PRICE.add(5);
        DISCOUNTS_LIST_SECOND.add(new WeightBasedDiscountItem(5, 16.95, NORMAL_PRICE_MAP.get(5), 150));
        NORMAL_PRICE_MAP.put(6, 11.95);
        NORMAL_PRICE_MAP.put(7, 93.00);
        ITEM_WITH_WEIGHT_BASED_PRICE.add(7);
        NORMAL_PRICE_MAP.put(8, 9.32);
        TIER_DISCOUNTS_MAP.put(1, DISCOUNTS_LIST_FIRST);
        TIER_DISCOUNTS_MAP.put(2, DISCOUNTS_LIST_SECOND);
    }

    public Checkout() {
        cart = new HashMap<>();
    }

    public HashMap<Integer, CheckoutItem> getCart() {
        return cart;
    }

    public void addItem(int id) {
        try {
            if (ITEM_WITH_WEIGHT_BASED_PRICE.contains(id)) {
                throw new IllegalArgumentException("ID " + id + " isn't a piece priced item!");
            }
            PerPieceCheckoutItem item;
            if (cart.containsKey(id)) {
                item = (PerPieceCheckoutItem) cart.get(id);
                item.addOne();
            } else {
                item = new PerPieceCheckoutItem(id, NORMAL_PRICE_MAP.get(id));
                cart.put(id, item);
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    public void addItem(int id, double weight) {
        try {
            if (!(ITEM_WITH_WEIGHT_BASED_PRICE.contains(id))) {
                throw new IllegalArgumentException("ID " + id + " isn't a weight priced item!");
            }
            PerWeightCheckoutItem item;
            if (cart.containsKey(id)) {
                item = (PerWeightCheckoutItem) cart.get(id);
                item.addWeight(weight);
            } else {
                item = new PerWeightCheckoutItem(id, NORMAL_PRICE_MAP.get(id), weight);
                cart.put(id, item);
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    public double calculateBaseCartPrice() {
        double total = 0;
        for (Map.Entry<Integer, CheckoutItem> item : cart.entrySet()) {
            total += item.getValue().calculateNormalPrice();
        }
        return total;
    }

    private double calculateCartDiscount() {
        double discount = 0; // The hashmap is orderd, so lower keys will be iterated over first. 
        for (Map.Entry<Integer, ArrayList<DiscountItem>> tier : TIER_DISCOUNTS_MAP.entrySet()) {
            for (DiscountItem item : tier.getValue()) {
                discount += item.getDiscountAmount(this, discount);
            }
        }
        return discount;
    }

    public double sum() {
        double total = calculateBaseCartPrice() - calculateCartDiscount();
        //System.out.println(String.format("%.2f", total) + ":-");
        return Math.round(total * 100.0) / 100.0;
    }
}