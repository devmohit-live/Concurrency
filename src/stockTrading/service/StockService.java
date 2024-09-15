package stockTrading.service;

import stockTrading.exceptions.StockAlreadyPresent;
import stockTrading.models.Stock;
import stockTrading.workers.StockWorker;

import java.util.Map;

import static stockTrading.util.StockUtil.validateAndGetStockWorker;

public class StockService {
    public static final int DEFAULT_WORKER_COUNT = 1;
    final Map<String, StockWorker> stockWorkers;


    public StockService(Map<String, StockWorker> stockWorkers) {
        this.stockWorkers=stockWorkers;
    }
    public void addStock(Stock stock) {
        StockWorker stockWorker = new StockWorker(stock);
        StockWorker existingWorker = stockWorkers.putIfAbsent(stock.getSymbol(), stockWorker);
        if (existingWorker != null) {
            throw new StockAlreadyPresent();
        }
        stockWorker.increaseWorkerCountBy(DEFAULT_WORKER_COUNT);
        stockWorker.increaseExpiryWorkerCountBy(DEFAULT_WORKER_COUNT);
        System.out.println("Added stock: " + stock);
    }

    public void getStockWorkersCount(String symbol) {
        StockWorker stockWorker = validateAndGetStockWorker(stockWorkers, symbol);
        System.out.printf("Stock workers count for Stock %s is %s: \n", symbol, stockWorker.getWorkersCount());
    }

    public void increaseWorkersCount(String symbol, int count) {
        StockWorker stockWorker = validateAndGetStockWorker(stockWorkers, symbol);
        stockWorker.increaseWorkerCountBy(count);
        System.out.println("Increase worker count for Stock " + symbol + " is " + count);
    }
}
