package stockTrading.exceptions;

public class StockAlreadyPresent extends RuntimeException {
    final static String defaultMsg = "Stock already present on Exchange";

    public StockAlreadyPresent(String message) {
        super(message);
    }

    public StockAlreadyPresent() {
        super(defaultMsg);
    }
}
