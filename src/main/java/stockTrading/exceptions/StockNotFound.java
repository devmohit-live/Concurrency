package stockTrading.exceptions;

public class StockNotFound extends RuntimeException {
    final static String defaultMsg = "Stock not found on exchange, PLease register the stock first";

    public StockNotFound(String message) {
        super(message);
    }

    public StockNotFound() {
        super(defaultMsg);
    }
}
