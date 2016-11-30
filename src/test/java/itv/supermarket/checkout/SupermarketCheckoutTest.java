package itv.supermarket.checkout;

import itv.supermarket.checkout.SupermarketCheckout.Transaction;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class SupermarketCheckoutTest {

    private static final Item A = new Item("A");
    private static final Item B = new Item("B");
    private static final Item C = new Item("C");
    private static final Item D = new Item("D");

    private Set<SingleItemPricingRule> regularPrices = new HashSet();
    private Set<MultiItemPricingRule> offers = new HashSet();

    private SupermarketCheckout supermarketCheckout = new SupermarketCheckout();

    @Before
    public void setUp() throws Exception {

        regularPrices.add(new SingleItemPricingRule(A, 50));
        regularPrices.add(new SingleItemPricingRule(B, 30));
        regularPrices.add(new SingleItemPricingRule(C, 20));
        regularPrices.add(new SingleItemPricingRule(D,15));

        offers.add(new MultiItemPricingRule(A, 130, 3));
        offers.add(new MultiItemPricingRule(B, 45, 2));

        supermarketCheckout = new SupermarketCheckout();

    }

    @Test
    public void calculateBillForSingleItems() {
        Transaction transaction = supermarketCheckout.initiateTransaction(regularPrices, offers)
                .scan(A)
                .scan(D);

        assertEquals(65, supermarketCheckout.calculateTotalBill(transaction));
    }

    @Test
    public void calculateBillForMultiOfferItems() {
        Transaction transaction = supermarketCheckout.initiateTransaction(regularPrices, offers)
                .scan(A)
                .scan(A)
                .scan(A)
                .scan(C);

        assertEquals(150, supermarketCheckout.calculateTotalBill(transaction));
    }

    @Test
    public void calculateBillForMultiOfferItemsOnly() {
        Transaction transaction = supermarketCheckout.initiateTransaction(regularPrices, offers)
                .scan(A)
                .scan(A)
                .scan(A);

        assertEquals(130, supermarketCheckout.calculateTotalBill(transaction));
    }

    @Test
    public void calculateBillForMultiOfferItemsInAnyOrder() {
        Transaction transaction = supermarketCheckout.initiateTransaction(regularPrices, offers)
                .scan(B)
                .scan(A)
                .scan(B);

        assertEquals(95, supermarketCheckout.calculateTotalBill(transaction));
    }

    @Test
    public void calculateBillForMultiOfferItemsWhichIncludesExtraItemNotPartOfOffer() {
        Transaction transaction = supermarketCheckout.initiateTransaction(regularPrices, offers)
                .scan(B)
                .scan(A)
                .scan(B)
                .scan(B);

        assertEquals(125, supermarketCheckout.calculateTotalBill(transaction));
    }
}
