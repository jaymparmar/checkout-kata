package itv.supermarket.checkout;

public final class SingleItemPricingRule {

    private Item item;
    private long price;

    public SingleItemPricingRule(Item item, long price) {
        this.item = item;
        this.price = price;
    }

    public Item getItem() {
        return item;
    }

    public long apply(long amount) {
        return (amount * price);
    }

    @Override
    public String toString() {
        return "Item " + item + " at " + price + " each";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((item == null) ? 0 : item.hashCode());
        result = prime * result + (int) (price ^ (price >>> 32));
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

        SingleItemPricingRule other = (SingleItemPricingRule) obj;
        if (item == null) {
            if (other.item != null)
                return false;
        } else if (!item.equals(other.item))
            return false;
        if (price != other.price)
            return false;

        return true;
    }
}
