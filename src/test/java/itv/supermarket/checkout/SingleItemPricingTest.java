package itv.supermarket.checkout;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SingleItemPricingTest {


    private SingleItemPricingRule singleItemPricingRule;

    @Before
    public void setUp() throws Exception {
        singleItemPricingRule = new SingleItemPricingRule(new Item("A"), 50);
    }

    @Test
    public void apply() {
        long result = singleItemPricingRule.apply(5);
        assertEquals(250, result);
    }
}
