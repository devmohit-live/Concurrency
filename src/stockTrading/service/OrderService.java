package stockTrading.service;

import lombok.SneakyThrows;
import stockTrading.exceptions.StockNotFound;
import stockTrading.models.Order;
import stockTrading.workers.StockWorker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static stockTrading.util.StockUtil.validateAndGetStockWorker;

public class OrderService {

    final Map<String, StockWorker> stockWorkers;

    public OrderService(Map<String, StockWorker> stockWorkers) {
        this.stockWorkers = stockWorkers;
    }

    @SneakyThrows
    public void addOrder(Order order) {
        StockWorker stockWorker = validateAndGetStockWorker(stockWorkers, order.getStockSymbol());
        Thread.sleep(2000);
        stockWorker.addOrder(order);
    }

    public void cancelOrder(Order order) {
        StockWorker stockWorker = validateAndGetStockWorker(stockWorkers ,order.getStockSymbol());
        stockWorker.cancelOrder(order);
    }

    public void modifyOrder(Order order) {
        StockWorker stockWorker = validateAndGetStockWorker(stockWorkers ,order.getStockSymbol());
        stockWorker.modifyOrder(order);
    }

    public void getCompletedOrder(String symbol) {
        StockWorker stockWorker = validateAndGetStockWorker(stockWorkers,symbol);
        System.out.println("Completed order for Stock " + symbol + " are: ");
        for (Order order : stockWorker.getCompletedOrders()) {
            System.out.println(order);
        }
        System.out.println(" ---- Finished --------");
    }

    public void getPendingAndPartialOrder(String symbol) {
        StockWorker stockWorker = validateAndGetStockWorker(stockWorkers,symbol);
        System.out.println("Pending order for Stock " + symbol + " are: ");
        for (Order order : stockWorker.getOrderBook()) {
            System.out.println(order);
        }
        System.out.println(" ---- Finished --------");
    }


}
