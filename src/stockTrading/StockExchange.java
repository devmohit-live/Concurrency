package stockTrading;

import lombok.SneakyThrows;
import stockTrading.exceptions.StockAlreadyPresent;
import stockTrading.exceptions.StockNotFound;
import stockTrading.models.Order;
import stockTrading.models.Stock;
import stockTrading.models.User;
import stockTrading.workers.StockWorker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StockExchange {
    public static final int DEFAULT_WORKER_COUNT = 1;
    List<User> users;
    Map<String, StockWorker> stockWorkers;

    StockExchange() {
        this.users = new ArrayList<>();
        this.stockWorkers = new HashMap<>();
    }

    public void registerUser(User user) {
        users.add(user);
        System.out.println("Added user: " + user.toString());
    }

    public void addStock(Stock stock) {
        if (stockWorkers.containsKey(stock.getSymbol())) {
            throw new StockAlreadyPresent();
        }
        StockWorker stockWorker = new StockWorker(stock);
        stockWorkers.put(stock.getSymbol(), stockWorker);
        stockWorker.addWorkerCount(DEFAULT_WORKER_COUNT);
        System.out.println("Added stock: " + stock);
    }

    @SneakyThrows
    public void addOrder(Order order) {
        validateStock(order.getStockSymbol());
        Thread.sleep(2000);
        stockWorkers.get(order.getStockSymbol()).addOrder(order);
    }

    public void cancelOrder(Order order) {
        validateStock(order.getStockSymbol());
        stockWorkers.get(order.getStockSymbol()).cancelOrder(order);
    }

    public void modifyOrder(Order order) {
        validateStock(order.getStockSymbol());
        stockWorkers.get(order.getStockSymbol()).modifyOrder(order);
    }

    public void getStockWorkersCount(String symbol) {
        validateStock(symbol);
        System.out.printf("Stock workers count for Stock %s is %s: \n", symbol, stockWorkers.get(symbol).getWorkersCount());
    }

    public void increaseWorkersCount(String symbol, int count) {
        validateStock(symbol);
        stockWorkers.get(symbol).addWorkerCount(count);
        System.out.println("Increase worker count for Stock " + symbol + " is " + count);
    }

    private void validateStock(String symbol) throws StockNotFound{
        if (!stockWorkers.containsKey(symbol)) {
            throw new StockNotFound();
        }
    }


    public void getCompletedOrder(String symbol) {
        validateStock(symbol);
        System.out.println("Completed order for Stock " + symbol+" are: ");
        StockWorker stockWorker = stockWorkers.get(symbol);
        for(Order order: stockWorker.getCompletedOrders()){
            System.out.println(order);
        }
        System.out.println(" ---- Finished --------");
    }


    public void getPendingAndPartialOrder(String symbol) {
        validateStock(symbol);
        System.out.println("Pending order for Stock " + symbol+" are: ");
        StockWorker stockWorker = stockWorkers.get(symbol);
        for(Order order: stockWorker.getOrderBook()){
            System.out.println(order);
        }
        System.out.println(" ---- Finished --------");
    }

}
