package itv.supermarket.checkout;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MultiItemPricingTest {

    private MultiItemPricingRule multiItemPricingRule;

    @Before
    public void setUp() throws Exception {
        multiItemPricingRule = new MultiItemPricingRule(new Item("A"), 50, 2);
    }

    @Test
    public void applyWithMultiOffer() {
        ProcessedMultiBuy processedMultiBuy = multiItemPricingRule.apply(5);
        assertEquals(100, processedMultiBuy.getPrice());
        assertEquals(4, processedMultiBuy.getProcessedQty());
    }

    @Test
    public void applyWithoutMultiOffer() {
        ProcessedMultiBuy processedMultiBuy = multiItemPricingRule.apply(1);
        assertEquals(0, processedMultiBuy.getPrice());
        assertEquals(0, processedMultiBuy.getProcessedQty());
    }
}
