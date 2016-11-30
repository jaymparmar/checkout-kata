package itv.supermarket.checkout;

public final class MultiItemPricingRule {

    private Item item;
    private long price;
    private int qty;

    public MultiItemPricingRule(Item item,long price ,int qty) {
        this.item = item;
        this.price = price;
        this.qty = qty;
    }

    public Item getItem() {
        return item;
    }

    public ProcessedMultiBuy apply(long amount) {
        long applies = amount / qty;
        long totalPrice = price * applies;
        return new ProcessedMultiBuy(totalPrice, applies * qty);
    }

    @Override
    public String toString() {
        return "Item " + item + " at " + price + " for " + qty;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((item == null) ? 0 : item.hashCode());
        result = prime * result + (int) (price ^ (price >>> 32));
        result = prime * result + qty;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        MultiItemPricingRule other = (MultiItemPricingRule) obj;
        if (item == null) {
            if (other.item != null)
                return false;
        } else if (!item.equals(other.item))
            return false;
        if (price != other.price)
            return false;
        if (qty != other.qty)
            return false;

        return true;
    }
}
