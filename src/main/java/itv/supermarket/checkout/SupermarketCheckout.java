package itv.supermarket.checkout;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SupermarketCheckout {

    public Transaction initiateTransaction(Set<SingleItemPricingRule> regularPrice, Set<MultiItemPricingRule> offers) {
        return new Transaction(regularPrice, offers);
    }

    public long calculateTotalBill(final Transaction transaction) {

        Map<Item, Long> shoppingBasket = transaction.shoppingBasket;
        int totalAmount = 0;

        for(MultiItemPricingRule rule : transaction.offers) {
            if(shoppingBasket.containsKey(rule.getItem())) {
                long amount = shoppingBasket.get(rule.getItem());

                ProcessedMultiBuy processedMultiBuy= rule.apply(amount);
                totalAmount += processedMultiBuy.getPrice();
                shoppingBasket.put(rule.getItem(), amount - processedMultiBuy.getProcessedQty());
            }
        }

        for(SingleItemPricingRule rule : transaction.regularPrice) {
            if(shoppingBasket.containsKey(rule.getItem())) {
                long amount = shoppingBasket.get(rule.getItem());
                totalAmount += rule.apply(amount);
            }
        }

        return totalAmount;
    }

    public class Transaction {
        private Map<Item, Long> shoppingBasket = new HashMap<Item, Long>();
        private Set<SingleItemPricingRule> regularPrice;
        private Set<MultiItemPricingRule> offers;

        Transaction(Set<SingleItemPricingRule> regularPrice, Set<MultiItemPricingRule> offers) {
            this.regularPrice = regularPrice;
            this.offers = offers;
        }

        public Transaction scan(Item item) {
            Long amount = shoppingBasket.get(item);

            if(amount == null) {
                amount = 0L;
            }

            amount++;

            shoppingBasket.put(item, amount);
            return this;
        }
    }
}
