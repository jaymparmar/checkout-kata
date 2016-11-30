package itv.supermarket.checkout;

public class ProcessedMultiBuy {

    private long price;
    private long processed_qty;

    public ProcessedMultiBuy(long price, long processed_qty) {
        super();
        this.price = price;
        this.processed_qty = processed_qty;
    }

    long getPrice() {
        return price;
    }

    long getProcessedQty() {
        return processed_qty;
    }

    @Override
    public String toString()
    {
        return "ProcessedMultiBuy [price:" + price + ", processed_qty:" + processed_qty + "]";
    }
}