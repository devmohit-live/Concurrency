package stockTrading;

import lombok.SneakyThrows;
import stockTrading.exceptions.StockAlreadyPresent;
import stockTrading.exceptions.StockNotFound;
import stockTrading.models.Order;
import stockTrading.models.Stock;
import stockTrading.models.User;
import stockTrading.workers.StockWorker;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StockExchange {
    public static final int DEFAULT_WORKER_COUNT = 1;
    final List<User> users;
    final Map<String, StockWorker> stockWorkers;

    StockExchange() {
        this.users = new ArrayList<>();
        this.stockWorkers = new ConcurrentHashMap<>();
    }

    public void registerUser(User user) {
        users.add(user);
        System.out.println("Added user: " + user.toString());
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

    @SneakyThrows
    public void addOrder(Order order) {
        StockWorker stockWorker = validateAndGetStockWorker(order.getStockSymbol());
        Thread.sleep(2000);
        stockWorker.addOrder(order);
    }

    public void cancelOrder(Order order) {
        StockWorker stockWorker = validateAndGetStockWorker(order.getStockSymbol());
        stockWorker.cancelOrder(order);
    }

    public void modifyOrder(Order order) {
        StockWorker stockWorker = validateAndGetStockWorker(order.getStockSymbol());
        stockWorker.modifyOrder(order);
    }

    public void getStockWorkersCount(String symbol) {
        StockWorker stockWorker = validateAndGetStockWorker(symbol);
        System.out.printf("Stock workers count for Stock %s is %s: \n", symbol, stockWorker.getWorkersCount());
    }

    public void increaseWorkersCount(String symbol, int count) {
        StockWorker stockWorker = validateAndGetStockWorker(symbol);
        stockWorker.increaseWorkerCountBy(count);
        System.out.println("Increase worker count for Stock " + symbol + " is " + count);
    }

    private StockWorker validateAndGetStockWorker(String symbol) throws StockNotFound {
        StockWorker stockWorker = stockWorkers.get(symbol);
        if (stockWorker == null) {
            throw new StockNotFound();
        }
        return stockWorker;
    }

    public void getCompletedOrder(String symbol) {
        StockWorker stockWorker = validateAndGetStockWorker(symbol);
        System.out.println("Completed order for Stock " + symbol + " are: ");
        for (Order order : stockWorker.getCompletedOrders()) {
            System.out.println(order);
        }
        System.out.println(" ---- Finished --------");
    }

    public void getPendingAndPartialOrder(String symbol) {
        StockWorker stockWorker = validateAndGetStockWorker(symbol);
        System.out.println("Pending order for Stock " + symbol + " are: ");
        for (Order order : stockWorker.getOrderBook()) {
            System.out.println(order);
        }
        System.out.println(" ---- Finished --------");
    }
}
