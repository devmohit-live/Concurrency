package stockTrading.util;

import stockTrading.exceptions.StockNotFound;
import stockTrading.workers.StockWorker;

import java.util.Map;

public class StockUtil {
    public static StockWorker validateAndGetStockWorker(Map<String,StockWorker> stockWorkers, String symbol) throws StockNotFound {
        StockWorker stockWorker = stockWorkers.get(symbol);
        if (stockWorker == null) {
            throw new StockNotFound();
        }
        return stockWorker;
    }
}
